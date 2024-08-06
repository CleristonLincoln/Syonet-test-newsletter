package br.com.syonet.newsletter.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {

    Page<T> getPageable(Pageable pageable);

    T save(T t);
}
