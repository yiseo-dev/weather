package com.zerobase.weather.repository;

import com.zerobase.weather.entity.Users;
import com.zerobase.weather.model.response.user.UserInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUserNm(String userNm);
//    Users findByUserName(String userNm);
}
