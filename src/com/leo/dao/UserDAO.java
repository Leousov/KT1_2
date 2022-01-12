package com.leo.dao;
import com.leo.CUser;
import com.leo.config.CConfigHibernate;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO {
    public CUser FindById(UUID id){
        return CConfigHibernate.getSessionFactory().openSession().get(CUser.class, id);
    }
    public void adduser(CUser user){
        Session session = CConfigHibernate.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(user);
        transaction1.commit();
        session.close();
    }
    public void updateuser(CUser user){
        Session session = CConfigHibernate.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.update(user);
        transaction1.commit();
        session.close();
    }
    public void deleteuser(CUser user){
        Session session = CConfigHibernate.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.delete(user);
        transaction1.commit();
        session.close();
    }
    public ObservableList<CUser> findall(){
        ArrayList<CUser> users = (ArrayList<CUser>) CConfigHibernate.getSessionFactory().openSession().createQuery("FROM CUser").list();
        ObservableList<CUser> users1 = FXCollections.observableArrayList(users);
        return users1;
    }
}
