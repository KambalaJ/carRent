package com.intetics.carservice.kaliachka.dao;

import com.intetics.carservice.kaliachka.dao.exception.DaoException;
import com.intetics.carservice.kaliachka.entity.Car;
import com.intetics.carservice.kaliachka.entity.Order;

import java.util.List;


public interface OrderDao {
    void addOrder (Order order) throws DaoException;
    void editOrder (Order order) throws DaoException;
    List<Order> getOrdersByCar (Car car) throws DaoException;
}
