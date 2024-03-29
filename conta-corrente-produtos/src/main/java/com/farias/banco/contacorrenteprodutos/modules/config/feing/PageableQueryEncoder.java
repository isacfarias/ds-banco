package com.farias.banco.contacorrenteprodutos.modules.config.feing;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

class PageableQueryEncoder implements Encoder {

    private final Encoder delegate;

    PageableQueryEncoder(Encoder delegate){
        this.delegate = delegate;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {

        if(object instanceof Pageable){
            Pageable pageable = (Pageable)object;
            template.query("page", pageable.getPageNumber() + "");
            template.query("size", pageable.getPageSize() + "");

            if(pageable.getSort() != null) {
                Collection<String> existingSorts = template.queries().get("sort");
                List<String> sortQueries  = existingSorts != null ? new ArrayList<>(existingSorts) : new ArrayList<>();
                for (Sort.Order order : pageable.getSort()) {
                    sortQueries.add(order.getProperty() + "," + order.getDirection());
                }
                template.query("sort", sortQueries);
            }

        }else{
            delegate.encode(object, bodyType, template);
        }
    }
}