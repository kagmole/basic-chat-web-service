package com.kagmole.workshops.angularjs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kagmole.workshops.basicchat.webservice.BasicChatWebServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicChatWebServiceApplication.class)
public class BasicChatWebServiceApplicationTests {
	
	@Test
	public void contextLoads() {
		// Yeah should be enough!
	}
}
