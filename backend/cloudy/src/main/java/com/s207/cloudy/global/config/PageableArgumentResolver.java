package com.s207.cloudy.global.config;

import com.s207.cloudy.global.error.enums.ErrorCode;
import com.s207.cloudy.global.error.exception.InvalidPaginationArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PageableArgumentResolver extends PageableHandlerMethodArgumentResolver {

    static final int MIN_PAGE_NUM  = 0;
    static final int MAX_PAGE_PER_SIZE_NUM = 100;
    static final int MIN_PAGE_PER_SIZE_NUM = 0;


    private void validatePage(int page){
        if (page < MIN_PAGE_NUM) {
            throw new InvalidPaginationArgumentException(ErrorCode.INVALID_PAGINATION_PAGE);
        }
    }

    private void validateSize(int size){
        if (size < MIN_PAGE_PER_SIZE_NUM || size > MAX_PAGE_PER_SIZE_NUM) {
            throw new InvalidPaginationArgumentException(ErrorCode.INVALID_PAGINATION_SIZE);
        }
    }


    private int getPage(NativeWebRequest webRequest, Pageable pageable){

        if (webRequest.getParameter("page") == null) {
            return pageable.getPageNumber();
        } else {
            return Integer.parseInt(webRequest.getParameter("page"));
        }
    }

    private int getSize(NativeWebRequest webRequest, Pageable pageable){
        if (webRequest.getParameter("size") == null) {
            return pageable.getPageSize();
        } else {
            return Integer.parseInt(webRequest.getParameter("size"));
        }
    }

    @Override
    public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final var pageable = super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);

        if (webRequest.getParameter("page") == null && webRequest.getParameter("size") == null) {
            return pageable;
        }

        var page = getPage(webRequest, pageable);
        var size = getSize(webRequest, pageable);

        validatePage(page);
        validateSize(size);

//        int size = 20;
//        int page = 0;

        return PageRequest.of(page, size);

    }
}