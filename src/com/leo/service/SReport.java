package com.leo.service;

import com.leo.CGood;
import com.leo.dao.ReportDAO;
import javafx.collections.ObservableList;

public class SReport {
    ReportDAO reportDAO = new ReportDAO();
    public SReport(){}
    public ObservableList<CGood> Report(){
        return reportDAO.reportlist();
    }
}
