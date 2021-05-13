package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Lend;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.LendDao;
import com.bjpowernode.util.BeanUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LendDaoImpl implements LendDao {
    /**
     * ��ѯ
     * @param lend
     * @return
     */
    @Override
    public List<Lend> select(Lend lend) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));) {

            List<Lend> list = (List<Lend>)ois.readObject();
            if(lend == null || "".equals(lend.getId())){
                return list;
            }

        }catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException();
        }

        return new ArrayList<>();
    }

    /**
     * ���
     * @param lend
     */

    @Override
    public void add(Lend lend) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            List<Lend> list = (List<Lend>) ois.readObject();
            list.add(lend);
                //д��
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
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
     * ����
     * @param id
     */
 @Override
    public void delete(String id) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            List<Lend> list =(List<Lend>)ois.readObject();
            if(list != null){
                //�Ӽ����в���Ҫɾ����ͼ������
               Lend originLend = list.stream().filter(r -> Objects.equals(id,r.getId())).findFirst().get();
                list.remove(originLend);
                //��ͼ������д����Ӳ����
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
                oos.writeObject(list);
            }


        } catch (Exception e) {
            e.printStackTrace();
            //���ϲ��׳��쳣��Ϣ
            throw new RuntimeException("ɾ���������ݳ�����");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if(ois != null){
                    ois.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * �޸�
     * @param
     */
   @Override
    public void update(Lend lend) {
       //��list���ݴ��ļ��в����
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            List<Lend> list =(List<Lend>)ois.readObject();
            if(list != null){
                //�Ӽ����в���Ҫ�޸ĵ�ͼ������
                Lend originLend = list.stream().filter(u -> u.getId().equals(lend.getId())).findFirst().get();

                BeanUtil.populate(originLend,lend);
                //��ͼ������д����Ӳ����
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
                oos.writeObject(list);
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if(ois != null){
                    ois.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
