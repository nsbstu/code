package com.bjpowernode.util;

import com.bjpowernode.bean.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InitDataUtil {
    public static void main(String[] args) {
  /*  initUser(null);
    initBook(null);*/
        //��ʼ���û�����
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001,"�Ŵ�", Constant.USER_OK, BigDecimal.TEN,false));
        initData(PathConstant.USER_PATH,userList);

        //��ʼ��ͼ������
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1,"java����","����",Constant.TYPE_COMPUTER,"123-1","��е��ҵ������",Constant.STATUS_STORAGE));
        bookList.add(new Book(2,"java����","����",Constant.TYPE_COMPUTER,"123-1","��е��ҵ������",Constant.STATUS_STORAGE));
        initData(PathConstant.BOOK_PATH,bookList);

        //��ʼ����������
        List<Lend> lendList = new ArrayList<>();
        User user = new User(1001, "�Ŵ�", Constant.USER_OK, BigDecimal.TEN,false);
        Book book = new Book(1, "java����", "����", Constant.TYPE_COMPUTER, "123-1", "��е��ҵ������", Constant.STATUS_STORAGE);
        Lend lend = new Lend();

            //����UUID�������

        lend.setId(UUID.randomUUID().toString());


        lend.setUser(user);
        lend.setBook(book);
            //����״̬����
        lend.setStatus(Constant.STATUS_LEND);
             //���ý������
        LocalDate begin = LocalDate.now();
        lend.setLendDate(begin);
            //���ù黹����
        lend.setReturnDate(begin.plusDays(30));
        lendList.add(lend);

        initData(PathConstant.LEND_PATH,lendList);


    }
    /**
     * ��ʼ���û����ݵķ���
     */
    public static void initUser(List<User> userList){
        ObjectOutputStream oos = null;
        try {
            //��������ļ���
            File directory = new File("user/");
            File file  = new File(PathConstant.USER_PATH);
            //�ж��ļ����Ƿ����
            if(!directory.exists()){
                directory.mkdir();
            }
            //�ж��ļ��Ƿ����
            if(!file.exists()){
                file.createNewFile();
             List<User> list = new ArrayList<>();
             //userList�����û�����ݵĻ��������Լ�����һЩ
                if(userList == null){
                    list.add(new User(1001,"�Ŵ�", Constant.USER_OK, BigDecimal.TEN,false));
                }else {
                    list = userList;
                }

                //���ö����������list����д�����ļ�

                /*Java �ṩ��һ�ֶ������л��Ļ��ơ���һ���ֽ����п��Ա�ʾһ�����󣬸��ֽ����а����� ��������� �� �����
                ���� �� �����д洢������ ����Ϣ���ֽ�����д�����ļ�֮���൱���ļ��г־ñ�����һ���������Ϣ��*/

                //public ObjectOutputStream(OutputStream out) �� ����һ��ָ��OutputStream��ObjectOutputStream��
                //java.io.ObjectOutputStream �࣬��Java�����ԭʼ��������д�����ļ�,ʵ�ֶ���ĳ־ô洢��
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //�ر���
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    /**
     * ��ʼ��ͼ������
     */
    public static void initBook(List<Book> bookList){
        ObjectOutputStream oos = null;
        try {
            //��������ļ���
            File directory = new File("book/");
            File file  = new File(PathConstant.BOOK_PATH);
            //�ж��ļ����Ƿ����
            if(!directory.exists()){
                directory.mkdir();
            }
            //�ж��ļ��Ƿ����
            if(!file.exists()){
                file.createNewFile();
                List<Book> list = new ArrayList<>();
                //userList�����û�����ݵĻ��������Լ�����һЩ
                if(bookList == null){
                    list.add(new Book(1,"java����","����",Constant.TYPE_COMPUTER,"123-1","��е��ҵ������",Constant.STATUS_STORAGE));
                }else {
                    list = bookList;
                }

                //���ö����������list����д�����ļ�

                /*Java �ṩ��һ�ֶ������л��Ļ��ơ���һ���ֽ����п��Ա�ʾһ�����󣬸��ֽ����а����� ��������� �� �����
                ���� �� �����д洢������ ����Ϣ���ֽ�����д�����ļ�֮���൱���ļ��г־ñ�����һ���������Ϣ��*/

                //public ObjectOutputStream(OutputStream out) �� ����һ��ָ��OutputStream��ObjectOutputStream��
                //java.io.ObjectOutputStream �࣬��Java�����ԭʼ��������д�����ļ�,ʵ�ֶ���ĳ־ô洢��
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //�ر���
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    /**�ϲ�����
     * ��ʼ������
     */
    //���ô���·���Ĳ���Ϊ��ַ
    public static void initData(String path,List<?> list){
        ObjectOutputStream oos = null;
        try {
            //��������ļ���
            //ͨ��б�ܽ�ȡ·��ǰ�벿����Ϊ�ļ�������
            File directory = new File(path.split("/")[0] + "/");
            File file  = new File(path);
            //�ж��ļ����Ƿ����
            if(!directory.exists()){
                directory.mkdir();
            }
            //�ж��ļ��Ƿ����
            if(!file.exists()){
                file.createNewFile();


               /* List<?> list = new ArrayList<>();
                //userList�����û�����ݵĻ��������Լ�����һЩ
                if(userList == null){
                    list.add(new User(1001,"�Ŵ�", Constant.USER_OK, BigDecimal.TEN));
                }else {
                    list = userList;
                }*/


                //���ö����������list����д�����ļ�

                /*Java �ṩ��һ�ֶ������л��Ļ��ơ���һ���ֽ����п��Ա�ʾһ�����󣬸��ֽ����а����� ��������� �� �����
                ���� �� �����д洢������ ����Ϣ���ֽ�����д�����ļ�֮���൱���ļ��г־ñ�����һ���������Ϣ��*/

                //public ObjectOutputStream(OutputStream out) �� ����һ��ָ��OutputStream��ObjectOutputStream��
                //java.io.ObjectOutputStream �࣬��Java�����ԭʼ��������д�����ļ�,ʵ�ֶ���ĳ־ô洢��
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //�ر���
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
