package com.tcp;

public class MyEndpoint implements SUPEREndpoint {
    @Override
    public SUPERResponse get() {
        return new SUPERResponse("2;Successful get");
    }

    @Override
    public SUPERResponse post(String requestBody) {
        boolean val = requestBody.equals("correct");
        if(val){
            return new SUPERResponse("2;Successful post");
        } else {
            return new SUPERResponse("3;Failed post");
        }
    }
}
