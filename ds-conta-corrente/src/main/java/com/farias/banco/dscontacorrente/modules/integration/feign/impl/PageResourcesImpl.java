package com.farias.banco.dscontacorrente.modules.integration.feign.impl;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PageResourcesImpl<T> implements Page<T> {

    private final Page<T> delegate;

    public PageResourcesImpl(
            @JsonProperty("content") List<T> content,
            @JsonProperty("page")int number,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") long totalElements){
        delegate = new PageImpl<>(content, PageRequest.of(number, size), totalElements);
    }


    @JsonProperty
    @Override
    public int getTotalPages() {
        return delegate.getTotalPages();
    }

    @JsonProperty
    @Override
    public long getTotalElements() {
        return delegate.getTotalElements();
    }

    @JsonProperty("page")
    @Override
    public int getNumber() {
        return delegate.getNumber();
    }

    @JsonProperty
    @Override
    public int getSize() {
        return delegate.getSize();
    }

    @JsonProperty
    @Override
    public int getNumberOfElements() {
        return delegate.getNumberOfElements();
    }

    @JsonProperty
    @Override
    public List<T> getContent() {
        return delegate.getContent();
    }

    @JsonProperty
    @Override
    public boolean hasContent() {
        return delegate.hasContent();
    }

    @JsonIgnore
    @Override
    public Sort getSort() {
        return delegate.getSort();
    }

    @JsonProperty
    @Override
    public boolean isFirst() {
        return delegate.isFirst();
    }

    @JsonProperty
    @Override
    public boolean isLast() {
        return delegate.isLast();
    }

    @JsonIgnore
    @Override
    public boolean hasNext() {
        return delegate.hasNext();
    }

    @JsonIgnore
    @Override
    public boolean hasPrevious() {
        return delegate.hasPrevious();
    }

    @JsonIgnore
    @Override
    public Pageable nextPageable() {
        return delegate.nextPageable();
    }
    @JsonIgnore
    @Override
    public Pageable previousPageable() {
        return delegate.previousPageable();
    }
    
    @JsonIgnore
    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    @JsonIgnore
	@Override
	public <U> Page<U> map(Function<? super T, ? extends U> converter) {
		return null;
	}
}