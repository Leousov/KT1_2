package com.leo.dao;

import com.leo.COrder;
import com.leo.CUser;
import com.leo.config.CConfigHibernate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
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
    public ObservableList<COrder> findall() {
        ArrayList<COrder> orders = (ArrayList<COrder>) CConfigHibernate.getSessionFactory().openSession().createQuery("FROM COrder").list();
        ObservableList<COrder> orders1 = FXCollections.observableArrayList(orders);
        return orders1;
    }
}
