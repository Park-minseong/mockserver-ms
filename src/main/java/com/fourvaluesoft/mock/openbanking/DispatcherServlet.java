package com.fourvaluesoft.mock.openbanking;

import com.fourvaluesoft.mock.openbanking.account.balance.AccountBalanceController;
import com.fourvaluesoft.mock.openbanking.account.realname.AccountRealNameController;
import com.fourvaluesoft.mock.openbanking.common.ErrorResponse;
import com.fourvaluesoft.mock.openbanking.controller.Controller;

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

    private static final String VIEWS_PATH = "/WEB-INF/views/";

    private final Map<String, Controller> controllerMap = new HashMap<String, Controller>();

    private Controller controller;

    private String resultView;

    @Override
    public void init() throws ServletException {
        String webResourcesPath = getServletContext().getRealPath("/");
        controllerMap.put("/account/balance", new AccountBalanceController(webResourcesPath));
        controllerMap.put("/inquiry/real_name", new AccountRealNameController(webResourcesPath));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        controller = controllerMap.get(request.getServletPath());

        if (controller == null) {
            resultView = getErrorViewPath(request, "O0007",
                    "API를 요청 또는 처리할 수 없습니다. (API 업무처리 Routing 실패 시)");

            forwardToView(request, response);
        } else if (controller.getMethod().equals("GET")) {
            doGet(request, response);
        } else if (controller.getMethod().equals("POST")){
            doPost(request,response);
        } else {
            resultView = getErrorViewPath(request, "O0010", "허용되지 않은 HTTP Method 입니다.");

            forwardToView(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!controller.getMethod().equals(request.getMethod())) {
            resultView = getErrorViewPath(request, "O0010", "허용되지 않은 HTTP Method 입니다.");
        } else {
            resultView = controller.processRequest(request, response);
        }
        forwardToView(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!controller.getMethod().equals(request.getMethod())) {
            resultView = getErrorViewPath(request, "O0010", "허용되지 않은 HTTP Method 입니다.");
        } else {
            resultView = controller.processRequest(request, response);
        }
        forwardToView(request, response);
    }

    private void forwardToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VIEWS_PATH + resultView).forward(request, response);
    }

    private String getErrorViewPath(HttpServletRequest request, String rspCode, String rspMessage) {
        request.setAttribute("error", new ErrorResponse(rspCode, rspMessage));

        return "common/error.jsp";
    }
}