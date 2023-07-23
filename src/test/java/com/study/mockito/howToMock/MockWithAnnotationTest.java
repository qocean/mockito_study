package com.study.mockito.howToMock;

import com.study.mockito.quickstart.Account;
import com.study.mockito.quickstart.AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class MockWithAnnotationTest {

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private AccountDao accountDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock() {
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}
