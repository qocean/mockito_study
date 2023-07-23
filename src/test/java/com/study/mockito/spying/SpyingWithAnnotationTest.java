package com.study.mockito.spying;


import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class SpyingWithAnnotationTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Spy
    private List<String> list = new ArrayList<>();

    @Test
    public void testSpy() {
        //正常mock出的对象，下面的方法是无效的
        list.add("Mockito");
        list.add("PowerMock");

        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(false));

        when(list.isEmpty()).thenReturn(true);
        when(list.size()).thenReturn(0);
        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(true));
        assertThat(list.size(), equalTo(0));
    }
}
