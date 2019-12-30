package org.lypgod.demo.mock;

import org.lypgod.demo.model.dao.AccountDao;
import org.lypgod.demo.model.entity.Account;

import javax.servlet.http.HttpServletRequest;

public class AccountLoginController {
    private AccountDao accountDao;

    public AccountLoginController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String login(HttpServletRequest request) {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");

        try {
            Account account = accountDao.findAccount(username, password);
            return account == null ? "/login" : "/index";
        } catch (Exception e) {
            return "/500";
        }
    }
}
