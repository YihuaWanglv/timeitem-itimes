package com.iyihua.itimes.component.message;

import org.springframework.data.redis.listener.ChannelTopic;

public interface RedisPublisher {

	public void publish(ChannelTopic topic, RedisMessage message);
}
