package com.s207.cloudy.global.auth.api;


import com.s207.cloudy.global.error.dto.CommonErrorResponse;
import com.s207.cloudy.global.error.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.s207.cloudy.global.error.enums.ErrorCode.UNAUTHORIZED;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/fail")

    public ResponseEntity<CommonErrorResponse> fail(){
        return ResponseEntity
                .status(UNAUTHORIZED.getHttpStatus())
                .body(
                        CommonErrorResponse
                                .builder()
                                .message(UNAUTHORIZED.getMessage())
                                .code(UNAUTHORIZED.getCode())
                                .build()
                );
    }

}
