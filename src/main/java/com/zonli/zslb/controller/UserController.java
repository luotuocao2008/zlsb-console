package com.zonli.zslb.controller;

import com.zonli.zslb.common.entity.User;
import com.zonli.zslb.common.constants.MessageConstants;
import com.zonli.zslb.service.UserService;
import com.zonli.zslb.util.SecurityUtil;
import com.zonli.zslb.common.model.ValidationResponse;
import com.zonli.zslb.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/u")
public class UserController {
    @Autowired
    private UserService userService;
    Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("user/login");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession httpSession) {
        SecurityUtils.getSubject().logout();
        httpSession.removeAttribute("sessionUser");
        httpSession.invalidate();
        return new ModelAndView("user/logout");
    }


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView editNew() {
        ModelAndView modelAndView = new ModelAndView("user/editNew");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable Integer id, HttpServletResponse response)
            throws IOException {
        User user = userService.get(id);
        if (user == null)
            response.sendError(404);
        return new ModelAndView("user/show", "user", user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        return new ModelAndView("user/edit", "user", user);
    }

    @RequestMapping(value = "/edit/photo", method = RequestMethod.GET)
    public ModelAndView editPhoto(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        return new ModelAndView("user/edit-photo", "user", user);
    }

    @RequestMapping(value = "/edit/password", method = RequestMethod.GET)
    public ModelAndView editPassword(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        return new ModelAndView("user/edit-password", "user", user);
    }

    /**
     * 注册用户
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ValidationResponse create(@ModelAttribute @Valid User user, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.failFieldValidation(bindingResult.getFieldErrors());
        }
        ValidationResponse res = userService.create(user);
        if (res.success()) {
            User u = userService.getByUsername(user.getUsername());
            httpSession.setAttribute("sessionUser", u);
        }
        return res;
    }

    /**
     * 验证用户名和密码
     */
    @RequestMapping(value = "/valid", method = RequestMethod.POST)
    public ValidationResponse valid(String username, String password, HttpSession httpSession) {

        User u = userService.getByUsernameAndPassword(username, SecurityUtil.MD5(password));
        if (u != null) {
            httpSession.setAttribute("sessionUser", u);
            UsernamePasswordToken token = new UsernamePasswordToken(username, SecurityUtil.MD5(password));
            token.setRememberMe(true);
            try {
                Subject currentUser = SecurityUtils.getSubject();
                currentUser.login(token);
                String userID = (String) currentUser.getPrincipal();
            } catch (UnknownAccountException uae) {
                return ResponseUtil.successValidation("用户认证失败：" + "username wasn't in the system.");
            } catch (IncorrectCredentialsException ice) {
                return ResponseUtil.successValidation("用户认证失败：" + "password didn't match.");
            } catch (LockedAccountException lae) {
                return ResponseUtil.successValidation("用户认证失败：" + "account for that username is locked - can't login.");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                token.clear();
                return ResponseUtil.successValidation("登录失败错误信息：" + e);

            }
            return ResponseUtil.successValidation(MessageConstants.USER_LOGIN_SUCCESS);
        }
        return ResponseUtil.failValidation(MessageConstants.USER_LOGIN_FAIL);
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ValidationResponse update(@PathVariable Integer id, @Valid User user,
                                     BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.failFieldValidation(bindingResult);
        } else {
            ValidationResponse res = userService.save(user, httpSession);
            if (res.success()) {
                httpSession.setAttribute("sessionUser", userService.get(id));
            }
            return res;
        }
    }

    /**
     * 修改用户密码
     *
     * @param id
     * @param user
     * @param oldPassword
     * @return
     */
    @RequestMapping(value = "/{id}/password", method = RequestMethod.PUT)
    public ValidationResponse updatePassword(@PathVariable Integer id, @ModelAttribute @Valid User user,
                                             BindingResult bindingResult, String oldPassword, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.failFieldValidation(bindingResult);
        } else {
            return userService.savePassword(user, oldPassword, httpSession);
        }
    }

    @RequestMapping(value = "/loginMessage", method = RequestMethod.GET)
    public ValidationResponse loginMessage() {
        return ResponseUtil.failValidation(MessageConstants.USER_LOGIN_REQUIRE);
    }
}
