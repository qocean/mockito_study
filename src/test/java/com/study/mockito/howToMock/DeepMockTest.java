package com.study.mockito.howToMock;

import com.study.mockito.howtomock.HowToMockService;
import com.study.mockito.howtomock.HowToMockVO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class DeepMockTest {

    @Mock
    public HowToMockService howToMockService;

    @Mock
    public HowToMockVO howToMockVO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeepMock() {
        when(howToMockService.get()).thenReturn(howToMockVO);

        HowToMockVO howToMock = howToMockService.get();
        howToMock.foo();
    }
}
