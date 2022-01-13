package com.leo.config;
import com.leo.CGood;
import com.leo.COrder;
import com.leo.CUser;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class CConfigHibernate {
    private static SessionFactory sessionFactory;

    private CConfigHibernate( ) {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {;
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/domoonlineshop");
                settings.put(Environment.USER, "OnlineShop");
                settings.put(Environment.PASS, "123456");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(CUser.class);
                configuration.addAnnotatedClass(CGood.class);
                configuration.addAnnotatedClass(COrder.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    public static void shutodwn(){
        getSessionFactory().close();
    }
}

