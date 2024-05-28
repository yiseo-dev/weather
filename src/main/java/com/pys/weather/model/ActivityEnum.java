package com.pys.weather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActivityEnum {
    CLIMBING("1", "climbing"),
    CYCLING("2", "cycling"),
    CAMPING("3", "camping"),
    JOGGING("4", "jogging"),
    WALKING("5", "walking"),
    FISHING("6", "fishing"),
    SURFING("7", "surfing"),
    PICNICKING("8", "picnicking"),
    COOKING("9", "cooking"),
    YOGA("10", "yoga"),
    READING("11", "reading"),
    GAMES("12", "games"),
    FITNESS_EXERCISE("13", "fitness_exercise");

    private final String activityCd;
    private final String activityNm;

    public static String getActivityNmByCd(String activityCd) {
        for(ActivityEnum activityEnum : ActivityEnum.values()) {
            if(activityEnum.getActivityCd().equals(activityCd)) {
                return activityEnum.getActivityNm();
            }
        }
        return null;
    }
}
