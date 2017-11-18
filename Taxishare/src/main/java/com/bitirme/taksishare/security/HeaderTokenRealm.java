package com.bitirme.taksishare.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by YunusS on 3/28/2016.
 */
public class HeaderTokenRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        HeaderAuthenticationToken hedearToken = (HeaderAuthenticationToken) token;
        String headerVal = (String) token.getCredentials();
//        User user = securityService.validateTokenAndGetUser(headerVal);
//        if(user != null){
            return new SimpleAuthenticationInfo(1 /* user.getId()*/, headerVal, getName());
//        } else {
//            throw new AuthenticationException();
//        }

    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return (token instanceof HeaderAuthenticationToken);
    }
}