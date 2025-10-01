package com.spldeolin.satisficing.app.openapi;

/**
 * @author Deolin 2025-08-02
 */
public interface OpenApiCredentialService {

    String getSecretKeyByAccessKey(String accessKey) throws OpenApiAuthcException;

}
