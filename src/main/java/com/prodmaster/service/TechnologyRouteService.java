package com.prodmaster.service;

import com.prodmaster.dao.TechnologyRouteDAO;
import com.prodmaster.entity.TechnologyRoute;

import java.util.List;

public class TechnologyRouteService {
    private final TechnologyRouteDAO technologyRouteDAO = new TechnologyRouteDAO();

    public void saveTechnologyRoute(TechnologyRoute technologyRoute) {
        technologyRouteDAO.save(technologyRoute);
    }

    public List<TechnologyRoute> getAllTechnologyRoutes() {
        return technologyRouteDAO.getAll();
    }

    public void deleteTechnologyRoute(TechnologyRoute technologyRoute) {
        technologyRouteDAO.delete(technologyRoute);
    }

    public TechnologyRoute getTechnologyRouteById(Integer id) {
        return technologyRouteDAO.getById(id);
    }
}
