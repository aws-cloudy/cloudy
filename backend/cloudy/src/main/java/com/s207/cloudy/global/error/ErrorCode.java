package com.s207.cloudy.global.error;

import lombok.Getter;

public enum ErrorCode {
    CE_001("CE001","API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다."),
    CE_002("CE002", "size 파라미터 값의 허용 범위가 1~100입니다."),
    CE_003("CE003", "page 파라미터 값의 허용 범위가 양수입니다."),
    CE_004("CE004", "로그인 한 회원만 요청 가능합니다."),
    CE_005("CE005", "작성자만 삭제 가능합니다."),
    CE_006("CE006", "요청한 데이터가 존재하지 않습니다."),
    SE_001("SE001", "Internal Server Error / 데이터베이스 오류입니다.");

    @Getter
    private String code;

    @Getter
    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
