package com.zonli.zslb.realm;

import com.zonli.zslb.common.entity.User;
import com.zonli.zslb.dao.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by luohaiyan on 2017/3/2.
 */
public class ShiroDbRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    //授权     @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        List<User> list = userMapper.selectByUsername(username);

        /* 这里编写授权代码 */
        Set<String> roleNames = new HashSet<String>();
        Set<String> permissions = new HashSet<String>();
        roleNames.add(username);
        roleNames.add("zhangsan");
        permissions.add("user.do?myjsp");
        permissions.add("login.do?main");
        permissions.add("login.do?logout");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleNames);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    //认证     @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

//        UsernamePasswordToken login_token = (UsernamePasswordToken) token;
// // //校验码判断逻辑
// //取得用户输入的校验码
//        String userInputValidCode = login_token.getValidCode();
// // //取得真实的正确校验码
//        String realRightValidCode = (String) SecurityUtils.getSubject().getSession().getAttribute("rand");
// //        if (null == userInputValidCode || !userInputValidCode.equalsIgnoreCase(realRightValidCode)) {
//            throw new ValidCodeException("验证码输入不正确");
//        }

        //以上校验码验证通过以后,查数据库
        String username = (String) token.getPrincipal();
        List<User> list = userMapper.selectByUsername(username);

        if (list == null || list.size() == 0) {
            throw new UnknownAccountException();//没找到帐号
        }

//        if (Boolean.FALSE.equals(user.getEnable())) {
//                throw new LockedAccountException(); //帐号锁定
//        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                list.get(0).getUsername(),
                list.get(0).getPassword(),
                getName());
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}