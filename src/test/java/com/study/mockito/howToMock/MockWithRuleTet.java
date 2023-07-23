package com.study.mockito.howToMock;

import com.study.mockito.quickstart.Account;
import com.study.mockito.quickstart.AccountDao;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class MockWithRuleTet {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private AccountDao accountDao;

    @Test
    public void testMock() {
//        AccountDao accountDao = mock(AccountDao.class);
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}
