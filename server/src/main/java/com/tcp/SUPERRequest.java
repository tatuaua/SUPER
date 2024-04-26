package com.tcp;

public class SUPERRequest {

    private String endPointName;
    private int requestType = -1;
    private String requestBody;

    public SUPERRequest(String requestContent){
        parse(requestContent);
    }

    public void parse(String requestContent){//TODO: handle bad requests

        String[] split = requestContent.split(";");

        endPointName = split[0];

        if(!split[1].equals("0") && !split[1].equals("1")){
            return;
        }

        requestType = Integer.parseInt(split[1]);

        if(requestType == 0){
            requestBody = "";
        } else {
            requestBody = split[2];
        }
    }

    public String getEndPointName(){
        return endPointName;
    }
    
    public int getRequestType(){
        return requestType;
    }

    public String getRequestBody(){
        return requestBody;
    }
}
