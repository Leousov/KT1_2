package com.leo.service;
import com.leo.CUser;
import com.leo.dao.UserDAO;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.UUID;

public class SUser {
    private UserDAO userDAO = new UserDAO();

    public SUser(){}

    public CUser findbyid(UUID id){
        return userDAO.FindById(id);
    }
    public void AddUser(CUser user){
        userDAO.adduser(user);
    }
    public void UpdateUser(CUser user){
        userDAO.updateuser(user);
    }
    public void DeleteUser(CUser user){
        userDAO.deleteuser(user);
    }
    public ObservableList<CUser> findAllUsers(){
        return userDAO.findall();
    }
}
