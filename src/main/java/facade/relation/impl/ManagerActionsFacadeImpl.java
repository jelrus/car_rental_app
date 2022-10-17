package facade.relation.impl;

import facade.relation.ManagerActionsFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import persistence.entity.relation.ManagerActions;
import service.impl.relation.ManagerActionsService;
import service.impl.relation.impl.ManagerActionsServiceImpl;
import util.DtoConverter;
import util.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.relation.ManagerActionsDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;
import view.dto.response.relation.ManagerActionsDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerActionsFacadeImpl implements ManagerActionsFacade {

    private final ManagerActionsService managerActionsService;

    public ManagerActionsFacadeImpl() {
        this.managerActionsService = new ManagerActionsServiceImpl();
    }

    @Override
    public long create(ManagerActionsDtoRequest managerActionsDtoRequest) {
        ManagerActions managerActions = new ManagerActions();
        managerActions.setManagerId(managerActionsDtoRequest.getUserId());
        managerActions.setActionId(managerActions.getActionId());
        return managerActionsService.create(managerActions);
    }

    @Override
    public boolean update(ManagerActionsDtoRequest managerActionsDtoRequest, Long id) {
        ManagerActions managerActions = managerActionsService.findById(id);
        managerActions.setManagerId(managerActionsDtoRequest.getUserId());
        managerActions.setActionId(managerActions.getActionId());
        return managerActionsService.update(managerActions);
    }

    @Override
    public boolean delete(Long id) {
        return managerActionsService.delete(id);
    }

    @Override
    public ManagerActionsDtoResponse findById(Long id) {
        return new ManagerActionsDtoResponse(managerActionsService.findById(id));
    }

    @Override
    public PageData<ManagerActionsDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<ManagerActions> mActs = managerActionsService.findAll(dataTableRequest);
        List<ManagerActionsDtoResponse> responseList = mActsToDtoList(mActs);
        PageData<ManagerActionsDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(mActs.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<ActionDtoResponse> findActionsByManager(Long userId, HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest tableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Action> actions = managerActionsService.findActionsByManager(userId, tableRequest);
        List<ActionDtoResponse> responseList = actionsToDtoList(actions);
        PageData<ActionDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(actions.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<ActionDtoResponse> actionsToDtoList(DataTableResponse<Action> mActs) {
        return mActs.getItems().stream()
                    .map(ActionDtoResponse::new)
                    .collect(Collectors.toList());
    }


    private List<ManagerActionsDtoResponse> mActsToDtoList(DataTableResponse<ManagerActions> mActs) {
        return mActs.getItems().stream()
                               .map(ManagerActionsDtoResponse::new)
                               .collect(Collectors.toList());
    }
}