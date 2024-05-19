package com.s207.cloudy.global.auth.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.KeyFactory;

@Component
@Slf4j
public class RsaUtil {

    public RSAPublicKey getRSAPublicKey(byte[] modulus, byte[] exponent){
        try {
            // RSAPublicKeySpec 생성
            var spec = new RSAPublicKeySpec(
                    new java.math.BigInteger(1, modulus), // modulus는 양수로 변환
                    new java.math.BigInteger(1, exponent) // exponent는 양수로 변환
            );

            // RSAPublicKey 생성
            var factory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) factory.generatePublic(spec);
        } catch (Exception e) {
            log.error("rsa public key 생성 중 오류, error message :{}", e.getLocalizedMessage());
            throw new RuntimeException("RSAPublicKey 생성 중 오류", e);
        }


    }

}
