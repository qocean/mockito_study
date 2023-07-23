package com.study.mockito.spying;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {

    @Test
    public void testNoCallRealMethodWithMock() {
        List<String> list = mock(ArrayList.class);
        list.add("Mockito");
        list.add("PowerMock");

        System.out.println(list.get(0));
        System.out.println(list.size());
    }


    @Test
    public void testSpy() {
        List<String> realList = new ArrayList<>();
        List<String> list = spy(realList);

        //正常mock出的对象，下面的方法是无效的
        list.add("Mockito");
        list.add("PowerMock");

        //调用真实方法
        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(false));

        //对方法进行stubbing
        when(list.isEmpty()).thenReturn(true);
        when(list.size()).thenReturn(0);
        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(true));
        assertThat(list.size(), equalTo(0));
    }
}
