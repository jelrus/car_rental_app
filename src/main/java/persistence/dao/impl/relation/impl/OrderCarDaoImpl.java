package persistence.dao.impl.relation.impl;

import config.datasource.impl.DataSourceConnectionImpl;
import persistence.dao.impl.relation.OrderCarDao;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.product.Car;
import persistence.entity.product.type.CarBrand;
import persistence.entity.product.type.CarQualityType;
import persistence.entity.relation.OrderCar;
import util.QueryGenerator;

import java.sql.*;
import java.util.*;

public class OrderCarDaoImpl implements OrderCarDao {

    private final DataSourceConnectionImpl dsc;

    public OrderCarDaoImpl() {
        this.dsc = DataSourceConnectionImpl.getInstance();
    }

    @Override
    public boolean create(OrderCar orderCar) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.createQuery(OrderCar.class));
            ps.setTimestamp(1, new Timestamp(orderCar.getCreated().getTime()));
            ps.setTimestamp(2, new Timestamp(orderCar.getUpdated().getTime()));
            ps.setLong(3, orderCar.getOrderId());
            ps.setLong(4, orderCar.getCarId());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException createEx) {
            dsc.rollback(connection);
            return false;
        } finally {
            dsc.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public boolean update(OrderCar orderCar) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_REPEATABLE_READ);

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.updateQuery(OrderCar.class,
                    "id", List.of("created")));
            ps.setTimestamp(1, new Timestamp(orderCar.getUpdated().getTime()));
            ps.setLong(2, orderCar.getOrderId());
            ps.setLong(3, orderCar.getCarId());
            ps.setLong(4, orderCar.getId());

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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.deleteQuery(OrderCar.class,
                    "id"));
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
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.existBy(OrderCar.class, "id"));
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
    public OrderCar findById(Long id) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        OrderCar orderCar = null;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findBy(OrderCar.class, "id"));
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderCar = convertResultToOrderCar(rs);
            }

            connection.commit();
        } catch (SQLException findByEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        return orderCar;
    }

    @Override
    public DataTableResponse<OrderCar> findAll(DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<OrderCar> orderCar = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findAllByRequest(OrderCar.class, request));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderCar.add(convertResultToOrderCar(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<OrderCar> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(orderCar);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    @Override
    public long count() {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        int count = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.count(OrderCar.class));
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
    public DataTableResponse<Car> findCarByOrder(Long orderId, DataTableRequest request) {
        Connection connection = dsc.getConnection();
        dsc.setupConnection(connection, Connection.TRANSACTION_READ_COMMITTED);

        List<Car> car = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        try {
            PreparedStatement ps = connection.prepareStatement(QueryGenerator.findByRelation(OrderCar.class,
                    Car.class,
                    "c",
                    "order_id",
                    Collections.emptyList(),
                    request));
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                car.add(convertResultToCar(rs));
            }

            connection.commit();
        } catch (SQLException findAllEx) {
            dsc.rollback(connection);
        } finally {
            dsc.releaseConnection(connection);
        }

        DataTableResponse<Car> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(car);
        tableResponse.setOtherParamMap(otherParamMap);

        return tableResponse;
    }

    private OrderCar convertResultToOrderCar(ResultSet rs) {
        OrderCar orderActions = new OrderCar();

        try {
            orderActions.setId(rs.getLong("id"));
            orderActions.setCreated(rs.getTimestamp("created"));
            orderActions.setUpdated(rs.getTimestamp("updated"));
            orderActions.setOrderId(rs.getLong("order_id"));
            orderActions.setCarId(rs.getLong("car_id"));
        } catch (SQLException resEx) {
            //TODO: log error
        }

        return orderActions;
    }

    private Car convertResultToCar(ResultSet rs) {
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
            //TODO: log error
        }

        return car;
    }
}