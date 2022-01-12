package com.leo.service;
import com.leo.CGood;
import com.leo.dao.GoodDAO;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.UUID;

public class SGood {
    private GoodDAO userDAO = new GoodDAO();

    public SGood(){}

    public CGood findbyid(UUID id){
        return userDAO.FindById(id);
    }
    public void AddUser(CGood user){
        userDAO.addgood(user);
    }
    public void UpdateUser(CGood user){
        userDAO.updategood(user);
    }
    public void DeleteUser(CGood user){
        userDAO.deletegood(user);
    }
    public ObservableList<CGood> findAllUsers(){
        return userDAO.findall();
    }
}
