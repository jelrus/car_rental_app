package service.relation.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.relation.InvoicesOrderDao;
import persistence.dao.relation.impl.InvoicesOrderDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.InvoicesOrder;
import service.relation.InvoicesOrderService;

import java.util.List;

public class InvoicesOrderServiceImpl implements InvoicesOrderService {

    private final InvoicesOrderDao invoicesOrderDao;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public InvoicesOrderServiceImpl(InvoicesOrderDao invoicesOrderDao) {
        this.invoicesOrderDao = invoicesOrderDao;
    }

    @Override
    public Long create(InvoicesOrder invoicesOrder) {
        LOGGER_INFO.info("Relation between invoice and order created");
        return invoicesOrderDao.create(invoicesOrder);
    }

    @Override
    public Boolean update(InvoicesOrder invoicesOrder) {
        LOGGER_INFO.info("Relation " + invoicesOrder.getId() + " between invoice and order updated");
        return invoicesOrderDao.update(invoicesOrder);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER_WARNING.warn("Relation " + id + " between invoice and order deleted");
        return invoicesOrderDao.delete(id);
    }

    @Override
    public InvoicesOrder findById(Long id) {
        return invoicesOrderDao.findById(id);
    }

    @Override
    public List<InvoicesOrder> findAll() {
        return invoicesOrderDao.findAll();
    }

    @Override
    public DataTableResponse<InvoicesOrder> findAll(DataTableRequest request) {
        DataTableResponse<InvoicesOrder> invoicesOrders = invoicesOrderDao.findAll(request);
        invoicesOrders.setItemsSize(invoicesOrderDao.count());
        return invoicesOrders;
    }

    @Override
    public List<InvoicesOrder> findAllByOrder(Long orderId) {
        return invoicesOrderDao.findAllByOrder(orderId);
    }

    @Override
    public List<InvoicesOrder> findAllByOrderFiltered(Long orderId) {
        return invoicesOrderDao.findAllByOrderFiltered(orderId);
    }
}