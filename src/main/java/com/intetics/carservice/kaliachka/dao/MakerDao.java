package com.intetics.carservice.kaliachka.dao;

import com.intetics.carservice.kaliachka.dao.exception.DaoException;
import com.intetics.carservice.kaliachka.entity.Maker;

public interface MakerDao {
    Maker addMaker(Maker maker) throws DaoException;
    void editMaker (Maker maker) throws DaoException;
    void deleteMaker (Maker maker) throws DaoException;
}
