package com.tcp;

public class SUPERRequest {

    private String endpointName;
    private int requestType = -1;
    private String requestBody;

    /** Parses a raw string into a SUPERRequest. 
     * @return Whether the raw string was a valid SUPERRequest
    */
    public boolean parse(String requestContent){
        if (requestContent == null || requestContent.isEmpty()) {
            return false;
        }

        String[] split = requestContent.split(";");

        if (split.length != 2 && split.length != 3) {
            return false;
        }

        //SUPEREncryption enc = new SUPEREncryption();

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

    /** Builds a request from the given parameters. Using this ensures a valid SUPERRequest is sent.
     * @param endpoint The endpoint on the server to make the request to
     * @param requestType The type of request, 0 or 1
     * @param requestBody The body of the request
    */
    public boolean build(String endpoint, int requestType, String requestBody){
        if(endpoint == null){return false;}
        if(requestType != 0 && requestType != 1){return false;}
        if(requestType == 1 && requestBody == null){return false;}

        // If its a get request and the body is null, just replace with empty str
        if(requestBody == null){requestBody = "";}

        this.endpointName = endpoint;
        this.requestType = requestType;
        this.requestBody = requestBody;

        return true;
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

    /** Returns a raw string representation of the request eg. ENDPOINT;TYPE;BODY */
    public String raw(){
        return this.getEndpointName() + ";" + this.getRequestType() + ";" + this.getRequestBody();
    }
}
