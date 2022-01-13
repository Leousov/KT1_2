package com.leo.dao;

import com.leo.CGood;
import com.leo.CUser;
import com.leo.config.CConfigHibernate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ReportDAO {
    public ObservableList<CGood> reportlist(){
        Session session = CConfigHibernate.getSessionFactory().openSession();
        Query query = session.createQuery("From CGood Where id in (Select o.Gid From COrder o Where o.Uid in (Select u.id From CUser u Where u.gender = true))");
        ArrayList<CGood> goods = (ArrayList<CGood>) query.list();
        session.close();
        ObservableList<CGood> goods1 = FXCollections.observableArrayList(goods);
        return goods1;
    }
}
