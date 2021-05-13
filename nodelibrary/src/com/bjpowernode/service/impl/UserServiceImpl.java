package com.bjpowernode.service.impl;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.Lend;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.LendDao;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.LendDaoImpl;
import com.bjpowernode.dao.impl.UserDaoImpl;
import com.bjpowernode.service.UserService;

import java.math.BigDecimal;
import java.util.List;

/**
 * �û�������
 */
public class UserServiceImpl implements UserService {
   private UserDao userDao = new UserDaoImpl();
private LendDao lendDao = new LendDaoImpl();
    /**
     * ��ѯ
     * @return
     */
    @Override
    public List<User> select() {
        //����dao��д�õķ�������

        return userDao.select();
    }

    /**
     * ����
     * @param user
     */
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    /**
     * �޸�
     * @param user
     */
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * ɾ��
     * @param id
     */
    @Override
    public void delect(int id) {
        userDao.delect(id);
    }

    /**
     * ����
     * @param id
     */
    @Override
    public void frozen(int id) {
        userDao.frozen(id);
    }

    /**
     * ��ѯ���Խ��������
     * @return
     */
    @Override
    public List<User> selectUserLend() {
        return userDao.selectUserLend();
    }

    /**
     * �û���ֵ
     * @param user
     * @param money
     * @return
     */
    @Override
    public User charge(User user, BigDecimal money) {
        //�����ֵ֮������
        BigDecimal sum = money.add(user.getMoney());
        //�жϳ�ֵ֮������Ƿ����0
        if(BigDecimal.ZERO.compareTo(sum) < 0){
            //�޸��û�״̬
            user.setStatus(Constant.USER_OK);
        }
        user.setMoney(sum);
        //�����û�
        userDao.update(user);

        //�޸Ľ����ļ��е��û�����
        List<Lend> lendList = lendDao.select(null);
        for (int i = 0; i < lendList.size(); i++) {
            Lend lend = lendList.get(i);
            if(lend.getUser().getId()  == user.getId()){
                lend.setUser(user);
                lendDao.update(lend);
                break;

            }
        }

        return user;
    }
}