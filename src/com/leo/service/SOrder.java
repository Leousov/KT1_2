package com.leo.service;
import com.leo.COrder;
import com.leo.dao.OrderDAO;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.UUID;

public class SOrder {
    private OrderDAO orderDAO = new OrderDAO();

    public SOrder(){}

    public COrder findbyid(UUID id){
        return orderDAO.FindById(id);
    }
    public void AddUser(COrder user){
        orderDAO.addorder(user);
    }
    public void UpdateOrder(COrder order){
        orderDAO.updateorder(order);
    }
    public void DeleteOrder(COrder order){
        orderDAO.deleteorder(order);
    }
    public ObservableList<COrder> findAllUsers(){
        return orderDAO.findall();
    }
}
