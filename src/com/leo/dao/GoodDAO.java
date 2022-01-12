package com.leo.dao;

import com.leo.CGood;
import com.leo.CUser;
import com.leo.config.CConfigHibernate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GoodDAO {
    public CGood FindById(UUID id){
        return CConfigHibernate.getSessionFactory().openSession().get(CGood.class, id);
    }
    public void addgood(CGood good){
        Session session = CConfigHibernate.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(good);
        transaction1.commit();
        session.close();
    }
    public void updategood(CGood good){
        Session session = CConfigHibernate.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.update(good);
        transaction1.commit();
        session.close();
    }
    public void deletegood(CGood good){
        Session session = CConfigHibernate.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.delete(good);
        transaction1.commit();
        session.close();
    }
    public ObservableList<CGood> findall() {
        ArrayList<CGood> goods = (ArrayList<CGood>) CConfigHibernate.getSessionFactory().openSession().createQuery("FROM CGood").list();
        ObservableList<CGood> goods1 = FXCollections.observableArrayList(goods);
        return goods1;
    }
}
