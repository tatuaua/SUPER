package com.tcp.example;

import com.tcp.SUPEREndpoint;
import com.tcp.SUPERResponse;

public class Endpoint implements SUPEREndpoint {

    @Override
    public SUPERResponse get() {
        SUPERResponse resp = new SUPERResponse();
        resp.build(2, "Hello from SUPERServer");
        return resp;
    }

    @Override
    public SUPERResponse post(String requestBody) {
        SUPERResponse resp = new SUPERResponse();
        resp.build(3, "You cant post here!");
        return resp;
    }

}