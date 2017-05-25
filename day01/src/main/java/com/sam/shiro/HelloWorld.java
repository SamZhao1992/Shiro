package com.sam.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;

/**
 * Hello world!
 */
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Begin...");
        //读取配置文件 初始化SecurityManager 工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取SecurityManager 实例
        SecurityManager securityManager = factory.getInstance();
        //把securityManager实例绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //得到当前执行的用户
        Subject subject = SecurityUtils.getSubject();
        //创建token令牌， 用户名/密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("java123", "123456");
        //登录身份认证
        try{
            subject.login(usernamePasswordToken);
            System.out.println("Login Success...*_*");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Login Fail!!!");
        }finally {
            //退出
            subject.logout();
        }
    }

}
