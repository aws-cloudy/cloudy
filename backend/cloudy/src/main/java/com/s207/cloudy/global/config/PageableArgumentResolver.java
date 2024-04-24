package com.s207.cloudy.global.config;

import com.s207.cloudy.global.error.ErrorCodeEnum;
import com.s207.cloudy.global.error.exception.InvalidPaginationArgumentException;
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

    @Override
    public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final Pageable pageable = super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);

        if (webRequest.getParameter("page") == null && webRequest.getParameter("size") == null) {
            return pageable;
        }

        int size = 20;
        int page = 0;

        if (webRequest.getParameter("page") == null) {
            page = pageable.getPageNumber();
        } else {
            page = Integer.parseInt(webRequest.getParameter("page"));
        }

        if (webRequest.getParameter("size") == null) {
            size = pageable.getPageSize();
        } else {
            size = Integer.parseInt(webRequest.getParameter("size"));
        }


        if (page < 0) {
            throw new InvalidPaginationArgumentException(ErrorCodeEnum.INVALID_PAGINATION_PAGE);
        }
        if (size < 1 || size > 100) {
            throw new InvalidPaginationArgumentException(ErrorCodeEnum.INVALID_PAGINATION_SIZE);
        }

        return PageRequest.of(page, size);

    }
}