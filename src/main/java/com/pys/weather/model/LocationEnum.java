package com.pys.weather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LocationEnum {
      SEOUL(1, "1835848", "서울특별시","seoul")
    , BUSAN(2, "1838524", "부산", "busan")
    , DAEGU(3, "1835329", "대구","daegu")
    , INCHEON(4, "1843564", "인천","incheon")
    , GWANGJU(5, "1841810", "광주","gwangju")
    , DAEJEON(6, "1835235", "대전","daejeon")
    , ULSAN(7,"1833742","울산","ulsan")
    , GYEONGGI(8, "1841610", "경기","gyeonggi-do")
    , GANGWON(9,"1843125","강원","gangwon-do")
    , JEJU(10, "1846265","제주","jeju-do")
    ;
    private Integer locId;
    private String locCd;
    private String locNm;
    private String locEngNm;

    // locCd를 기반으로 locId를 반환하는 정적 메소드
    public static Integer getLocIdByLocCd(String locCd) {
        for (LocationEnum location : LocationEnum.values()) {
            if (location.getLocCd().equals(locCd)) {
                return location.getLocId();
            }
        }
        return null;  // locCd가 일치하는 항목이 없을 경우 null을 반환
    }
}