package service.relation;

import persistence.entity.relation.InvoicesOrder;
import service.BaseService;

import java.util.List;

public interface InvoicesOrderService extends BaseService<InvoicesOrder> {

    List<InvoicesOrder> findAllByOrder(Long orderId);

    List<InvoicesOrder> findAllByOrderFiltered(Long orderId);
}