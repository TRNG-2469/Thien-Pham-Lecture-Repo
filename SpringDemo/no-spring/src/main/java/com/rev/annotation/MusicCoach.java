package com.rev.annotation;

import org.springframework.stereotype.Component;

@Component
public class MusicCoach {
    //insert a dependency

    SymphonyFortune symphonyFortune;
    public MusicCoach(SymphonyFortune symphonyFortune) {
        this.symphonyFortune = symphonyFortune;

    }
    public String getDailyMusic(){
        return "Pracice your scales for 30 minutes";

    }

    public String getDailySymphony(){
        return symphonyFortune.getDailyFortune();
    }
}
