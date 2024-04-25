package com.tcp;

public class MyHandler implements SUPERHandler {
    @Override
    public String get(String requestBody) {
        boolean val = requestBody.equals("correct");
        if(val){
            return "2;Get function: (" + requestBody + ");";
        } else {
            return "3;Get function: (" + requestBody + ");";
        }
    }

    @Override
    public String post(String requestBody) {
        boolean val = requestBody.equals("correct");
        if(val){
            return "2;Post function: (" + requestBody + ");";
        } else {
            return "3;Post function: (" + requestBody + ");";
        }
    }
}
