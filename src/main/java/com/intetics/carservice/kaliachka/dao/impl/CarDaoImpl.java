package com.intetics.carservice.kaliachka.dao.impl;

import com.intetics.carservice.kaliachka.dao.CarDao;
import com.intetics.carservice.kaliachka.dao.exception.DaoException;
import com.intetics.carservice.kaliachka.entity.Car;
import com.intetics.carservice.kaliachka.entity.Client;
import com.intetics.carservice.kaliachka.entity.Maker;
import com.intetics.carservice.kaliachka.entity.Model;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao{

    private DataSource dataSource;

    public CarDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Car> getCarsByClient(Client client) throws DaoException {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        List<Car> listCar = new ArrayList<Car>();

        try {
            con = DataSourceUtils.getConnection(dataSource);
            statement = con.prepareStatement(GET_CAR_BY_CLIENT);
            statement.setInt(1, client.getClientId());
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                resultSet.getString("model");
                Car car = new Car();
                Model model = new Model();
                model.setName(resultSet.getString("model"));
                Maker maker = new Maker();
                maker.setName(resultSet.getString("maker"));
                listCar.add(car);
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
        return listCar;
    }

    public void addCar(Car car) throws DaoException {
        Connection con = null;
        PreparedStatement statement = null;

        try{
            con= DataSourceUtils.getConnection(dataSource);
            statement =con.prepareStatement(INSERT_CAR);
            statement.setInt(1,car.getVin());
            statement.setDate(2, new Date(car.getYear().getTime()));
            statement.setInt(3, car.getModel().getModelId());
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

    public void editCar(Car car) throws DaoException {
        Connection con = null;
        PreparedStatement statement = null;

        try{
            con= DataSourceUtils.getConnection(dataSource);
            statement =con.prepareStatement(EDIT_CAR);
            statement.setDate(1, new Date(car.getYear().getTime()));
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

    public void deleteCar(Car car) throws DaoException {
        Connection con = null;
        PreparedStatement statement = null;

        try{
            con= DataSourceUtils.getConnection(dataSource);
            statement =con.prepareStatement(DELETE_CAR);
            statement.setInt(1,car.getVin());
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

    private static final String INSERT_CAR ="INSERT INTO `car_service`.`car` (`vin`, `year`, `model_id`) VALUES (?, ?, ?)";
    private static final String DELETE_CAR = "DELETE FROM 'car_service'.'car' WHERE 'vin' = ?";
    private static final String EDIT_CAR = "UPDATE 'car_service'.'car' SET 'year' = ?";
    private static final String GET_CAR_BY_CLIENT = "SELECT car.vin, car.year, model.name as model,maker.name as marker\n" +
            "FROM car\n" +
            "INNER JOIN model ON model.id_model = car.model_id\n" +
            "INNER JOIN maker ON maker.id_maker = model.maker_id_maker\n" +
            "INNER JOIN `order` ON car.vin = `order`.car_vin\n" +
            "INNER JOIN client ON client.id_client = `order`.client_id\n" +
            "WHERE client_id= ?;";
}


