/** 
 * Project Name:mail-consumer 
 * File Name:TestMailConsumer.java 
 * Package Name:com.liu.mq 
 * Date:2019年1月24日上午11:49:33 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.mq;  
/** 
 * ClassName:TestMailConsumer <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月24日 上午11:49:33 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-context.xml"})
public class TestMailConsumer {

	@Test
	public void testSendEmail() {
		System.out.println("------------------");
	}
}
 