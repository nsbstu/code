package com.bjpowernode.service.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.Lend;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.BookDao;
import com.bjpowernode.dao.LendDao;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.BookDaoImpl;
import com.bjpowernode.dao.impl.LendDaoImpl;
import com.bjpowernode.dao.impl.UserDaoImpl;
import com.bjpowernode.module.lend.LendHandleViewCtrl;
import com.bjpowernode.service.LendService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class LendServiceImpl implements LendService {
   private LendDao lendDao = new LendDaoImpl();
   private BookDao bookDao = new BookDaoImpl();
   private UserDao userDao = new UserDaoImpl();

    @Override
    public List<Lend> select(Lend lend) {
        return lendDao.select(lend);
    }

    /**
     * ��ӽ�������
     * @param bookId
     * @param userId
     */
    @Override
    public void add(int bookId, int userId) {
        //��ѯͼ������
        Book paramBook = new Book();
        paramBook.setId(bookId);
        List<Book> bookList = bookDao.select(paramBook);

        //���ݱ�Ų�ѯ�û�����
        User paramUser = new User();
        paramUser.setId(userId);
        List<User> userList = userDao.select(paramUser);

        Lend lend = new Lend();
        //���ɱ��
        lend.setId(UUID.randomUUID().toString());

        //��ͼ�����ֵ
        Book book = bookList.get(0);
        book.setStatus(Constant.STATUS_LEND);//��״̬��Ϊ����
        lend.setBook(book);

        //���û���ֵ
        User user = userList.get(0);
        user.setLend(true);//�޸Ľ���״̬
        lend.setUser(user);

        lend.setStatus(Constant.STATUS_LEND);

        //���ó������ں͹黹����
        LocalDate begin = LocalDate.now();
        lend.setLendDate(begin);
        lend.setReturnDate(begin.plusDays(30));

        //�޸�ͼ��
        bookDao.update(book);
        //�޸��û�
        userDao.update(user);
        //��ӳ����¼
        lendDao.add(lend);
    }

    /**
     * ����
     * @param lend
     * @return
     */
    @Override
    public List<Lend> returnBook(Lend lend) {
        //��ȡ�û������ͼ�����
        Book book = lend.getBook();
        User user = lend.getUser();

        //�޸�״̬
        book.setStatus(Constant.STATUS_STORAGE);
        user.setLend(false);

        userDao.update(user);
        bookDao.update(book);

        //ɾ��Lend����
        lendDao.delete(lend.getId());

        //��ѯ(���ñ����ϱߵ�select������
      //  select(null);
        //����dao���select

        return lendDao.select(null);
    }

    @Override
    public void update(Lend lend) {
        lendDao.update(lend);
    }
}
