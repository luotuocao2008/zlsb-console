package com.zonli.zslb.util;

import org.junit.Ignore;
import org.junit.Test;

public class SecurityUtilTest {
    @Ignore
    @Test
    public void test(){
        String password = "123456";
        System.out.println(SecurityUtil.MD5(password));
    }
}
