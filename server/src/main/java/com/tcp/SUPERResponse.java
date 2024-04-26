package com.tcp;

public class SUPERResponse {

    private int result = -1;
    private String responseBody;

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

    public int getResponseType(){
        return result;
    }

    public String getResponseBody(){
        return responseBody;
    }

    @Override
    public String toString(){
        return "" + getResponseType() + ";" + getResponseBody();
    }
}
