package com.tcp;

public interface SUPEREndpoint {
    SUPERResponse get();
    SUPERResponse post(String requestContent);
}
