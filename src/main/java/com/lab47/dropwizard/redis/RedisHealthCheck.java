package com.lab47.dropwizard.redis;

import redis.clients.jedis.JedisPool;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;

public class RedisHealthCheck extends HealthCheck {
    private final JedisPool jedisPool;

    public RedisHealthCheck(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    protected Result check() throws Exception {
        if ("PONG".equalsIgnoreCase(jedisPool.getResource().ping())) {
            return HealthCheck.Result.healthy();
        } else {
            return HealthCheck.Result.unhealthy("Failed to ping redis");
        }
    }
}
