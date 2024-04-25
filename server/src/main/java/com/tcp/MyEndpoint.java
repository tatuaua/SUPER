package com.tcp;

public class MyEndpoint implements SUPEREndpoint {
    @Override
    public String get(String requestBody) {
        boolean val = requestBody.equals("correct");
        if(val){
            return "2;Successful get";
        } else {
            return "3;Failed get";
        }
    }

    @Override
    public String post(String requestBody) {
        boolean val = requestBody.equals("correct");
        if(val){
            return "2;Successful post";
        } else {
            return "3;Failed post";
        }
    }
}
