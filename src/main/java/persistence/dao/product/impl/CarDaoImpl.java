package persistence.dao.product.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.product.CarDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;
import util.queries.interaction.OrderQueries;
import util.queries.product.CarQueries;
import util.resultsets.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarDaoImpl implements CarDao {

    private final DataSourceConnectionImpl dsc;

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public CarDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public Long create(Car car) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(car.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(car.getUpdated().getTime()));
            ps.setString(3, car.getTitle());
            ps.setString(4, car.getProductPic());
            ps.setString(5, car.getCarBrand().name());
            ps.setString(6, car.getCarQuality().name());
            ps.setString(7, car.getInfo());
            ps.setBigDecimal(8, car.getRentalPrice());
            ps.setBoolean(9, car.getEnabled());
            ps.executeUpdate();
            generatedKey = generateKeys(ps);
            connection.commit();
        } catch (SQLException createEx) {
            dsc.rollback(connection);
            return generatedKey;
        } finally {
            dsc.releaseConnection(connection);
        }

        return generatedKey;
    }

    @Override
    public Boolean update(Car car) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.UPDATE_FULL);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.setString(2, car.getTitle());
            ps.setString(3, car.getProductPic());
            ps.setString(4, car.getCarBrand().name());
            ps.setString(5, car.getCarQuality().name());
            ps.setString(6, car.getInfo());
            ps.setBigDecimal(7, car.getRentalPrice());
            ps.setBoolean(8, car.getEnabled());
            ps.setLong(9, car.getId());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException updateEx) {
            dsc.rollback(connection);
            return false;
        } finally {
            dsc.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public Boolean delete(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.DELETE);
            ps.setLong(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException onDeleteEx) {
            dsc.rollback(connection);
            return false;
        } finally {
            dsc.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public Boolean existById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_UNCOMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.EXIST);
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                count = result.getInt("count");
            }

            connection.commit();
        } catch (SQLException existEx) {
            dsc.rollback(connection);
            return false;
        } finally {
            dsc.releaseConnection(connection);
        }

        return count == 1;
    }

    @Override
    public Car findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        Car car = null;

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                car = ResultSetConverter.convertResultToCar(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return car;
    }

    @Override
    public List<Car> findAll() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Car> actions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                actions.add(ResultSetConverter.convertResultToCar(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return actions;
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Car> cars = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.findAll(request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cars.add(ResultSetConverter.convertResultToCar(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Car> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(cars);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public Integer count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.COUNT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }

            connection.commit();
        } catch (SQLException e) {
            dsc.rollback(connection);
        } finally {
            dsc.rollback(connection);
        }

        return count;
    }

    @Override
    public Boolean updateAccess(Car car) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.UPDATE_ACCESS);
            ps.setTimestamp(1, new Timestamp(car.getUpdated().getTime()));
            ps.setBoolean(2, car.getEnabled());
            ps.setLong(3, car.getId());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException updateEx) {
            dsc.rollback(connection);
            return false;
        } finally {
            dsc.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public DataTableResponse<Car> findAllFiltered(DataTableRequest req) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Car> cars = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.findAllFiltered(req));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cars.add(ResultSetConverter.convertResultToCar(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Car> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(cars);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public List<Car> findAllFiltered() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Car> actions = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(CarQueries.FIND_ALL_FILTERED);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                actions.add(ResultSetConverter.convertResultToCar(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return actions;
    }

    @Override
    public Long generateKeys(PreparedStatement ps) {
        long genKey = -1;

        try (ResultSet keys = ps.getGeneratedKeys()) {
            while (keys.next()) {
                genKey = keys.getLong(1);
            }
        } catch (SQLException sqlEx) {
            LOGGER_ERROR.error("Key(s) generation failed");
        }

        return genKey;
    }
}