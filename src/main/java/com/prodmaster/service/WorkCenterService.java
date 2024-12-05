package com.prodmaster.service;

import com.prodmaster.dao.WorkCenterDAO;
import com.prodmaster.entity.WorkCenter;

import java.util.List;

public class WorkCenterService {
    private final WorkCenterDAO workCenterDAO = new WorkCenterDAO();

    public void saveWorkCenter(WorkCenter workCenter) {
        workCenterDAO.save(workCenter);
    }

    public List<WorkCenter> getAllWorkCenters() {
        return workCenterDAO.getAll();
    }

    public void deleteWorkCenter(WorkCenter workCenter) {
        workCenterDAO.delete(workCenter);
    }

    public WorkCenter getWorkCenterById(Integer id) {
        return workCenterDAO.getById(id);
    }
}
