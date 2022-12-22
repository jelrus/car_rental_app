package persistence.dao.relation;

import persistence.dao.BaseDao;
import persistence.entity.relation.InvoicesOrder;

import java.util.List;

public interface InvoicesOrderDao extends BaseDao<InvoicesOrder> {

    List<InvoicesOrder> findAllByOrder(Long orderId);

    List<InvoicesOrder> findAllByOrderFiltered(Long orderId);
}