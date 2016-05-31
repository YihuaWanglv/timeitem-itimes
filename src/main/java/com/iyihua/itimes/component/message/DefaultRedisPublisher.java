package com.iyihua.itimes.component.message;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class DefaultRedisPublisher implements RedisPublisher {

	private final RedisTemplate<String, Object> template;
	MessageSerializer serializer = new MessageSerializer();

	public DefaultRedisPublisher(final RedisTemplate<String, Object> template) {
		this.template = template;
	}

	@Override
	public void publish(ChannelTopic topic, RedisMessage message) {
		template.convertAndSend(topic.getTopic(), message);
	}

}
