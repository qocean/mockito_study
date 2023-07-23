package com.study.mockito.argumentmatchers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentMatchers {

    @Test
    public void basicTest() {
        List<Integer> list = mock(ArrayList.class);
        when(list.get(eq(0))).thenReturn(100);

        assertThat(list.get(0), equalTo(100));
        assertThat(list.get(1), nullValue());
    }


    @Test
    public void testComplex() {
        Foo foo = mock(Foo.class);
        when(foo.function(isA(Child1.class))).thenReturn(100);
        int result = foo.function(new Child1());
        assertThat(result, equalTo(100));

//        result = foo.function(new Child2());
//        assertThat(result, equalTo(100));

        //使stubbing无效
        reset(foo);
        when(foo.function(any(Child1.class))).thenReturn(100);
        result = foo.function(new Child2());
        assertThat(result, equalTo(100));
    }

    static class Foo {
        int function (Parent p) {
            return p.work();
        }
    }

    interface Parent {
        int work();
    }

    class Child1 implements Parent {

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    class Child2 implements Parent {

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }
}
