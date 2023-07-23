package com.study.mockito.quickstart;

import javax.servlet.http.HttpServletRequest;

public class AccountLoginController {

    private AccountDao accountDao;

    public AccountLoginController() {

    };

    public AccountLoginController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String login(HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Account account = accountDao.findAccount(userName, password);

            if (null == account) {
                return "/login";
            } else {
                return "/index";
            }
        } catch (Exception e) {
            return "/505";
        }
    }
}
