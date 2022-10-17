package facade.interaction.impl;

import facade.interaction.ActionFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Action;
import service.impl.interaction.ActionService;
import service.impl.interaction.impl.ActionServiceImpl;
import util.DtoConverter;
import util.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.interaction.ActionDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.ActionDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class ActionFacadeImpl implements ActionFacade {

    private final ActionService actionService;

    public ActionFacadeImpl() {
        this.actionService = new ActionServiceImpl();
    }

    @Override
    public long create(ActionDtoRequest actionDtoRequest) {
        Action action = new Action();
        action.setMessage(actionDtoRequest.getMessage());
        return actionService.create(action);
    }

    @Override
    public boolean update(ActionDtoRequest actionDtoRequest, Long id) {
        Action action = actionService.findById(id);
        action.setMessage(actionDtoRequest.getMessage());
        return actionService.update(action);
    }

    @Override
    public boolean delete(Long id) {
        return actionService.delete(id);
    }

    @Override
    public ActionDtoResponse findById(Long id) {
        return new ActionDtoResponse(actionService.findById(id));
    }

    @Override
    public PageData<ActionDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Action> actions = actionService.findAll(dataTableRequest);
        List<ActionDtoResponse> responseList = toDtoList(actions);
        PageData<ActionDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(actions.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<ActionDtoResponse> toDtoList(DataTableResponse<Action> actions) {
        return actions.getItems().stream()
                                 .map(ActionDtoResponse::new)
                                 .collect(Collectors.toList());
    }
}
