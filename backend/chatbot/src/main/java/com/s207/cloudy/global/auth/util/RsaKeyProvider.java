package com.s207.cloudy.global.auth.util;

import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.s207.cloudy.global.auth.error.exception.AuthorizationException;
import com.s207.cloudy.global.infra.cognito.dto.JwkDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import static com.s207.cloudy.global.error.enums.ErrorCode.PUBLIC_KEY_GENERATION_ERROR;

@RequiredArgsConstructor
@Slf4j
public class RsaKeyProvider implements RSAKeyProvider {

    private final RsaUtil rsaUtils;
    private final JwkDto jwk;

    @Override
    public RSAPublicKey getPublicKeyById(String keyId) {
        // JWKS를 가져오고 kid와 일치하는 키를 반환
        try {
            byte[] modulus = Base64.getUrlDecoder().decode(jwk.n());
            byte[] exponent = Base64.getUrlDecoder().decode(jwk.e());
            return rsaUtils.getRSAPublicKey(modulus, exponent);
        } catch (Exception e) {
            log.error("Public Key Generation Error :: {}", e.getMessage());
            throw new AuthorizationException(PUBLIC_KEY_GENERATION_ERROR);
        }
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return null;
    }

    @Override
    public String getPrivateKeyId() {
        return null;
    }
}
