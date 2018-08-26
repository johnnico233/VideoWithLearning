package com.yosoro.video.dao;

import com.yosoro.video.domain.user.PrivateUser;
import com.yosoro.video.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper {
    int checkUserExist(String id);
    void addNewUser(Map<String,Object> map);
    User checkLoginValid(PrivateUser user);
    User getUserById(int id);
}
