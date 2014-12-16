package com.lab47.dropwizard.redis;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedisConfiguration {
    @JsonProperty
    @NotNull
    @Valid
    private HostAndPort endpoint;

    public static class HostAndPort {
        @NotEmpty
        @JsonProperty
        private String host;

        @Min(1)
        @Max(65535)
        @JsonProperty
        private Integer port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }

    @JsonProperty
    @Min(0)
    private int minIdle = 0;

    @JsonProperty
    @Min(0)
    private int maxIdle = 0;

    @JsonProperty
    @Min(1)
    @Max(1024)
    private int maxTotal = 1024;

    public HostAndPort getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(HostAndPort endpoint) {
        this.endpoint = endpoint;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

}
