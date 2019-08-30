package com.qa.theInfiniteTesselatingSwamp;

public class StuckInMud implements RandomEvents {
    public void event(){
        System.out.println("While travelling you get caught in a bog, and spend an entire day trying to free yourself.");
        System.out.println("(-1 day left to search)");
    }
}
