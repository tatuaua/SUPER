package com.tcp;

public class SUPERResponse {

    private int result = -1;
    private String responseBody;

    public SUPERResponse(){}

    public SUPERResponse(String text){
        parse(text);
    }

    public void parse(String responseContent){
        String[] split = responseContent.split(";");

        if(!split[0].equals("2") && !split[0].equals("3") && !split[0].equals("-1")){
            System.out.println("Invalid start character");
            return;
        }

        result = Integer.parseInt(split[0]);
        responseBody = split[1];
    }

    public void build(int result, String responseBody){
        if(result != 2 && result != 3){
            return;
        }

        if(responseBody == null){
            return;
        }

        this.result = result;
        this.responseBody = responseBody;
    }

    public int getResponseType(){
        return result;
    }

    public String getResponseBody(){
        return responseBody;
    }

    public String raw(){
        return "" + getResponseType() + ";" + getResponseBody();
    }

    @Override
    public String toString(){
        String status = this.getResponseType() == 2 ? "successful" : "failed";
        return "Request " + status + "\nResponse body: " + this.getResponseBody();
    }
}
