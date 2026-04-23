package com.example.ss14.repository;

import com.example.ss14.model.Account;
import com.example.ss14.model.Transfer;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Target;
import java.time.LocalDateTime;

@Repository
@Transactional
@RequiredArgsConstructor
public class transferRepository implements ITransferRepository{
    private final SessionFactory sessionFactory;

    @Override
    public void banking(Long senderId, Long receiverId, Double amount) {
        //1. Mở sesion
        Session session = sessionFactory.getCurrentSession();

        //2. tìm ra đối tượng sender và receiver
        Account sender = session.get(Account.class, senderId);
        Account receiver = session.get(Account.class, receiverId);
        //3. kiểm tra xem có đủ điền hay k
        if(amount < 0){
            System.err.println("Số tiền chuyển không hợp lệ.");
//            tx.rollback();
        }
        if(sender.getBanlance() < amount){
            System.err.println("Số dư không đủ");
//            tx.rollback();
        }
        //4. Cập nhật tiền trong tài khoản
        sender.setBanlance(sender.getBanlance() - amount);
        receiver.setBanlance(receiver.getBanlance() + amount);
        //5. lưu database
        session.merge(sender);
        session.merge(receiver);

        // tạo lịch sử giao dịch
        Transfer transfer = new Transfer();
        transfer.setSender(sender);
        transfer.setReceiver(receiver);
        transfer.setAmount(amount);
        transfer.setTransferDate(LocalDateTime.now());
        session.persist(transfer);
        //6. Áp dụng transaction
//        tx.commit();
//        session.close();
    }

}
