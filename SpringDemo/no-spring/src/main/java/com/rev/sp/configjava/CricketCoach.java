package com.rev.sp.configjava;

public class CricketCoach {
    // what does this line do ?
    CricketFortune cricketFortune;
    // this for dependency
    public String getDailyWorkout() {
        return "Practice fielding";
    }
   // constrcutor injection
    public CricketCoach(CricketFortune cricketFortune) {
        this.cricketFortune = cricketFortune;

    }

}
