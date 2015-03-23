package com.lab47.dropwizard.redis;

import io.dropwizard.setup.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.lab47.dropwizard.redis.RedisConfiguration.HostAndPort;

public class RedisModule extends AbstractModule {

    private RedisManagedPool pool;

    @Override
    protected void configure() {
    }

    @Provides
    public JedisPool provideJedisPool(RedisManagedPool _pool) {
        return _pool.getPool();
    }

    @Provides
    public RedisManagedPool provideJedisPool(RedisConfiguration conf, Environment env) {
        // Add singleton
        if (null != pool) {
            return pool;
        }

        synchronized (this) {
            if (null != pool) {
                return pool;
            }

            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMinIdle(conf.getMinIdle());
            poolConfig.setMaxIdle(conf.getMaxIdle());
            poolConfig.setMaxTotal(conf.getMaxTotal());

            poolConfig.setTestOnBorrow(conf.isTestOnBorrow());
            poolConfig.setTestOnReturn(conf.isTestOnReturn());
            poolConfig.setTestWhileIdle(conf.isTestWhileIdle());
            poolConfig.setTimeBetweenEvictionRunsMillis(conf.getTimeBetweenEvictionRunsMillis());
            poolConfig.setNumTestsPerEvictionRun(conf.getNumTestsPerEvictionRun());
            poolConfig.setBlockWhenExhausted(conf.isBlockWhenExhausted());
            poolConfig.setMaxWaitMillis(conf.getMaxWaitMillis());

            HostAndPort ep = conf.getEndpoint();
            JedisPool _pool = new JedisPool(poolConfig, ep.getHost(), ep.getPort(), 10000);
            pool = new RedisManagedPool(_pool);

            // Add to environment
            env.lifecycle().manage(pool);
            env.healthChecks().register("redis", new RedisHealthCheck(_pool));

            return pool;
        }
    }

}
