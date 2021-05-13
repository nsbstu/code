package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.util.BeanUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * �û�Dao��
 */
public class UserDaoImpl implements UserDao {
    /**
     * ��Ӳ���ļ��ж�ȡ����
     *
     * @return
     */
    @Override
    public List<User> select() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH))) {
            List<User> list = (List<User>) ois.readObject();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        //�������������쳣���򷵻�һ��List����
        return new ArrayList<>();
    }

    /**
     * ������ѯ
     * @param user
     * @return
     */
    @Override
    public List<User> select(User user) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH))) {
            List<User> list = (List<User>) ois.readObject();
            return   list.stream().filter(u -> u.getId() == user.getId()).collect(Collectors.toList());


        } catch (Exception e) {
            e.printStackTrace();
        }
        //�������������쳣���򷵻�һ��List����
        return new ArrayList<>();
    }


    /**
     * ��Ӳ���
     *
     * @param user
     */
    @Override
    public void add(User user) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            //��ȡ�ļ��е�List����
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>) ois.readObject();
            if (list != null) {

                //��ȡlist������User����
                User lastUser = list.get(list.size() - 1);
                //�����û����
                user.setId(lastUser.getId() + 1);

                //��user������뵽List�У�Ȼ��listд�����ļ���
                list.add(user);
            } else {
                list = new ArrayList<>();
                user.setId(1001);
                list.add(user);
            }
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
            oos.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {

            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * �޸�����
     *
     * @param user
     */
    @Override
    public void update(User user) {
        //��list�����ݴ��ļ��в����
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>) ois.readObject();
            if (list != null) {
                //��list�в���Ҫ�޸ĵ�����
                User originUser = list.stream().filter(u -> u.getId() == user.getId()).findFirst().get();
                //�޸�����
                BeanUtil.populate(originUser,user);
                //�����ݳ־û����ļ���
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {

            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * �û�ɾ��
     *
     * @param id
     */

    @Override
    public void delect(int id) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            //��ȡ����
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>) ois.readObject();

            //ʹ��stream������
            User user = list.stream().filter(u -> u.getId() == id).findFirst().get();

            //��list�н���userɾ��
            list.remove(user);

            //��listд�����ļ���
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
            oos.writeObject(list);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ɾ���û�ʧ�ܣ�����ϵ����Ա");//�׵�������

        } finally {

            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * ����
     *
     * @param id
     */

    @Override
    public void frozen(int id) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list =(List<User>) ois.readObject();
            //����stream��
            User user = list.stream().filter(u -> u.getId() == id).findFirst().get();
            //��״̬�޸ĳɶ���
            user.setStatus(Constant.USER_FROZEN);

            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
            oos.writeObject(list);


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {

            try {
                if (ois != null) {
                    ois.close();
                }
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    /**
     * ��ѯ���Խ�����û�
     * @return
     */

    @Override
    public List<User> selectUserLend() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH))) {

            List<User> list  = (List<User>)ois.readObject();
            if(list != null){
                //��ѯ���û�״̬������������isLEND��false
                List<User> collect = list.stream().filter(u -> Constant.USER_OK.equals(u.getStatus()) && false == u.isLend()).collect(Collectors.toList());
                return collect;


            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return new ArrayList<>();
    }
}

