package util.controller;

import facade.user.UserFacade;
import facade.user.impl.UserFacadeImpl;
import persistence.dao.user.impl.UserDaoImpl;
import persistence.entity.user.type.RoleType;
import service.user.impl.UserServiceImpl;
import view.controller.AbstractServlet;
import view.dto.request.user.*;
import view.dto.response.PageData;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserControllerUtil extends AbstractServlet {

    private final UserFacade userFacade = new UserFacadeImpl(new UserServiceImpl(new UserDaoImpl()));

    public void createManagerPost(HttpServletRequest req) {
        UserDtoRequest userDtoRequest = new UserDtoRequest();
        setupBaseForUser(req, userDtoRequest);
        userDtoRequest.setRoleType(RoleType.ROLE_MANAGER);
        userFacade.create(userDtoRequest);
    }

    public void createUserPost(HttpServletRequest req) {
        UserDtoRequest userDtoRequest = new UserDtoRequest();
        setupBaseForUser(req, userDtoRequest);
        userDtoRequest.setRoleType(RoleType.ROLE_USER);
        userFacade.create(userDtoRequest);
    }

    public void deleteUserPost(HttpServletRequest req) {
        userFacade.delete(Long.parseLong(req.getParameter("id")));
    }

    public void editUserGet(HttpServletRequest req) {
        Long managerId = Long.parseLong(req.getParameter("id"));
        UserDtoResponse user = userFacade.findById(managerId);
        List<RoleType> roleTypes = Arrays.stream(RoleType.values()).collect(Collectors.toList());
        roleTypes.remove(RoleType.ROLE_ADMIN);
        req.setAttribute("bUser", user);
        req.setAttribute("roleTypes", roleTypes);
    }

    public UserDtoResponse editUserPost(HttpServletRequest req) {
        Long managerId = Long.parseLong(req.getParameter("id"));
        UserDtoResponse userDtoResponse = userFacade.findById(managerId);

        AuthDtoRequest authDto = setupAuthDto(req);
        InfoDtoRequest infoDto = setupInfoDto(req);
        BalanceDtoRequest balanceDto = setupBalanceDto(req);
        RoleDtoRequest roleDto = setupRoleDto(req);
        AccessDtoRequest accessDto = setupAccessDto(req);

        userFacade.updateSecurityInfo(authDto, userDtoResponse.getId());
        userFacade.updateInfo(infoDto, userDtoResponse.getId());
        userFacade.updateBalance(balanceDto, userDtoResponse.getId());
        userFacade.updateRole(roleDto, userDtoResponse.getId());
        userFacade.updateAccess(accessDto, userDtoResponse.getId());
        return userDtoResponse;
    }

    public void hideUserPost(HttpServletRequest req) {
        UserDtoResponse userDtoResponse = userFacade.findById(Long.parseLong(req.getParameter("id")));
        AccessDtoRequest accessDtoRequest = new AccessDtoRequest();
        accessDtoRequest.setEnabled(!userDtoResponse.getEnabled());
        userFacade.updateAccess(accessDtoRequest, userDtoResponse.getId());
    }

    public void infoUserGet(HttpServletRequest req) {
        UserDtoResponse user = userFacade.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("bUser", user);
    }

    public void managersGetAdmin(HttpServletRequest req) {
        PageData<UserDtoResponse> managers = userFacade.findAllManagers(req);
        AbstractServlet.HeaderName[] columnNames = getColumnNamesForUsers();
        List<HeaderData> headerData = getHeaderDataList(columnNames, managers);
        req.setAttribute("headerDataList", headerData);
        req.setAttribute( "createUrl", "/admin/management/managers");
        req.setAttribute("cardHeader", "managers");
        req.setAttribute("pageData", managers);
    }

    public void usersGetAdmin(HttpServletRequest req) {
        PageData<UserDtoResponse> users = userFacade.findAllUsers(req);
        AbstractServlet.HeaderName[] columnNames = getColumnNamesForUsers();
        List<HeaderData> headerData = getHeaderDataList(columnNames, users);
        req.setAttribute("headerDataList", headerData);
        req.setAttribute( "createUrl", "/admin/management/users");
        req.setAttribute("cardHeader", "users");
        req.setAttribute("pageData", users);
    }

    private AbstractServlet.HeaderName[] getColumnNamesForUsers() {
        return new AbstractServlet.HeaderName[]{
                new AbstractServlet.HeaderName("Created", "created", "created"),
                new AbstractServlet.HeaderName("Updated", "updated", "updated"),
                new AbstractServlet.HeaderName("Username", "username", "username"),
                new AbstractServlet.HeaderName("Details", null, null),
                new AbstractServlet.HeaderName("Disable", null, null),
                new AbstractServlet.HeaderName("Delete", null, null)
        };
    }

    private void setupBaseForUser(HttpServletRequest req, UserDtoRequest userDtoRequest) {
        userDtoRequest.setUsername(req.getParameter("username"));
        userDtoRequest.setPassword(req.getParameter("password"));
        userDtoRequest.setFirstName(req.getParameter("firstName"));
        userDtoRequest.setLastName(req.getParameter("lastName"));
        userDtoRequest.setProfilePic(req.getParameter("profilePic"));
        userDtoRequest.setBalance(new BigDecimal(req.getParameter("balance")));
        userDtoRequest.setDescription(req.getParameter("description"));
        userDtoRequest.setEnabled(true);
    }

    private AuthDtoRequest setupAuthDto(HttpServletRequest req) {
        AuthDtoRequest authDto = new AuthDtoRequest();
        authDto.setUsername(req.getParameter("username"));
        authDto.setPassword(req.getParameter("password"));
        return authDto;
    }

    private InfoDtoRequest setupInfoDto(HttpServletRequest req) {
        InfoDtoRequest infoDto = new InfoDtoRequest();
        infoDto.setFirstName(req.getParameter("firstName"));
        infoDto.setLastName(req.getParameter("lastName"));
        infoDto.setProfilePic(req.getParameter("profilePic"));
        infoDto.setDescription(req.getParameter("description"));
        return infoDto;
    }

    private BalanceDtoRequest setupBalanceDto(HttpServletRequest req) {
        BalanceDtoRequest balanceDto = new BalanceDtoRequest();
        balanceDto.setBalance(new BigDecimal(req.getParameter("balance")));
        return balanceDto;
    }

    private RoleDtoRequest setupRoleDto(HttpServletRequest req) {
        RoleDtoRequest roleDto = new RoleDtoRequest();
        roleDto.setRoleType(RoleType.valueOf(req.getParameter("roleType")));
        return roleDto;
    }

    private AccessDtoRequest setupAccessDto(HttpServletRequest req) {
        AccessDtoRequest accessDto = new AccessDtoRequest();
        accessDto.setEnabled(Objects.equals(req.getParameter("enabled"), "on"));
        return accessDto;
    }

    private boolean isNumber(String test){
        try {
            BigDecimal bigDecimal = new BigDecimal(test);
        } catch (ArithmeticException | NumberFormatException e){
            return false;
        }
        return true;
    }
}