package com.tasksCodewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PinUtills {
	
	public static String generateNumbersFromMap(Map<Integer, List<Integer>> mapWithListsOfNumbers) {
	    Map<Integer,List<int[]>>indexes = getIndexes(mapWithListsOfNumbers);
	    
	    Map<Integer,List<int[]>>allIndexes = updateIndexes(indexes);
	    for (int i = 0; i < allIndexes.size(); i++) {
			List<int[]> list = allIndexes.get(i);
			List<int[]> removeDuplicateArrays = removeDuplicateArrays(list);
			allIndexes.put(i, removeDuplicateArrays);
		}
	    //allNumbers.put(1, Arrays.asList(1,2,4));
//		allNumbers.put(2, Arrays.asList(0,8));
//		allNumbers.put(3, Arrays.asList(1,2,4));
//	    		Index: 0. Value :[0, 1, 2] 101 102 104 |000 001 002
//	    		Index: 0. Value :[1, 2, 0] 180 182 184 |010 011 012
//	    		Index: 0. Value :[2, 0, 1] 201 202 204 |100 101 102
//	    		Index: 0. Value :[1, 0, 2] 281 282 284 |110 111 112
//	    		Index: 0. Value :[2, 1, 0] 401 402 204 |200 201 202
//	    		Index: 0. Value :[0, 2, 1] 481 482 484 |210 211 212
	    
//	    		Index: 1. Value :[0, 1]
//	    		Index: 1. Value :[1, 0]
	    
//	    		Index: 2. Value :[0, 1, 2]
//	    		Index: 2. Value :[1, 2, 0]
//	    		Index: 2. Value :[2, 0, 1]
//	    		Index: 2. Value :[1, 0, 2]
//	    		Index: 2. Value :[2, 1, 0]
//	    		Index: 2. Value :[0, 2, 1]
		
		return null;
	}
	
	private static Map<Integer, List<int[]>> updateIndexes(Map<Integer, List<int[]>> indexes) {
		Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();
		int counter = 0;
	    for (Map.Entry<Integer, List<int[]>> entry : indexes.entrySet()) {
	    	List<int[]> value = entry.getValue();
	    	int[] mainIndexArray = value.get(0);
	    	value.addAll(getAllPossibleIndexCombinations(mainIndexArray));
	    	map.put(counter, value);
	    	counter++;
	    }
		return map;
	}

	private static List<int[]> getAllPossibleIndexCombinations(int[] mainIndexArray) {
		int factorial = getFactorial(mainIndexArray);
		List<int[]> arrayList = new ArrayList<>();
		List<Integer> intList = Arrays.stream(mainIndexArray).boxed().collect(Collectors.toList());
		int max = intList.stream().mapToInt(v->v).max().orElseThrow(NoSuchElementException::new);
		int min = intList.stream().mapToInt(v->v).min().orElseThrow(NoSuchElementException::new);
		for (int i = 0; i < factorial; i++) {
			int[]tempArr = new int[mainIndexArray.length];
			int c;
			for (int j = 0; j < intList.size(); j++) {
				c = intList.get(j);
				if(c==max) tempArr[j] = min;
				if(c<max) {
					tempArr[j] = getNextElement(c,mainIndexArray);
				} 
				intList.set(j, tempArr[j]);
			}
			if(listContainsIntArray(arrayList, tempArr)) {
				int toBeInstaledAfter = tempArr[0];
				int [] newArr = new int [tempArr.length-1];
				for (int j = 0; j < newArr.length; j++) {
					newArr[j] = tempArr[j+1];
				}
				List<int[]> allPossibleIndexCombinations = getAllPossibleIndexCombinations(newArr);
				
				List<int[]> collect = allPossibleIndexCombinations.stream().map(v->v=repairArray(v,toBeInstaledAfter)).collect(Collectors.toList());
				arrayList.addAll(collect);

			}else {
			arrayList.add(tempArr);	
			}
		}
		return removeDuplicateArrays(arrayList);
	}
	
	private static List<int[]> removeDuplicateArrays(List<int[]> arrayList) {
		List<String> collect = arrayList.stream().map(v->Arrays.toString(v))
		.distinct().collect(Collectors.toList());
		List<int[]> resultList = new ArrayList<>();
		for(String s : collect) {
			String[] split = s.split(",");
			int[]temparr = new int[split.length];
			for (int i = 0; i < temparr.length; i++) {
				temparr[i] = Integer.parseInt(split[i].replaceAll("\\D", ""));
			}
			resultList.add(temparr);
		}
		return resultList;
	}

	private static int[] repairArray(int[]beforeArray,int toBeInstaledAfter) {
		int[] resultArr = new int[beforeArray.length+1];
		resultArr[0]=toBeInstaledAfter;
		for (int i = 1; i < resultArr.length; i++) {
			resultArr[i] = beforeArray[i-1];
		}
		return resultArr;
	}

	private static int getNextElement(int a, int[]array) {
		List<Integer> collect = Arrays.stream(array).boxed().collect(Collectors.toList());
		 for (int j = 0; j < collect.size(); j++) {
			 if(collect.get(j)>a) {
				 	return collect.get(j);
				}
		}
		return 0;
	}

	private static boolean listContainsIntArray(List<int[]> arrayList, int[] tempArr) {
		String str = Arrays.toString(tempArr);
		for(int[]i:arrayList) {
			if(str.equals(Arrays.toString(i))) {
				return true;
			}
		}
		return false;
	}

	private static int getFactorial(int [] array) {
		int result = 1;
		for (int i = 1; i < array.length+1; i++) {
			result = result * i;
		}
		return result;
	}
	
	private static Map<Integer,List<int[]>> getIndexes(Map<Integer, List<Integer>> allNumbers) {
		Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();
		int counter = 0;
		for (Map.Entry<Integer, List<Integer>> entry : allNumbers.entrySet()) {
			List<int[]> tempSet = new ArrayList<>();
			int[]tempArr = new int [entry.getValue().size()];
			for (int i = 0; i < entry.getValue().size(); i++) {
				for (int j = 0; j < tempArr.length; j++) {
					tempArr[j] = j;
				}
			}
			tempSet.add(tempArr);
			map.put(counter, tempSet);
			counter++;
		}
		return map;
	}

	public static void main(String[] args) {	
		Map<Integer, List<Integer>> allNumbers = new HashMap<Integer, List<Integer>>();
		allNumbers.put(1, Arrays.asList(1,2,4));
		allNumbers.put(2, Arrays.asList(0,8));
		allNumbers.put(3, Arrays.asList(1,2,4));
		System.out.println(generateNumbersFromMap(allNumbers));
	}
}
