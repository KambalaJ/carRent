package com.intetics.carservice.kaliachka.dao.impl;

import com.intetics.carservice.kaliachka.dao.OrderDao;
import com.intetics.carservice.kaliachka.dao.exception.DaoException;
import com.intetics.carservice.kaliachka.entity.Car;
import com.intetics.carservice.kaliachka.entity.Order;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class OrderDaoImpl implements OrderDao{

    private DataSource dataSource;

    public OrderDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addOrder(Order order) throws DaoException {
        Connection con = null;
        PreparedStatement statement = null;

        try{
            con= DataSourceUtils.getConnection(dataSource);
            statement =con.prepareStatement(INSERT_ORDER);
            statement.setDate(1, new Date(order.getDate().getTime()));
            statement.setInt(2, order.getAmount());
            statement.setInt(3, order.getClient().getClientId());
            statement.setInt(4, order.getCar().getVin());
            statement.setObject(5, order.getOrderStatus());
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

    public void editOrder(Order order) throws DaoException {
        Connection con = null;
        PreparedStatement statement = null;

        try{
            con= DataSourceUtils.getConnection(dataSource);
            statement =con.prepareStatement(EDIT_ORDER);
            statement.setInt(1, order.getAmount());
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

    public List<Order> getOrdersByCar(Car car) throws DaoException {
        return null;
    }
    private static final String INSERT_ORDER = "INSERT INTO 'car_service'.'order' ('date', 'amount', 'clinet_id'," +
            " 'car_vin', 'status_order') VALUES (?, ?, ?, ?, ?)";
    private static final String EDIT_ORDER = "UPDATE 'car_service'.'order' SET 'amount' = ?";
    private static final String GET_ORDERS_BY_CAR = "";
}
