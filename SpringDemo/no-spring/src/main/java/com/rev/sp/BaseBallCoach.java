package com.rev.sp;

public class BaseBallCoach implements Coach {


    public void setHf(HappyFortune hf) {
        this.hf = hf;
    }

    // go into configraution
    HappyFortune hf;

    public String getDailyWorkout() {
        return "Run 5k";

    }

    @Override
    public String getDailyFortune() {
        return hf.getFortune();
    }
}


