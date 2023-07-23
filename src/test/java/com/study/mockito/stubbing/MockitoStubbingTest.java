package com.study.mockito.stubbing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoStubbingTest {
//    private List<String> list;

    @Before
    public void init() {
        this.list = mock(ArrayList.class);
    }

    @After
    public void destroy() {
        reset(this.list);
    }

    private List<String> list;

    @Test
    public void howToUseStubbing() {
        when(list.get(0)).thenReturn("first");
        assertThat(list.get(0), equalTo("first"));

        when(list.get(anyInt())).thenThrow( new RuntimeException());
        try {
            list.get(0);
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void howToStubbingVoidMethod() {
//        when(list.clear()).thenThrow(new RuntimeException());
        doNothing().when(list).clear();
        list.clear();
        verify(list, times(1)).clear();

        doThrow(RuntimeException.class).when(list).clear();
        try {
            list.clear();
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void stubbingDoReturn() {
        when(list.get(0)).thenReturn("first");
        doReturn("second").when(list).get(1);
        assertThat(list.get(0), equalTo("first"));
        assertThat(list.get(1), equalTo("second"));
    }

    @Test
    public void iteratorStubbing() {
//        when(list.size()).thenReturn(1);
//        when(list.size()).thenReturn(2);
//        when(list.size()).thenReturn(3);
//        when(list.size()).thenReturn(4);
//        assertThat(list.size(), equalTo(1));

        when(list.size()).thenReturn(1, 2, 3, 4);
        assertThat(list.size(), equalTo(1));
        assertThat(list.size(), equalTo(2));
        assertThat(list.size(), equalTo(3));
        assertThat(list.size(), equalTo(4));
//        assertThat(list.size(), equalTo(x));
    }

    @Test
    public void stubbingWithAnswer() {
        when(list.get(anyInt())).thenAnswer(param -> {
            Integer index = param.getArgumentAt(0, Integer.class);
            return String.valueOf(index * 10);
        });

        assertThat(list.get(0), equalTo("0"));
        assertThat(list.get(20), equalTo("200"));
    }

    @Test
    public void StubbingWithRealCall() {
        StubbingService stubbingService = mock(StubbingService.class);
        stubbingService.getS();
        System.out.println(stubbingService.getClass());
        System.out.println(stubbingService.getI());

        when(stubbingService.getS()).thenReturn("alex");
        assertThat(stubbingService.getS(), equalTo("alex"));

        when(stubbingService.getI()).thenCallRealMethod();
        assertThat(stubbingService.getI(), equalTo(10));
    }
}
