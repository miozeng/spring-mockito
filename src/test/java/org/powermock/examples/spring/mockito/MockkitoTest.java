package org.powermock.examples.spring.mockito;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mock;

import powermock.examples.spring.IdGenerator;

/**
 * for more information please view
 * http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html
 * https://github.com/mockito/mockito/wiki/FAQ
 * @author admin
 *
 */
public class MockkitoTest {

//	@Mock
//	private IdGenerator idGenerator;
	
	/**
	 * test 
	 */
	@Test
	public void testGetMsg(){
		// //You can mock concrete classes, not just interfaces
		 IdGenerator idGenerator = mock(IdGenerator.class);
		 //given  or stubbing
		when(idGenerator.getMsg("hello")).thenReturn("not hello");
		System.out.println(idGenerator.getMsg("hello"));
	}
	
	/**
	 * test param matchers
	 */
	@Test
	public void testGetMsg2(){
		// //You can mock concrete classes, not just interfaces
		 IdGenerator idGenerator = mock(IdGenerator.class);
		 //anyX() and any(SomeType.class) matchers
		when(idGenerator.getMsg( anyString())).thenReturn("not hello");
		System.out.println(idGenerator.getMsg("hello"));
	}
	
	/**
	 * test consecutive calls 
	 */
	@Test
	public void testGetMsg3(){
		// //You can mock concrete classes, not just interfaces
		 IdGenerator idGenerator = mock(IdGenerator.class);
		 //consecutive calls    last one will win
		when(idGenerator.getMsg( anyString())).thenReturn("1").thenReturn("2").thenReturn("3");
		System.out.println(idGenerator.getMsg("hello"));
		System.out.println(idGenerator.getMsg("hello"));
		System.out.println(idGenerator.getMsg("hello"));
		System.out.println(idGenerator.getMsg("hello"));
	}
	
	/**
	 * test verify times
	 * times(number)   never()  atLeastOnce()  atLeast(number) atMost(number) 
	 *  calls(number):This verification mode can only be used with in order verification.
	 */
	@Test
	public void testGetMsg4(){
		// //You can mock concrete classes, not just interfaces
		 IdGenerator idGenerator = mock(IdGenerator.class);
		 //anyX() and any(SomeType.class) matchers
		when(idGenerator.getMsg( anyString())).thenReturn("not hello");
		System.out.println(idGenerator.getMsg("hello"));
		 verify(idGenerator, times(1)).getMsg(anyString());
	}
	/**
	 * test void method 
	 * doThrow 
	 * U can also use doThrow(),  doNothing()and doCallRealMethod()
	 */
	@Test
	public void testPrint(){
		// //You can mock concrete classes, not just interfaces
		 IdGenerator idGenerator = mock(IdGenerator.class);
//		 doThrow(new RuntimeException()).when(idGenerator).print();
//		 doCallRealMethod(new RuntimeException()).when(idGenerator).print();
//		  doNothing().when(idGenerator).print();
//		  doCallRealMethod().when(idGenerator).print();
		  idGenerator.print();
		  verify(idGenerator).print();
	}
	
	/**
	 * test void method 
	 * by spy 
	 */
	@Test
	public void testPrint2(){
		// //You can mock concrete classes, not just interfaces
		 IdGenerator idGenerator = spy(IdGenerator.class);
		 //using the spy calls *real* methods
		idGenerator.print();
		when(idGenerator.getMsg( anyString())).thenReturn("not hello");
		System.out.println(idGenerator.getMsg("hello"));
		////optionally, you can verify
		 verify(idGenerator).print();
	}
}
