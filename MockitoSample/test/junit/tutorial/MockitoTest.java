package junit.tutorial;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.cglib.transform.impl.AddDelegateTransformer;

public class MockitoTest {
	@Test
	public void mockitoSample1() {
		List<String> mock = Mockito.mock(List.class);
		assertThat(mock.get(0), is(nullValue()));
		assertThat(mock.contains("Hello"), is(false));
	}

	@Test
	public void mockitoSample2() {
		List<String> stub = mock(List.class);
		when(stub.get(0)).thenReturn("Hello");
		assertThat(stub.get(0), is("Hello"));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void mockitoSample3() {
		List<String> stub = mock(List.class);
		when(stub.get(0)).thenReturn("Hello");
		when(stub.get(1)).thenReturn("World");
		when(stub.get(2)).thenThrow(new IndexOutOfBoundsException());
		stub.get(2);
	}

	@Test(expected = RuntimeException.class)
	public void mockitoSample4() {
		List<String> stub = mock(List.class);
		doThrow(new RuntimeException()).when(stub).clear();
		stub.clear();
	}

	@Test
	public void mockitoSample5() {
		List<String> stub = mock(List.class);
		when(stub.get(anyInt())).thenReturn("Hello");
		assertThat(stub.get(0), is("Hello"));
		assertThat(stub.get(1), is("Hello"));
		assertThat(stub.get(999), is("Hello"));
	}

	@Test
	public void mockitoSample6() {
		List<String> mock = mock(List.class);
		mock.clear();
		mock.add("Hello");
		mock.add("Hello");
		verify(mock).clear();
		verify(mock, times(2)).add("Hello");
		verify(mock, never()).add("World");
	}

	@Test
	public void mockitoSample7() {
		List list = new ArrayList<>();
		List spy = spy(list);
		when(spy.size()).thenReturn(100);
		spy.add("Hello");

		// assertThat(spy.get(0), is("Hello"));
		assertThat(spy.size(), is(100));

	}
	
	@Test
	public void mockitoSample8() {
		List<String> list = new java.util.LinkedList<String>();
		List<String> spy = spy(list);
		
		doReturn("Mockito").when(spy).get(1);
		spy.add("Hello");
		spy.add("World");
		verify(spy).add("Hello");
		verify(spy).add("World");
		assertThat(spy.get(0), is("Hello"));
		assertThat(spy.get(1), is("Mockito"));
	}
}
