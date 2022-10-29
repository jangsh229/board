package com.testJunit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={
"file:src/main/webapp/WEB-INF/spring/*.xml"}) //root-context.xml
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordEncoderTest {
	@Autowired
	private PasswordEncoder pe;
	
	@Test
    public void passwordEncode() throws Exception{
        System.out.println(pe.encode("qwer1234"));
    }
	
	@Test
    public void passwordTest() throws Exception{
    	String encodePasswd = "$2a$10$USbExG2YOZJqu5rR9eWAqO3NqwjS6c8uI0c695cnURA2gxqRnx41O";
    	String password = "1234";
    	boolean test = pe.matches(password, encodePasswd);
    	System.out.println(test);
    }
}
