package facade.relation.impl;

import facade.relation.ManagerActionsFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.ManagerActions;
import service.relation.ManagerActionsService;
import service.relation.impl.ManagerActionsServiceImpl;
import util.facade.DtoConverter;
import util.request.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.relation.ManagerActionsDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.relation.ManagerActionsDtoResponse;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerActionsFacadeImpl implements ManagerActionsFacade {

    private final ManagerActionsService managerActionsService;

    public ManagerActionsFacadeImpl() {
        this.managerActionsService = new ManagerActionsServiceImpl();
    }

    @Override
    public Long create(ManagerActionsDtoRequest dtoReq) {
        ManagerActions managerActions = new ManagerActions();
        managerActionSetFields(managerActions, dtoReq);
        return managerActionsService.create(managerActions);
    }

    @Override
    public Boolean update(ManagerActionsDtoRequest dtoReq, Long id) {
        ManagerActions managerActions = managerActionsService.findById(id);
        managerActionSetFields(managerActions, dtoReq);
        return managerActionsService.update(managerActions);
    }

    @Override
    public Boolean delete(Long id) {
        return managerActionsService.delete(id);
    }

    @Override
    public ManagerActionsDtoResponse findById(Long id) {
        return new ManagerActionsDtoResponse(managerActionsService.findById(id));
    }

    @Override
    public List<ManagerActionsDtoResponse> findAll() {
        return managerActionsService.findAll().stream().map(ManagerActionsDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public PageData<ManagerActionsDtoResponse> findAll(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<ManagerActions> managerActions = managerActionsService.findAll(dataTableRequest);
        List<ManagerActionsDtoResponse> responseList = toDtoList(managerActions);
        PageData<ManagerActionsDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(managerActions.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<ActionDtoResponse> findActionsByManager(Long managerId) {
        return managerActionsService.findActionsByManager(managerId).stream().map(ActionDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public UserDtoResponse findManagerByAction(Long actionId) {
        return new UserDtoResponse(managerActionsService.findManagerByAction(actionId));
    }

    @Override
    public PageData<ActionDtoResponse> findActionsByManager(Long managerId, HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Action> actions = managerActionsService.findActionsByManager(managerId, dataTableRequest);
        List<ActionDtoResponse> responseList = toActionsDtoList(actions);
        PageData<ActionDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(actions.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private void managerActionSetFields(ManagerActions managerActions, ManagerActionsDtoRequest dto) {
        managerActions.setUserId(dto.getUserId());
        managerActions.setActionId(dto.getActionId());
    }

    private List<ManagerActionsDtoResponse> toDtoList(DataTableResponse<ManagerActions> managerActions) {
        return managerActions.getItems().stream().map(ManagerActionsDtoResponse::new).collect(Collectors.toList());
    }

    private List<ActionDtoResponse> toActionsDtoList(DataTableResponse<Action> actions) {
        return actions.getItems().stream().map(ActionDtoResponse::new).collect(Collectors.toList());
    }
}