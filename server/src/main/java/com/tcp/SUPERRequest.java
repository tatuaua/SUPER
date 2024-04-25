package com.tcp;

public class SUPERRequest {

    private int requestType = -1;
    private String requestBody;

    public SUPERRequest(String requestContent){
        parse(requestContent);
    }

    public void parse(String requestContent){
        String[] split = requestContent.split(";");

        if(!split[0].equals("0") && !split[0].equals("1")){
            System.out.println("Invalid start character");
            return;
        }

        requestType = Integer.parseInt(split[0]);

        requestBody = split[1];
    }

    public int getRequestType(){
        return requestType;
    }

    public String getRequestBody(){
        return requestBody;
    }
}
