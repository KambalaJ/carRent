package com.intetics.carservice.kaliachka.dao;

import com.intetics.carservice.kaliachka.dao.exception.DaoException;
import com.intetics.carservice.kaliachka.entity.Car;
import com.intetics.carservice.kaliachka.entity.Client;

import java.util.List;

public interface CarDao{
    List<Car> getCarsByClient(Client client) throws DaoException;
    void addCar(Car car) throws DaoException;
    void editCar(Car car) throws DaoException;
    void deleteCar(Car car) throws DaoException;

}
