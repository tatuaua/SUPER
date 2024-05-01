package com.tcp;

public class MyEndpoint implements SUPEREndpoint {

    @Override
    public SUPERResponse get() {
        SUPERResponse resp = new SUPERResponse();
        resp.build(2, "Hello from SUPERServer");
        return resp;
    }

    @Override
    public SUPERResponse post(String requestBody) {
        System.out.println("Endpoint got: " + requestBody);
        SUPERResponse resp = new SUPERResponse();
        resp.build(2, "Posted!");
        return resp;
    }
}
