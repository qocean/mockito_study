package com.study.mockito.howToMock;

import com.study.mockito.howtomock.HowToMockService;
import com.study.mockito.howtomock.HowToMockVO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class DeepMockDemo2Test {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    public HowToMockService howToMockService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeepMock() {
        HowToMockVO howToMock = howToMockService.get();
        howToMock.foo();
    }
}
