package com.lab47.dropwizard.redis;

import io.dropwizard.lifecycle.Managed;
import redis.clients.jedis.JedisPool;

public class RedisManagedPool implements Managed {

    private final JedisPool pool;

    public RedisManagedPool(JedisPool pool) {
        this.pool = pool;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        this.pool.destroy();
    }

    public JedisPool getPool() {
        return pool;
    }

}
