package com.farias.banco.contacorrenteprodutos.modules.integration.feign.impl;

import com.farias.banco.contacorrenteprodutos.modules.integration.feign.PageMixInCustom;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomJacksonModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	@Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(Page.class, PageMixInCustom.class);
    }
}