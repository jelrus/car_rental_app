package facade.interaction.impl;

import facade.interaction.ActionFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import service.interaction.ActionService;
import service.interaction.impl.ActionServiceImpl;
import util.facade.DtoConverter;
import util.request.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.interaction.ActionDtoRequest;
import view.dto.request.interaction.MessageDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class ActionFacadeImpl implements ActionFacade {

    private final ActionService actionService;

    public ActionFacadeImpl(ActionService actionService) {
        this.actionService = actionService;
    }

    @Override
    public Long create(ActionDtoRequest dtoReq) {
        Action action = new Action();
        actionSetFields(action, dtoReq);
        return actionService.create(action);
    }

    @Override
    public Boolean update(ActionDtoRequest dtoReq, Long id) {
        Action action = actionService.findById(id);
        actionSetFields(action, dtoReq);
        return actionService.update(action);
    }

    @Override
    public Boolean delete(Long id) {
        return actionService.delete(id);
    }

    @Override
    public ActionDtoResponse findById(Long id) {
        return new ActionDtoResponse(actionService.findById(id));
    }

    @Override
    public List<ActionDtoResponse> findAll() {
        return actionService.findAll().stream().map(ActionDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public PageData<ActionDtoResponse> findAll(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Action> actions = actionService.findAll(dataTableRequest);
        List<ActionDtoResponse> responseList = toDtoList(actions);
        PageData<ActionDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(actions.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public Boolean updateMessage(MessageDtoRequest dtoReq, Long id) {
        Action action = actionService.findById(id);
        action.setMessage(dtoReq.getMessage());
        return actionService.updateMessage(action);
    }

    @Override
    public Boolean updateAccess(ActionDtoRequest dtoReq, Long id) {
        Action action = actionService.findById(id);
        actionSetFields(action, dtoReq);
        return actionService.updateAccess(action);
    }

    @Override
    public PageData<ActionDtoResponse> findAllFiltered(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Action> actions = actionService.findAllFiltered(dataTableRequest);
        List<ActionDtoResponse> responseList = toDtoList(actions);
        PageData<ActionDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(actions.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private void actionSetFields(Action action, ActionDtoRequest dto) {
        action.setIdentifier(dto.getIdentifier());
        action.setMessage(dto.getMessage());
        action.setEnabled(dto.getEnabled());
    }

    private List<ActionDtoResponse> toDtoList(DataTableResponse<Action> actions) {
        return actions.getItems().stream().map(ActionDtoResponse::new).collect(Collectors.toList());
    }
}