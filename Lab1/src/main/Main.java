package main;

import java.util.LinkedList;
import java.util.List;

import s241379.lab1.CountingIntSorter;
import s241379.lab1.FloatElement;
import s241379.lab1.IElement;
import s241379.lab1.InsertFloatSorter;
import s241379.lab1.IntElement;
import s241379.lab1.MergeFloatSorter;

public class Main {
	public static void main(String[] agrs) {
		
		// Counting sort int part
		List<IntElement> intList = new LinkedList<IntElement>();
		CountingIntSorter countingInt = new CountingIntSorter();

		for(int i = 0; i < 20; i ++)
			intList.add(new IntElement(Integer.toString(i) , (int)(Math.random()*1000)));
		
		System.out.println("Lista int przed counting sort: ");
		for(int i = 0; i < intList.size(); i++)
			System.out.println(intList.get(i).getName() + ": " + intList.get(i).getValue());
		
		List<IntElement> resultCountingInt = countingInt.solve(intList);
		
		System.out.println("\nLista int po counting sort: ");
		for(int i = 0; i < resultCountingInt.size(); i++)
			System.out.println(resultCountingInt.get(i).getName() + ": " + resultCountingInt.get(i).getValue());

		
		// Insert sort Float part
		List<IElement> floatList1 = new LinkedList<IElement>();
		InsertFloatSorter insertFloat = new InsertFloatSorter();

		for(int i = 0; i < 20; i ++)
			floatList1.add(new FloatElement(Integer.toString(i) , (float)(Math.random()*1000)));
		
		System.out.println("Lista float przed insert sort: ");
		for(int i = 0; i < floatList1.size(); i++)
			System.out.println(floatList1.get(i).getValue());
		
		List<IElement> resultCountingFloat1 = insertFloat.solve2(floatList1);
		
		System.out.println("\nLista infloatt po insert sort: ");
		for(int i = 0; i < resultCountingFloat1.size(); i++)
			System.out.println(resultCountingFloat1.get(i).getName()+": "+resultCountingFloat1.get(i).getValue());

		
		// Merge sort Float part
		List<IElement> floatList2 = new LinkedList<IElement>();
		MergeFloatSorter mergeSort = new MergeFloatSorter();

		for(int i = 0; i < 20; i ++)
			floatList2.add(new FloatElement(Integer.toString(i) , (float)Math.random()*1000));
		
		System.out.println("Lista float przed insert sort: ");
		for(int i = 0; i < floatList2.size(); i++)
			System.out.println(floatList2.get(i).getValue());
		
		List<IElement> resultCountingFloat2 = mergeSort.solve2(floatList2);
		
		System.out.println("\nLista infloatt po insert sort: ");
		for(int i = 0; i < resultCountingFloat2.size(); i++)
			System.out.println(resultCountingFloat2.get(i).getName()+": "+resultCountingFloat1.get(i).getValue());
	}
}



