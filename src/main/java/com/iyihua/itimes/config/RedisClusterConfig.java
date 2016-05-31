package com.iyihua.itimes.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

//@Configuration
public class RedisClusterConfig {

//	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> set = new HashSet<>();
		set.add(new HostAndPort("192.168.1.128", 7000));
		set.add(new HostAndPort("192.168.1.128", 7001));
		set.add(new HostAndPort("192.168.1.128", 7002));
		set.add(new HostAndPort("192.168.1.128", 7003));
		set.add(new HostAndPort("192.168.1.128", 7004));
		set.add(new HostAndPort("192.168.1.128", 7005));
		JedisCluster jedisCluster = new JedisCluster(set);
		return jedisCluster;
	}

}
