package com.intetics.carservice.kaliachka.dao.impl;

import com.intetics.carservice.kaliachka.dao.ClientDao;
import com.intetics.carservice.kaliachka.dao.exception.DaoException;
import com.intetics.carservice.kaliachka.entity.Client;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

    private DataSource dataSource;

    public ClientDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Client> getClient(String name, String surname) throws DaoException {
        Connection con = null;
        PreparedStatement statement = null;
        List<Client> listClient= new ArrayList<Client>();
        ResultSet resultSet;
        try {
            con = DataSourceUtils.getConnection(dataSource);
            statement = con.prepareStatement(GET_CLIENT_BY_CLIENT);
            statement.setString(1, name);
            statement.setString(2, surname);

            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Client client = new Client();
                client.setFirstName(resultSet.getString(2));
                client.setLastName(resultSet.getString(3));
                client.setDateOfBirth(resultSet.getDate(4));
                client.setAddress(resultSet.getString(5));
                client.setEmail(resultSet.getString(6));
                client.setPhone(resultSet.getString(7));
                listClient.add(client);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    DataSourceUtils.releaseConnection(con, dataSource);
                }
            } catch (SQLException ex) {
                //todo Log
            }

        }
        return listClient;
    }

    public void addClient(Client client) throws DaoException {
        Connection con = null;
        PreparedStatement statement = null;

        try{
            con= DataSourceUtils.getConnection(dataSource);
            statement =con.prepareStatement(INSERT_CLIENT);
            statement.setString(1, client.getFirstName());
            statement.setString(2,client.getLastName());
            statement.setDate(3, new Date(client.getDateOfBirth().getTime()));
            statement.setString(4, client.getAddress());
            statement.setString(5, client.getPhone());
            statement.setString(6, client.getEmail());
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException(ex);
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if(con != null){
                    DataSourceUtils.releaseConnection(con,dataSource);
                }
            }catch (SQLException ex){
                //todo Log
            }
        }
    }

    public void editClient(Client client) throws DaoException {
        Connection con = null;
        PreparedStatement statement = null;

        try{
            con= DataSourceUtils.getConnection(dataSource);
            statement =con.prepareStatement(EDIT_CLIENT);
            statement.setDate(1, new Date(client.getDateOfBirth().getTime()));
            statement.executeUpdate();
        }
        catch (SQLException ex){
            throw new DaoException(ex);
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if(con != null){
                    DataSourceUtils.releaseConnection(con,dataSource);
                }
            }catch (SQLException ex){
                //todo Log
            }

        }
    }
    private static final String EDIT_CLIENT = "UPDATE 'car_service'.'client' SET 'date_of_birth' = ?";
    private static final String INSERT_CLIENT ="INSERT INTO `car_service`.`client` ('first_name', 'last_name'," +
            " 'date_of_birth', 'address', 'phone', 'email') VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_CLIENT_BY_CLIENT = "SELECT client.id_client, client.first_name, client.last_name, " +
            "client.date_of_birth, client.address, client.phone,\n" + "  client.email FROM client WHERE first_name = ?" +
            " AND last_name = ?";
}
