package com.bjpowernode.service;

import com.bjpowernode.bean.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    //һ����dao��ı���һ��
    List<User> select();

    void add(User user);

    void update(User user);

    void delect(int id);

    void frozen(int id);

    List<User> selectUserLend();

    User charge(User user, BigDecimal money);
}
