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
        //初始化用户数据
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001,"张大虎", Constant.USER_OK, BigDecimal.TEN,false));
        initData(PathConstant.USER_PATH,userList);

        //初始化图书数据
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1,"java入门","张三",Constant.TYPE_COMPUTER,"123-1","机械工业出版社",Constant.STATUS_STORAGE));
        bookList.add(new Book(2,"java进阶","李四",Constant.TYPE_COMPUTER,"123-1","机械工业出版社",Constant.STATUS_STORAGE));
        initData(PathConstant.BOOK_PATH,bookList);

        //初始化借阅数据
        List<Lend> lendList = new ArrayList<>();
        User user = new User(1001, "张大虎", Constant.USER_OK, BigDecimal.TEN,false);
        Book book = new Book(1, "java入门", "张三", Constant.TYPE_COMPUTER, "123-1", "机械工业出版社", Constant.STATUS_STORAGE);
        Lend lend = new Lend();

            //借用UUID生产编号

        lend.setId(UUID.randomUUID().toString());


        lend.setUser(user);
        lend.setBook(book);
            //设置状态出借
        lend.setStatus(Constant.STATUS_LEND);
             //设置借出日期
        LocalDate begin = LocalDate.now();
        lend.setLendDate(begin);
            //设置归还日期
        lend.setReturnDate(begin.plusDays(30));
        lendList.add(lend);

        initData(PathConstant.LEND_PATH,lendList);


    }
    /**
     * 初始化用户数据的方法
     */
    public static void initUser(List<User> userList){
        ObjectOutputStream oos = null;
        try {
            //创建相关文件夹
            File directory = new File("user/");
            File file  = new File(PathConstant.USER_PATH);
            //判断文件夹是否存在
            if(!directory.exists()){
                directory.mkdir();
            }
            //判断文件是否存在
            if(!file.exists()){
                file.createNewFile();
             List<User> list = new ArrayList<>();
             //userList中如果没有数据的话，我们自己创建一些
                if(userList == null){
                    list.add(new User(1001,"张大虎", Constant.USER_OK, BigDecimal.TEN,false));
                }else {
                    list = userList;
                }

                //利用对象输出流将list数据写出到文件

                /*Java 提供了一种对象序列化的机制。用一个字节序列可以表示一个对象，该字节序列包含该 对象的数据 、 对象的
                类型 和 对象中存储的属性 等信息。字节序列写出到文件之后，相当于文件中持久保存了一个对象的信息。*/

                //public ObjectOutputStream(OutputStream out) ： 创建一个指定OutputStream的ObjectOutputStream。
                //java.io.ObjectOutputStream 类，将Java对象的原始数据类型写出到文件,实现对象的持久存储。
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
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
     * 初始化图书数据
     */
    public static void initBook(List<Book> bookList){
        ObjectOutputStream oos = null;
        try {
            //创建相关文件夹
            File directory = new File("book/");
            File file  = new File(PathConstant.BOOK_PATH);
            //判断文件夹是否存在
            if(!directory.exists()){
                directory.mkdir();
            }
            //判断文件是否存在
            if(!file.exists()){
                file.createNewFile();
                List<Book> list = new ArrayList<>();
                //userList中如果没有数据的话，我们自己创建一些
                if(bookList == null){
                    list.add(new Book(1,"java入门","张三",Constant.TYPE_COMPUTER,"123-1","机械工业出版社",Constant.STATUS_STORAGE));
                }else {
                    list = bookList;
                }

                //利用对象输出流将list数据写出到文件

                /*Java 提供了一种对象序列化的机制。用一个字节序列可以表示一个对象，该字节序列包含该 对象的数据 、 对象的
                类型 和 对象中存储的属性 等信息。字节序列写出到文件之后，相当于文件中持久保存了一个对象的信息。*/

                //public ObjectOutputStream(OutputStream out) ： 创建一个指定OutputStream的ObjectOutputStream。
                //java.io.ObjectOutputStream 类，将Java对象的原始数据类型写出到文件,实现对象的持久存储。
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    /**合并方法
     * 初始化数据
     */
    //设置传入路径的参数为地址
    public static void initData(String path,List<?> list){
        ObjectOutputStream oos = null;
        try {
            //创建相关文件夹
            //通过斜杠截取路径前半部分作为文件夹名称
            File directory = new File(path.split("/")[0] + "/");
            File file  = new File(path);
            //判断文件夹是否存在
            if(!directory.exists()){
                directory.mkdir();
            }
            //判断文件是否存在
            if(!file.exists()){
                file.createNewFile();


               /* List<?> list = new ArrayList<>();
                //userList中如果没有数据的话，我们自己创建一些
                if(userList == null){
                    list.add(new User(1001,"张大虎", Constant.USER_OK, BigDecimal.TEN));
                }else {
                    list = userList;
                }*/


                //利用对象输出流将list数据写出到文件

                /*Java 提供了一种对象序列化的机制。用一个字节序列可以表示一个对象，该字节序列包含该 对象的数据 、 对象的
                类型 和 对象中存储的属性 等信息。字节序列写出到文件之后，相当于文件中持久保存了一个对象的信息。*/

                //public ObjectOutputStream(OutputStream out) ： 创建一个指定OutputStream的ObjectOutputStream。
                //java.io.ObjectOutputStream 类，将Java对象的原始数据类型写出到文件,实现对象的持久存储。
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
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
