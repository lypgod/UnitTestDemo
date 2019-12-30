package org.lypgod.demo.mock;

import org.junit.Before;
import org.junit.Test;
import org.lypgod.demo.model.dao.AccountDao;
import org.lypgod.demo.model.entity.Account;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class MockitoAnnotationTest {
    @Mock
    private AccountDao accountDao;
    @Mock
    private HttpServletRequest request;

    private AccountLoginController accountLoginController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.accountLoginController = new AccountLoginController(accountDao);
    }

    @Test
    public void loginTest() {
        when(request.getParameter("username")).thenReturn("lypgod");
        when(request.getParameter("password")).thenReturn("123456");

        when(accountDao.findAccount(anyString(), anyString())).thenReturn(null);
        when(accountDao.findAccount("lypgod", "123456")).thenReturn(new Account());

        assertThat(accountLoginController.login(request), equalTo("/index"));

        when(request.getParameter("password")).thenReturn("12345");

        assertThat(accountLoginController.login(request), equalTo("/login"));

        when(accountDao.findAccount(anyString(), anyString())).thenThrow(new UnsupportedOperationException());
        assertThat(accountLoginController.login(request), equalTo("/500"));
    }
}