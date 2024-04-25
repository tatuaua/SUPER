package com.tcp;

import java.util.ArrayList;

public class CarEndpoint implements SUPEREndpoint{

    ArrayList<String> cars;

    CarEndpoint(){
        cars = new ArrayList<>();
    }

    @Override
    public String get(String requestContent) {
        return "2;" + cars.toString();
    }

    @Override
    public String post(String requestContent) {
        cars.add(requestContent);
        return "2;Cars posted";
    }
    
}
