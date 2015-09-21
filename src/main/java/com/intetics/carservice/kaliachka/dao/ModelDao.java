package com.intetics.carservice.kaliachka.dao;

import com.intetics.carservice.kaliachka.dao.exception.DaoException;
import com.intetics.carservice.kaliachka.entity.Model;


public interface ModelDao {
    Model addModel(Model model) throws DaoException;
    void editModel(Model model) throws DaoException;
    void deleteModel(Model model) throws DaoException;
}
