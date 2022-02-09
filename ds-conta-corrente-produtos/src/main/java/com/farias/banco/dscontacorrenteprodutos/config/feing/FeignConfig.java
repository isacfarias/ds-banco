package com.farias.banco.dscontacorrenteprodutos.config.feing;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.farias.banco.dscontacorrenteprodutos.feignclients.impls.CustomJacksonModule;
import com.fasterxml.jackson.databind.Module;

import feign.codec.Encoder;

@Configuration
public class FeignConfig {

	@Autowired
	private ObjectFactory<HttpMessageConverters> messageConverters;

	@Bean
	public Encoder feignEncoder() {
		return new PageableQueryEncoder(new SpringEncoder(messageConverters));
	}
	
	@Bean
	public Module pageJacksonModule() {
		return new PageJacksonModule();
	}

	@Bean
    public Module myJacksonModule() {
        return new CustomJacksonModule();
    }
}