package facade.user.impl;

import facade.user.AdminFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.user.BaseUser;
import persistence.entity.user.type.UserRole;
import service.impl.user.AdminService;
import service.impl.user.impl.AdminServiceImpl;
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

public class AdminFacadeImpl implements AdminFacade {

    private final AdminService adminService;

    public AdminFacadeImpl() {
        this.adminService = new AdminServiceImpl();
    }

    @Override
    public long create(UserDtoRequest userDtoRequest) {
        BaseUser admin = new BaseUser();
        admin.setFirstName(userDtoRequest.getFirstName());
        admin.setLastName(userDtoRequest.getLastName());
        admin.setProfilePic(userDtoRequest.getProfilePic());
        admin.setBalance(new BigDecimal("0.00"));
        admin.setDescription(userDtoRequest.getProfilePic());
        admin.setEnabled(true);
        admin.setRoleType(UserRole.ADMIN);
        return adminService.create(admin);
    }

    @Override
    public boolean update(UserDtoRequest userDtoRequest, Long id) {
        BaseUser admin = adminService.findById(id);
        admin.setFirstName(userDtoRequest.getFirstName());
        admin.setLastName(userDtoRequest.getLastName());
        admin.setProfilePic(userDtoRequest.getProfilePic());
        admin.setDescription(userDtoRequest.getProfilePic());
        return adminService.update(admin);
    }

    @Override
    public boolean delete(Long id) {
        return adminService.delete(id);
    }

    @Override
    public UserDtoResponse findById(Long id) {
        return new UserDtoResponse(adminService.findById(id));
    }

    @Override
    public UserDtoResponse findByUsername(String username) {
        return new UserDtoResponse(adminService.findByUsername(username));
    }

    @Override
    public UserDtoResponse findByUsernamePassword(String username, String password) {
        return new UserDtoResponse(adminService.findByUsernamePassword(username, password));
    }

    @Override
    public PageData<UserDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<BaseUser> users = adminService.findAll(dataTableRequest);
        List<UserDtoResponse> responseList = toDtoList(users);
        PageData<UserDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(users.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public boolean muteUser(Long userId) {
        return adminService.muteUser(userId);
    }

    private List<UserDtoResponse> toDtoList(DataTableResponse<BaseUser> users) {
        return users.getItems().stream()
                    .map(UserDtoResponse::new)
                    .collect(Collectors.toList());
    }
}
