package facade.user.impl;

import facade.user.UserFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import persistence.entity.user.type.UserRole;
import service.impl.user.UserService;
import service.impl.user.impl.UserServiceImpl;
import util.DtoConverter;
import util.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.user.UserDtoRequest;
import view.dto.response.PageData;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public long create(UserDtoRequest userDtoRequest) {
        BaseUser user = new BaseUser();
        user.setFirstName(userDtoRequest.getFirstName());
        user.setLastName(userDtoRequest.getLastName());
        user.setProfilePic(userDtoRequest.getProfilePic());
        user.setBalance(new BigDecimal("0.00"));
        user.setDescription(userDtoRequest.getProfilePic());
        user.setEnabled(true);
        user.setRoleType(UserRole.USER);
        return userService.create(user);
    }

    @Override
    public boolean update(UserDtoRequest userDtoRequest, Long id) {
        BaseUser manager = userService.findById(id);
        manager.setFirstName(userDtoRequest.getFirstName());
        manager.setLastName(userDtoRequest.getLastName());
        manager.setProfilePic(userDtoRequest.getProfilePic());
        manager.setDescription(userDtoRequest.getProfilePic());
        return userService.update(manager);
    }

    @Override
    public boolean delete(Long id) {
        return userService.delete(id);
    }

    @Override
    public UserDtoResponse findById(Long id) {
        return new UserDtoResponse(userService.findById(id));
    }

    @Override
    public UserDtoResponse findByUsername(String username) {
        return new UserDtoResponse(userService.findByUsername(username));
    }

    @Override
    public UserDtoResponse findByUsernamePassword(String username, String password) {
        return new UserDtoResponse(userService.findByUsernamePassword(username, password));
    }

    @Override
    public PageData<UserDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<BaseUser> users = userService.findAll(dataTableRequest);
        List<UserDtoResponse> responseList = toDtoList(users);
        PageData<UserDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(users.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<UserDtoResponse> toDtoList(DataTableResponse<BaseUser> users) {
        return users.getItems().stream()
                               .map(UserDtoResponse::new)
                               .collect(Collectors.toList());
    }
}
