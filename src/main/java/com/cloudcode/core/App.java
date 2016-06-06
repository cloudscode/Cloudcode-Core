package com.cloudcode.core;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        StandardPasswordEncoder encoder = new StandardPasswordEncoder( "admin");
        System.out.println();
System.out.println(new Md5PasswordEncoder().encodePassword("000000", "admin"));


Md5PasswordEncoder md5 = new Md5PasswordEncoder();       
md5.setEncodeHashAsBase64(false);       
       
// 使用动态加密盐的只需要在注册用户的时候将第二个参数换成用户名即可       
String pwd = md5.encodePassword("000000", "admin");       
System.out.println("MD5 SystemWideSaltSource: " + pwd + " len=" + pwd.length());   
    }
}
