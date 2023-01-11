package facade.relation.impl;

import facade.relation.InvoicesOrderFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.relation.InvoicesOrder;
import service.relation.InvoicesOrderService;
import service.relation.impl.InvoicesOrderServiceImpl;
import util.facade.DtoConverter;
import util.request.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.relation.InvoicesOrderDtoRequest;
import view.dto.response.PageData;
import view.dto.response.relation.InvoicesOrderDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class InvoicesOrderFacadeImpl implements InvoicesOrderFacade {

    private final InvoicesOrderService invoicesOrderService;

    public InvoicesOrderFacadeImpl(InvoicesOrderService invoicesOrderService) {
        this.invoicesOrderService = invoicesOrderService;
    }

    @Override
    public Long create(InvoicesOrderDtoRequest dtoReq) {
        InvoicesOrder invoicesOrder = new InvoicesOrder();
        invoicesOrderSetFields(invoicesOrder, dtoReq);
        return invoicesOrderService.create(invoicesOrder);
    }

    @Override
    public Boolean update(InvoicesOrderDtoRequest dtoReq, Long id) {
        InvoicesOrder invoicesOrder = invoicesOrderService.findById(id);
        invoicesOrderSetFields(invoicesOrder, dtoReq);
        return invoicesOrderService.update(invoicesOrder);
    }

    @Override
    public Boolean delete(Long id) {
        return invoicesOrderService.delete(id);
    }

    @Override
    public InvoicesOrderDtoResponse findById(Long id) {
        return new InvoicesOrderDtoResponse(invoicesOrderService.findById(id));
    }

    @Override
    public List<InvoicesOrderDtoResponse> findAll() {
        return invoicesOrderService.findAll().stream().map(InvoicesOrderDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public PageData<InvoicesOrderDtoResponse> findAll(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<InvoicesOrder> invoicesOrders = invoicesOrderService.findAll(dataTableRequest);
        List<InvoicesOrderDtoResponse> responseList = toDtoList(invoicesOrders);
        PageData<InvoicesOrderDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(invoicesOrders.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<InvoicesOrderDtoResponse> findAllByOrder(Long orderId) {
        return invoicesOrderService.findAllByOrder(orderId).stream().map(InvoicesOrderDtoResponse::new)
                                                                    .collect(Collectors.toList());
    }

    @Override
    public List<InvoicesOrderDtoResponse> findAllByOrderFiltered(Long orderId) {
        return invoicesOrderService.findAllByOrderFiltered(orderId).stream().map(InvoicesOrderDtoResponse::new)
                                                                            .collect(Collectors.toList());
    }

    private void invoicesOrderSetFields(InvoicesOrder invoicesOrder, InvoicesOrderDtoRequest dtoReq) {
        invoicesOrder.setFileLink(dtoReq.getFileLink());
        invoicesOrder.setOrderId(dtoReq.getOrderId());
        invoicesOrder.setEnabled(dtoReq.getEnabled());
    }

    private List<InvoicesOrderDtoResponse> toDtoList(DataTableResponse<InvoicesOrder> invoicesOrder) {
        return invoicesOrder.getItems().stream().map(InvoicesOrderDtoResponse::new).collect(Collectors.toList());
    }
}