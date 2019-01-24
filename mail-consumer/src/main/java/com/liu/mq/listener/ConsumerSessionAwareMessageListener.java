/** 
 * Project Name:mail-consumer 
 * File Name:ConsumerSessionAwareMessageListener.java 
 * Package Name:com.liu.mq.listener 
 * Date:2019年1月24日上午11:44:30 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.mq.listener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.alibaba.fastjson.JSONObject;
import com.liu.mq.model.MailParam;
import com.liu.mq.servie.MailBiz;

/** 
 * ClassName:ConsumerSessionAwareMessageListener <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月24日 上午11:44:30 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<Message> {

	private static final Log log = LogFactory
			.getLog(ConsumerSessionAwareMessageListener.class);

	@Autowired
	private JmsTemplate activeMqJmsTemplate;
	@Autowired
	private Destination sessionAwareQueue;
	@Autowired
	private MailBiz bailBiz;

	public synchronized void onMessage(Message message, Session session) {
		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			final String ms = msg.getText();
			log.info("--->receive message:" + ms);
			MailParam mailParam = JSONObject.parseObject(ms, MailParam.class);// 转换成相应的对象
			if (mailParam == null) {
				return;
			}
			try {
				bailBiz.mailSend(mailParam);
			} catch (Exception e) {
				// 发送异常，重新放回队列
				activeMqJmsTemplate.send(sessionAwareQueue,
						new MessageCreator() {
							public Message createMessage(Session session)
									throws JMSException {
								return session.createTextMessage(ms);
							}
						});
				log.error("--->MailException:", e);
			}
		} catch (Exception e) {
			log.error("--->", e);
		}
	}

}
 