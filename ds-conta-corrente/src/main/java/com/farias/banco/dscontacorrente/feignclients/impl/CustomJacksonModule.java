package com.farias.banco.dscontacorrente.feignclients.impl;

import org.springframework.data.domain.Page;

import com.farias.banco.dscontacorrente.feignclients.PageMixInCustom;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomJacksonModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	@Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(Page.class, PageMixInCustom.class);
    }
}