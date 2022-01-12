package com.leo.dao;

import com.leo.COrder;
import com.leo.config.CConfigHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.UUID;

public class OrderDAO {
    public COrder FindById(UUID id){
        return CConfigHibernate.getSessionFactory().openSession().get(COrder.class, id);
    }
    public void addorder(COrder order){
        Session session = CConfigHibernate.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(order);
        transaction1.commit();
        session.close();
    }
    public void updateorder(COrder order){
        Session session = CConfigHibernate.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.update(order);
        transaction1.commit();
        session.close();
    }
    public void deleteorder(COrder order){
        Session session = CConfigHibernate.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.delete(order);
        transaction1.commit();
        session.close();
    }
    public List<COrder> findall(){
        List<COrder> order = (List<COrder>) CConfigHibernate.getSessionFactory().openSession().createQuery("From Order").list();
        return order;
    }
}
