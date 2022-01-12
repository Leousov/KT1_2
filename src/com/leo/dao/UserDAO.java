package com.leo.dao;
import com.leo.CUser;
import com.leo.config.CConfigHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
    public List<CUser> findall(){
        List<CUser> users = (List<CUser>) CConfigHibernate.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }
}
