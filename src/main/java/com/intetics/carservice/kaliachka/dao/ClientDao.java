package com.intetics.carservice.kaliachka.dao;

import com.intetics.carservice.kaliachka.dao.exception.DaoException;
import com.intetics.carservice.kaliachka.entity.Client;

import java.util.List;


public interface ClientDao {
    List<Client> getClient(String name, String surname) throws DaoException;
    void addClient(Client client) throws DaoException;
    void editClient(Client client) throws DaoException;
}
