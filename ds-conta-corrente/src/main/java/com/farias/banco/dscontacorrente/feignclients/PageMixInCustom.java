package com.farias.banco.dscontacorrente.feignclients;

import com.farias.banco.dscontacorrente.feignclients.impl.PageResourcesImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize(as = PageResourcesImpl.class)
public interface PageMixInCustom{ }