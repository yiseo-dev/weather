package com.zerobase.weather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "USER_NM")
    private String userNm;
    @Column(name = "USER_PW")
    private String userPw;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "PROVIDER")
    private String provider;
    @Column(name = "PROVIDER_USER_ID")
    private String providerUserId;
    @Column(name = "LOC_ID")
    private long locId;
}
