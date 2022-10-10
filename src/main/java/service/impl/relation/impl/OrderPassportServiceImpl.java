package service.impl.relation.impl;

import persistence.dao.impl.relation.OrderPassportDao;
import persistence.dao.impl.relation.impl.OrderPassportDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.OrderPassport;
import service.impl.relation.OrderPassportService;

public class OrderPassportServiceImpl implements OrderPassportService {

    private final OrderPassportDao orderPassportDao;

    public OrderPassportServiceImpl() {
        this.orderPassportDao = new OrderPassportDaoImpl();
    }

    @Override
    public long create(OrderPassport orderPassport) {
        return orderPassportDao.create(orderPassport);
    }

    @Override
    public boolean update(OrderPassport orderPassport) {
        return orderPassportDao.update(orderPassport);
    }

    @Override
    public boolean delete(Long id) {
        return orderPassportDao.delete(id);
    }

    @Override
    public OrderPassport findById(Long id) {
        return orderPassportDao.findById(id);
    }

    @Override
    public DataTableResponse<OrderPassport> findAll(DataTableRequest request) {
        return orderPassportDao.findAll(request);
    }
}
