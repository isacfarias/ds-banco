package com.farias.banco.contacorrenteprodutos.modules.integration.feign;

import com.farias.banco.contacorrenteprodutos.modules.integration.feign.impl.PageResourcesImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize(as = PageResourcesImpl.class)
public interface PageMixInCustom{ }