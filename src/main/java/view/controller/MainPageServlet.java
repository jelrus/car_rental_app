package view.controller;

import facade.user.UserFacade;
import facade.user.impl.UserFacadeImpl;
import view.dto.response.PageData;
import view.dto.response.user.UserDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/management/users")
public class MainPageServlet extends AbstractServlet {

    UserFacade userFacade = new UserFacadeImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AbstractServlet.HeaderName[] columnNames = getColumnNamesForUsers();
        PageData<UserDtoResponse> response = userFacade.findAll(req);
        response.initPaginationState(response.getCurrentPage());
        req.getSession().setAttribute("headerDataList", getHeaderDataList(columnNames, response));
        req.getSession().setAttribute("pageData", response);
        req.getSession().setAttribute("cardHeader", "All users");
        req.getSession().setAttribute("allowCreate", true);
        getServletContext().getRequestDispatcher("/admin/management/users.jsp").forward(req, resp);
    }

    private AbstractServlet.HeaderName[] getColumnNamesForUsers() {
        return new AbstractServlet.HeaderName[]{
                new AbstractServlet.HeaderName("#", null, null),
                new AbstractServlet.HeaderName("Created", "created", "created"),
                new AbstractServlet.HeaderName("Updated", "updated", "updated"),
                new AbstractServlet.HeaderName("Profile pic", "profile_pic", "profilePic"),
                new AbstractServlet.HeaderName("Username", "username", "username"),
                new AbstractServlet.HeaderName("Details", null, null),
                new AbstractServlet.HeaderName("Disable", null, null)
        };
    }
}
