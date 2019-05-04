package com.tugrulaslan;

import com.tugrulaslan.dto.CertificateRequest;
import com.tugrulaslan.dto.CredentialRequest;

public final class MockedObjectData {
    public static CertificateRequest certificateRequest = new CertificateRequest("MyCertificate", "ad:43:Xf:3f:34");
    public static CredentialRequest credentialRequest = new CredentialRequest("r00t", "p@$$w0rd");

    private MockedObjectData() {
    }
}
