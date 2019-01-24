/** 
 * Project Name:mail-producer 
 * File Name:MailProducerTest.java 
 * Package Name:com.liu.mq 
 * Date:2019年1月24日上午11:11:48 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.mq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liu.mq.model.MailParam;
import com.liu.mq.service.MailProducer;

/** 
 * ClassName:MailProducerTest <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月24日 上午11:11:48 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class MailProducerTest {

	private static final Log log = LogFactory.getLog(MailProducerTest.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath*:spring/spring-context.xml");
			context.start();

			MailProducer mqProducer = (MailProducer) context.getBean("mailProducer");
			for (int i = 0; i < 5; i++) {
				// 邮件发送，收件人的邮箱
				MailParam mail = new MailParam();
				mail.setTo("771474065@qq.com");
				mail.setSubject("ActiveMQ测试--->" + i);
				mail.setContent("通过ActiveMQ发送的邮件。");
				mqProducer.sendMessage(mail);
			}
			context.stop();
		} catch (Exception e) {
			log.error("--->MQ context start error:", e);
			System.exit(0);
		} finally {
			log.info("--->System.exit");
			System.exit(0);
		}
	}
}
 