package com.tcp;

public class SUPERRequest {

    private String endpointName;
    private int requestType = -1;
    private String requestBody;

    public boolean parse(String requestContent){//TODO: handle bad requests
        if (requestContent == null || requestContent.isEmpty()) {
            return false;
        }

        String[] split = requestContent.split(";");

        if (split.length != 2 && split.length != 3) {
            return false;
        }

        try {
            int type = Integer.parseInt(split[1].trim());
            
            if (type != 0 && type != 1) {return false;}

            endpointName = split[0];
            requestType = type;

            if(requestType == 1 && split.length == 2){return false;}

            requestBody = (requestType == 1) ? split[2] : "";

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getEndpointName(){
        return endpointName;
    }
    
    public int getRequestType(){
        return requestType;
    }

    public String getRequestBody(){
        return requestBody;
    }
}
