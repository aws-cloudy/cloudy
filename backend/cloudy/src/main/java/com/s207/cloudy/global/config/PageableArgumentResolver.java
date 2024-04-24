package com.s207.cloudy.global.config;

import com.s207.cloudy.global.error.ErrorCodeEnum;
import com.s207.cloudy.global.error.exception.InvalidPaginationArgumentException;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PageableArgumentResolver extends PageableHandlerMethodArgumentResolver {

    @Override
    public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final Pageable pageable = super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);

        final int page = Integer.parseInt(webRequest.getParameter("page"));
        final int size = Integer.parseInt(webRequest.getParameter("size"));

        if (size < 1 || size > 100) {
            throw new InvalidPaginationArgumentException(ErrorCodeEnum.INVALID_PAGINATION_SIZE);
        }
        if (page < 0) {
            throw new InvalidPaginationArgumentException(ErrorCodeEnum.INVALID_PAGINATION_SIZE);
        }
        return pageable;
    }
}