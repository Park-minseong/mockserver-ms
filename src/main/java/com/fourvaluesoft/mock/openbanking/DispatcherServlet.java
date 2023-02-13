package com.fourvaluesoft.mock.openbanking;

import com.fourvaluesoft.mock.openbanking.common.ErrorResponse;
import com.fourvaluesoft.mock.openbanking.controller.Controller;
import com.fourvaluesoft.mock.openbanking.filedata.controller.HttpParameterFileDataController;
import com.fourvaluesoft.mock.openbanking.filedata.controller.JsonRequestFileDataController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DispatcherServlet", value = "/")
public class DispatcherServlet extends HttpServlet {

    private static final String VIEWS_PATH = "/WEB-INF/views";
    private static final String ERROR_VIEW = "/common/error.jsp";

    private final Map<String, Controller> controllerMap = new HashMap<String, Controller>();

    @Override
    public void init() throws ServletException {
        String webResourcesPath = getServletContext().getRealPath("/");

//        controllerMap.put("/account/balance", new AccountBalanceController(webResourcesPath));
        controllerMap.put("/account/balance",
                new HttpParameterFileDataController(webResourcesPath, "/account/balance/", "tran_dtime"));
//        controllerMap.put("/inquiry/real_name", new AccountRealNameController(webResourcesPath));
        controllerMap.put("/inquiry/real_name",
                new JsonRequestFileDataController(webResourcesPath, "/inquiry/real_name/", "account_num"));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Controller controller = controllerMap.get(request.getServletPath());

        String requestMethod = request.getMethod();

        if (controller == null) {
            String rspCode = "O0007";
            String rspMessage = "API를 요청 또는 처리할 수 없습니다. (API 업무처리 Routing 실패 시)";

            request.setAttribute("error", new ErrorResponse(rspCode, rspMessage));

            forwardToErrorView(request, response);
        } else if (requestMethod.equals(controller.getMethod())) {
            forwardToView(request, response, controller.processRequest(request, response));
        } else {
            String rspCode = "O0010";
            String rspMessage = "허용되지 않은 HTTP Method 입니다.";

            request.setAttribute("error", new ErrorResponse(rspCode, rspMessage));

            forwardToErrorView(request, response);
        }
    }

    private void forwardToView(HttpServletRequest request, HttpServletResponse response, String viewPath)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEWS_PATH + viewPath).forward(request, response);
    }

    private void forwardToErrorView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forwardToView(request, response, ERROR_VIEW);
    }
}