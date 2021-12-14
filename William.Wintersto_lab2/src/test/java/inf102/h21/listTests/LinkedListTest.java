package inf102.h21.listTests;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import inf102.h21.list.LinkedList;
import inf102.h21.list.List;

public class LinkedListTest {

	List<Integer> linkedList;
	
	@Before
	public void setup() {
		linkedList = new LinkedList<>();
	}
	
	@Test
	public void addSingleElementTest() {
		Integer element = 42;
		linkedList.addLast(element);
		assertEquals(element, linkedList.get(0));
	}
	
	@Test
	public void add100ElementsTest() {
		for (Integer i = 0; i < 100; i++) {
			linkedList.addLast(i);
			assertEquals(i, linkedList.get(i));
		}
	}
	
	@Test
	public void sizeTest() {
		assertEquals(0, linkedList.size());
		Integer nElements = 100;
		for (Integer i = 0; i < nElements; i++) {
			linkedList.addLast(i);
			assertEquals((i+1), linkedList.size());
		}
		assertEquals(nElements, (Integer) linkedList.size());
	}

	public void addNElements(List<Integer> list, int n) {
		for (Integer i = 0; i < n; i++) {
			list.addLast(i);
		}
	}
	
	@Test
	public void insertTest() {
		Integer nElements = 100;
		addNElements(linkedList, nElements);
		int currentSize = linkedList.size();
		
		Integer element = 42;
		Integer index = 50;
		linkedList.add(index, element);
		assertEquals(element, linkedList.get(index));
		
		Integer newSize = linkedList.size();
		assertEquals(currentSize, newSize-1);
	}
	
	@Test
	public void headInsert() {
		Integer nElements = 100;
		addNElements(linkedList, nElements);
		
		Integer element = 42;
		Integer index = 0;
		linkedList.add(index, element);
		assertEquals(element, linkedList.get(index));
	}
	
	@Test
	public void tailInsert() {
		Integer nElements = 100;
		addNElements(linkedList, nElements);
		
		Integer element = 42;
		Integer index = linkedList.size()-1;
		linkedList.add(index, 42);
		assertEquals(element, linkedList.get(index));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void accessEmptyList() {
		linkedList.get(0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void accessAboveBound() {
		Integer nElements = 100;
		Integer index = nElements;

		addNElements(linkedList, nElements);
		linkedList.get(index);
	}
	
	@Test
	public void insert100Random() {
		Random rand = new Random();
		
		Integer nElements = 100;
		addNElements(linkedList, nElements);
		
		for (Integer i = 0; i < nElements; i++) {
			Integer randomIndex = rand.nextInt(linkedList.size());
			Integer randomNumber = rand.nextInt(1000);
			linkedList.add(randomIndex, randomNumber);
			assertEquals(randomNumber, linkedList.get(randomIndex));
		}
		
		assertEquals(nElements*2, linkedList.size());
	}
}

