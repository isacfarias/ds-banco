package com.farias.banco.dscontacorrenteprodutos.feignclients;

import com.farias.banco.dscontacorrenteprodutos.feignclients.impl.PageResourcesImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize(as = PageResourcesImpl.class)
public interface PageMixInCustom{ }