package com.zerobase.weather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmotionEnum {
    HAPPY("1", "happy"),
    EXCITED("2", "excited"),
    FRESH("3", "fresh"),
    FULFILLED("4", "fulfilled"),
    LONGING("5", "longing"),
    ANGRY("6", "angry"),
    UNPLEASANT("7", "unpleasant"),
    ANNOYING("8", "annoying"),
    AFRAID("9", "afraid"),
    GLOOMY("10", "gloomy"),
    SAD("11", "sad"),
    DESPERATE("12", "desperate"),
    UNEASY("13", "uneasy");

    private final String emotionCd;
    private final String emotionNm;

    // emotionCd 기반으로 emotionNm 반환하는 정적 메소드
    public static String getEmotionNmByCd(String emotionCd) {
        for (EmotionEnum emotion : EmotionEnum.values()) {
            if (emotion.getEmotionCd().equals(emotionCd)) {
                return emotion.getEmotionNm();
            }
        }
        return null;
    }
}
