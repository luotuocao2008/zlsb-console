package com.zonli.zslb.service;

import com.zonli.zslb.common.model.ValidationResponse;
import com.zonli.zslb.common.constants.MessageConstants;
import com.zonli.zslb.dao.UserMapper;
import com.zonli.zslb.common.entity.User;
import com.zonli.zslb.util.MyBatisSession;
import com.zonli.zslb.common.model.Page;
import com.zonli.zslb.util.ResponseUtil;
import com.zonli.zslb.util.SecurityUtil;
import com.zonli.zslb.util.UploadUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getByUsernameAndPassword(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        List<User> list = userMapper.selectByUsernameAndPassword(user);
        if (list != null && !list.isEmpty())
            return list.get(0);
        else return null;
    }

    public List<User> getByPage(Page page) {
        try (SqlSession session = MyBatisSession.getSession()) {
            int rowNumber = session.getMapper(UserMapper.class).selectCount();
            page.setRowNumber(rowNumber);
            List<User> list = session.selectList("selectUserByPage", null, page.toRowBounds());
            return list;
        }
    }

    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User getByUsername(String username) {
        List<User> list = userMapper.selectByUsername(username);
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Transactional
    public ValidationResponse create(User user) {
        List<FieldError> errors = new ArrayList<>();
        if (getByUsername(user.getUsername()) == null) {
            String newPwd = SecurityUtil.MD5(user.getPassword());
            user.setPassword(newPwd);
            int n = userMapper.insertSelective(user);
            if (n > 0) {
                return ResponseUtil.successValidation(MessageConstants.USER_CREATE_SUCCESS);
            } else {
                errors.add(new FieldError("username", "username", MessageConstants.USER_CREATE_FAIL));
            }
        } else {
            errors.add(new FieldError("username", "username", MessageConstants.USER_CREATE_REPEAT));
        }
        return ResponseUtil.failFieldValidation(errors);
    }

    /**
     * 保存用户信息
     * @param user
     * @param httpSession
     * @return
     */
    @Transactional
    public ValidationResponse save(User user, HttpSession httpSession) {
        String message;
        User sessionUser = (User) httpSession.getAttribute("sessionUser");
        if (sessionUser != null && sessionUser.getId().equals(user.getId())) {
            user.setUsername(null);
            int n = userMapper.updateByPrimaryKeySelective(user);
            if (n > 0) {
                return ResponseUtil.successValidation(MessageConstants.USER_SAVE_SUCCESS);
            } else {
                message = MessageConstants.USER_SAVE_FAIL;
            }
        } else {
            message = MessageConstants.USER_LOGIN_REQUIRE;
        }
        return ResponseUtil.failValidation(message);
    }

    /**
     * 保存密码
     * @param user
     * @param oldPassword
     * @param httpSession
     * @return
     */
    @Transactional
    public ValidationResponse savePassword(User user, String oldPassword, HttpSession httpSession) {
        User u = (User) httpSession.getAttribute("sessionUser");
        if(SecurityUtil.MD5(oldPassword).equals(u.getPassword())){
            user.setPassword(SecurityUtil.MD5(user.getPassword()));
            return save(user, httpSession);
        }else{
            return ResponseUtil.failValidation(MessageConstants.USER_PASSWORD_FAIL);
        }
    }

    /**
     * 上传图片并保存为用户头像
     * @param file
     * @param httpSession
     * @return
     * @throws IOException
     */
    @Transactional
    public ValidationResponse savePhoto(MultipartFile file, HttpSession httpSession) throws IOException {
        User user = (User) httpSession.getAttribute("sessionUser");
        if (user != null) {
            String filepath = httpSession.getServletContext().getRealPath("/resource/upload/photo/");
            ValidationResponse response = UploadUtil.upload(file, filepath);
            if (!response.success())
                return response;
            user.setPhoto((String) response.getData().get("filename"));
            return save(user, httpSession);
        }
        return ResponseUtil.failValidation(MessageConstants.USER_LOGIN_REQUIRE);
    }
}
