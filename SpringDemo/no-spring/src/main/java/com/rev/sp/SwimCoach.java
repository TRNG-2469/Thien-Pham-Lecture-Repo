package com.rev.sp;

public class SwimCoach implements Coach{
    public String getDailyWorkout() {

        return "swim 5k";

    }

    @Override
    public String getDailyFortune() {
        return "";
    }
}
