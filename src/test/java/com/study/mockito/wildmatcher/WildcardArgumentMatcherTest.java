package com.study.mockito.wildmatcher;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WildcardArgumentMatcherTest {

    @Mock
    private SimpleService simpleService;

    @Test
    public void wilcardMethod1() {
        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(100);

        int result = simpleService.method1(1, "alex", Collections.emptyList(), "mockito");
        assertThat(result, equalTo(100));
    }

    @Test
    public void wilcardMethod1Demo2() {
//        when(simpleService.method1(anyInt(), "alex", anyCollection(), isA(Serializable.class))).thenReturn(100);
//        when(simpleService.method1(anyInt(), "wang", anyCollection(), isA(Serializable.class))).thenReturn(200);

        //此处如果放到最后，会覆盖上面的stubbing
        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(-1);
        when(simpleService.method1(anyInt(), eq("alex"), anyCollection(), isA(Serializable.class))).thenReturn(100);
        when(simpleService.method1(anyInt(), eq("wang"), anyCollection(), isA(Serializable.class))).thenReturn(200);
        int result = simpleService.method1(1, "alex", Collections.emptyList(), "mockito");
        assertThat(result, equalTo(100));

        result = simpleService.method1(1, "wang", Collections.emptyList(), "mockito");
        assertThat(result, equalTo(200));


        result = simpleService.method1(1, "suibian", Collections.emptyList(), "mockito");
        assertThat(result, equalTo(-1));
    }

    @Test
    public void wildcardMethod() {

        List<Object> emptyList = Collections.emptyList();
        doNothing().when(simpleService).method2(anyInt(), anyString(), anyCollection(), isA(Serializable.class));
        simpleService.method2(1, "suibian", emptyList, "mockito");

        verify(simpleService, times(1)).method2(1, "suibian", emptyList, "mockito");
        verify(simpleService, times(1)).method2(anyInt(), eq("suibian"), anyCollection(), isA(Serializable.class));
    }

    @After
    public void destroy() {
        reset(simpleService);
    }
}
