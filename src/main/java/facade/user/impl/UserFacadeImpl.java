package facade.user.impl;

import facade.user.UserFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import persistence.entity.user.type.RoleType;
import service.user.UserService;
import service.user.impl.UserServiceImpl;
import util.facade.DtoConverter;
import util.request.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.user.*;
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
    public Long create(UserDtoRequest dtoReq) {
        BaseUser user = new BaseUser();
        userSetFields(user, dtoReq);
        return userService.create(user);
    }

    @Override
    public Boolean update(UserDtoRequest dtoReq, Long id) {
        BaseUser user = userService.findById(id);
        userSetFields(user, dtoReq);
        return userService.update(user);
    }

    @Override
    public Boolean delete(Long id) {
        return userService.delete(id);
    }

    @Override
    public UserDtoResponse findById(Long id) {
        return new UserDtoResponse(userService.findById(id));
    }

    @Override
    public List<UserDtoResponse> findAll() {
        return userService.findAll().stream().map(UserDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public PageData<UserDtoResponse> findAll(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<BaseUser> users = userService.findAll(dataTableRequest);
        List<UserDtoResponse> responseList = toDtoList(users);
        PageData<UserDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(users.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public Long register(AuthDtoRequest authDtoRequest) {
        BaseUser user = new BaseUser();
        user.setUsername(authDtoRequest.getUsername());
        user.setPassword(authDtoRequest.getPassword());
        user.setFirstName("");
        user.setLastName("");
        user.setProfilePic("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png");
        user.setBalance(new BigDecimal("0.00"));
        user.setDescription("");
        user.setEnabled(true);
        user.setRoleType(RoleType.ROLE_USER);
        return userService.create(user);
    }

    @Override
    public Boolean updateSecurityInfo(AuthDtoRequest authReq, Long id) {
        BaseUser user = userService.findById(id);
        user.setUsername(authReq.getUsername());
        user.setPassword(authReq.getPassword());
        return userService.updateSecurityInfo(user);
    }

    @Override
    public Boolean updateInfo(InfoDtoRequest infoReq, Long id) {
        BaseUser user = userService.findById(id);
        user.setFirstName(infoReq.getFirstName());
        user.setLastName(infoReq.getLastName());
        user.setProfilePic(infoReq.getProfilePic());
        user.setDescription(infoReq.getDescription());
        return userService.updateInfo(user);
    }

    @Override
    public Boolean updateBalance(BalanceDtoRequest balanceReq, Long id) {
        BaseUser user = userService.findById(id);
        user.setBalance(balanceReq.getBalance());
        return userService.updateBalance(user);
    }

    @Override
    public Boolean updateAccess(AccessDtoRequest accessReq, Long id) {
        BaseUser user = userService.findById(id);
        user.setEnabled(accessReq.getEnabled());
        return userService.updateAccess(user);
    }

    @Override
    public Boolean updateRole(RoleDtoRequest roleReq, Long id) {
        BaseUser user = userService.findById(id);
        user.setRoleType(roleReq.getRoleType());
        return userService.updateRole(user);
    }

    @Override
    public UserDtoResponse findByUsernamePassword(String username, String password) {
        return new UserDtoResponse(userService.findByUsernamePassword(username, password));
    }

    @Override
    public PageData<UserDtoResponse> findAllManagers(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<BaseUser> users = userService.findAllManagers(dataTableRequest);
        List<UserDtoResponse> responseList = toDtoList(users);
        PageData<UserDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(users.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<UserDtoResponse> findAllUsers(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<BaseUser> users = userService.findAllUsers(dataTableRequest);
        List<UserDtoResponse> responseList = toDtoList(users);
        PageData<UserDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(users.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private void userSetFields(BaseUser user, UserDtoRequest dto) {
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setProfilePic(dto.getProfilePic());
        user.setBalance(dto.getBalance());
        user.setDescription(dto.getDescription());
        user.setEnabled(dto.getEnabled());
        user.setRoleType(dto.getRoleType());
    }

    private List<UserDtoResponse> toDtoList(DataTableResponse<BaseUser> users) {
        return users.getItems().stream().map(UserDtoResponse::new).collect(Collectors.toList());
    }
}