package com.study.mockito.stubbing;

public class StubbingService {

    public int getI() {
        System.out.println("==============getI================");
        return 10;
    }

    public String getS() {
        System.out.println("==============getS================");
        throw new RuntimeException();
    }
}
