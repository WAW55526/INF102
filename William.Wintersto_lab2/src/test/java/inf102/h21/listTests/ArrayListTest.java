package inf102.h21.listTests;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import inf102.h21.list.ArrayList;
import inf102.h21.list.List;

public class ArrayListTest {

	List<Integer> arrayList;
	
	@Before
	public void setup() {
		arrayList = new ArrayList<>();
	}
	
	@Test
	public void addSingleElementTest() {
		Integer element = 42;
		arrayList.addLast(element);
		assertEquals(element, arrayList.get(0));
	}
	
	@Test
	public void add100ElementsTest() {
		for (Integer i = 0; i < 100; i++) {
			arrayList.addLast(i);
			assertEquals(i, arrayList.get(i));
		}
	}
	
	@Test
	public void sizeTest() {
		assertEquals(0, arrayList.size());
		Integer nElements = 100;
		for (Integer i = 0; i < nElements; i++) {
			arrayList.addLast(i);
			assertEquals((i+1), arrayList.size());
		}
		assertEquals(nElements, (Integer) arrayList.size());
	}

	public void addNElements(List<Integer> list, int n) {
		for (Integer i = 0; i < n; i++) {
			list.addLast(i);
		}
	}
	
	@Test
	public void insertTest() {
		Integer nElements = 100;
		addNElements(arrayList, nElements);
		int currentSize = arrayList.size();
		
		Integer element = 42;
		Integer index = 50;
		arrayList.add(index, element);
		assertEquals(element, arrayList.get(index));
		
		Integer newSize = arrayList.size();
		assertEquals(currentSize, newSize-1);
	}
	
	@Test
	public void headInsert() {
		Integer nElements = 100;
		addNElements(arrayList, nElements);
		
		Integer element = 42;
		Integer index = 0;
		arrayList.add(index, element);
		assertEquals(element, arrayList.get(index));
	}
	
	@Test
	public void tailInsert() {
		Integer nElements = 100;
		addNElements(arrayList, nElements);
		
		Integer element = 42;
		Integer index = arrayList.size()-1;
		arrayList.add(index, 42);
		assertEquals(element, arrayList.get(index));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void accessEmptyList() {
		arrayList.get(0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void accessAboveBound() {
		Integer nElements = 100;
		Integer index = nElements;

		addNElements(arrayList, nElements);
		arrayList.get(index);
	}
	
	@Test
	public void insert100Random() {
		Random rand = new Random();
		
		Integer nElements = 100;
		addNElements(arrayList, nElements);
		
		for (Integer i = 0; i < nElements; i++) {
			Integer randomIndex = rand.nextInt(arrayList.size());
			Integer randomNumber = rand.nextInt(1000);
			arrayList.add(randomIndex, randomNumber);
			assertEquals(randomNumber, arrayList.get(randomIndex));
		}
		
		assertEquals(nElements*2, arrayList.size());
	}
}

