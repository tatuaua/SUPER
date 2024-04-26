package com.tcp;

import java.util.ArrayList;

public class BananaEndpoint implements SUPEREndpoint {

    ArrayList<String> bananas = new ArrayList<>();

    @Override
    public SUPERResponse get() {
        if(bananas.isEmpty()){
            return new SUPERResponse("3;No bananas!");
        }
        return new SUPERResponse("2;" + bananas.toString());
    }

    @Override
    public SUPERResponse post(String requestBody) {
        bananas.add(requestBody);
        return new SUPERResponse("2;Banana added!");
    }
}
