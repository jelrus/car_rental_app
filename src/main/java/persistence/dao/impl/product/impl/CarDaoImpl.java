package persistence.dao.impl.product.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.product.CarDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;
import persistence.entity.product.type.CarBrand;
import persistence.entity.product.type.CarQualityType;
import util.QueryGenerator;

import java.sql.*;
import java.util.*;

public class CarDaoImpl implements CarDao {

    private final DataSourceConnectionImpl dsc;

    public CarDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public long create(Car car) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        long generatedKey = -1;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.createQuery(Car.class),
                                                               Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(car.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(car.getUpdated().getTime()));
            ps.setString(3, car.getTitle());
            ps.setString(4, car.getProductPic());
            ps.setString(5, car.getBrand().name());
            ps.setString(6, car.getQuality().name());
            ps.setString(7, car.getInfo());
            ps.setBigDecimal(8, car.getRentalPrice());
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
    public boolean update(Car car) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.updateQuery(Car.class, "id",
                    List.of("created")));
            ps.setTimestamp(1, new Timestamp(car.getUpdated().getTime()));
            ps.setString(2, car.getTitle());
            ps.setString(3, car.getProductPic());
            ps.setString(4, car.getBrand().name());
            ps.setString(5, car.getQuality().name());
            ps.setString(6, car.getInfo());
            ps.setBigDecimal(7, car.getRentalPrice());
            ps.setLong(8, car.getId());

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
    public boolean delete(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.deleteQuery(Car.class, "id"));
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
    public boolean existById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_UNCOMMITTED);
        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.existBy(Car.class, "id"));
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findBy(Car.class, "id"));
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                car = convertResultToCar(rs);
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
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Car> cars = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findAllByRequest(Car.class, request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cars.add(convertResultToCar(rs));
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
    public long count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.count(Car.class));
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

    private long generateKeys(PreparedStatement ps) {
        long genKey = -1;

        try (ResultSet keys = ps.getGeneratedKeys()) {
            while (keys.next()) {
                genKey = keys.getLong(1);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return genKey;
    }

    public Car convertResultToCar(ResultSet rs) {
        Car car = new Car();

        try {
            car.setId(rs.getLong("id"));
            car.setCreated(rs.getTimestamp("created"));
            car.setUpdated(rs.getTimestamp("updated"));
            car.setTitle(rs.getString("title"));
            car.setProductPic(rs.getString("product_pic"));
            car.setBrand(CarBrand.valueOf(rs.getString("brand")));
            car.setQuality(CarQualityType.valueOf(rs.getString("quality")));
            car.setInfo(rs.getString("info"));
            car.setRentalPrice(rs.getBigDecimal("rental_price"));
        } catch (SQLException resEx) {
            resEx.printStackTrace();
        }

        return car;
    }
}