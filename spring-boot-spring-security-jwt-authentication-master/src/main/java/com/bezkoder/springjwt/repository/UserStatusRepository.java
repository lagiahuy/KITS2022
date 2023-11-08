package com.bezkoder.springjwt.repository;


import com.bezkoder.springjwt.models.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    @Query("select ub from UserStatus ub where ub.status = false and ub.timeupdate < current_date")
    List<UserStatus> listUserStatus();

}
