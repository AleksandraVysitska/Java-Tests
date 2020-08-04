package com.tests.prova;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataServiceTest {
	
	
	@BeforeAll
	static void setUpBeforeAll() throws Exception {
		System.out.println("Run before all test");
	}
	
	@BeforeEach 
	public void setUp() throws Exception {
		System.out.println("Run before every test"); 
	  }
	
	@AfterEach
	public void tearDown () throws Exception {
		System.out.println("Run after every test");
	}
	 
	@AfterAll
	static void tearDownAfterAll () throws Exception {
		System.out.println("Run after all test");
	}
	
	@Test
	public void testFindMax() throws Exception {
		
		List<Integer> numbers = Arrays.asList(1,4,2,5,7,8,89,92,58);
		List<Integer> numbers2 = Arrays.asList(89,92,58,103,87, 643);
		
		int max = DataService.findMax(numbers);
		int max2 = DataService.findMax(numbers2);
		
		assertThat(max).isNotNull().isGreaterThan(60).isLessThan(199).isEqualTo(92);
		assertThat(max2).isEqualTo(643);
		System.out.println("testFindMax");
	}
	
	@Test
	public void testFindMaxException() throws Exception {
		
		List<Integer> numbers = Arrays.asList();
		
		 Exception exception = assertThrows(Exception.class, () ->
		 DataService.findMax(numbers));
	//    assertEquals("La lista è vuota", exception.getMessage());
		 System.out.println("testFindMaxException");
		
	}
	
	@Test
	public void testFindMaxByStreams() throws Exception {
		
		List<Integer> numbers = Arrays.asList(1,4,2,5,7,8,89,92,58);
		List<Integer> numbers2 = Arrays.asList(89,92,58,103,87, 643);
		
		int max = DataService.findMaxByStreams(numbers);
		int max2 = DataService.findMaxByStreams(numbers2);
		
		assertThat(max).isEqualTo(92);
		assertThat(max2).isEqualTo(643);
		System.out.println("testFindMaxByStreams");
	}
	
	@Test
	public void testFindMaxByStreamsException() throws Exception {
		
		List<Integer> numbers = Arrays.asList();
		
		 Exception exception = assertThrows(Exception.class, () ->
		 DataService.findMaxByStreams(numbers));
	//    assertEquals("La lista è vuota", exception.getMessage());
		 System.out.println("testFindMaxByStreamsException");
		
	}
	
	@Test
	public void testFindMaxByStreams_Null_List() throws Exception {
		
		List<Integer> numbers = null;
		
		 Exception exception = assertThrows(Exception.class, () ->
		 DataService.findMaxByStreams(numbers));
	//    assertEquals("La lista è vuota", exception.getMessage());
		 System.out.println("testFindMaxByStreams_Null_List");
		
	}
	
	@Test 
	public void testPerformance () throws Exception {
		 assertTimeout(ofMillis(100), () -> {
	           List<Integer> numbers = Stream.iterate(0, n -> n+1).limit(2000)
	        		   .collect(Collectors.toList());
	           
	           DataService.findMaxByStreams(numbers);
	        });
	}

}
