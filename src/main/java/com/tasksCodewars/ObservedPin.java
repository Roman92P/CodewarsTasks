package com.tasksCodewars;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;


public class ObservedPin {
	
	private static final List<Integer> keyBoard = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
	
	public static List<Integer>getsortedIntegers(String observed){
		List<String> asList = Arrays.asList(observed);
		String[] splited = observed.split("");
		List<Integer> resultIntList = Stream.of(splited)
				.mapToInt(s->Integer.parseInt(s))
				.boxed()
				.sorted()
				.collect(toList());
		return resultIntList;
	}

	public static List<String> getPINs(String observed) {
		List<Integer> sortedPossibleIntegers = getsortedIntegers(observed);
		Map<Integer, List<Integer>> allNumbers = new HashMap();
		for(int i = 0; i<sortedPossibleIntegers.size(); i++){
			allNumbers.put(i, getPossiblePressButtons(sortedPossibleIntegers.get(i)));
		}
		
	       return null;
	} 
	
//	┌───┬───┬───┐
//	│ 1 │ 2 │ 3 │
//	├───┼───┼───┤
//	│ 4 │ 5 │ 6 │
//	├───┼───┼───┤
//	│ 7 │ 8 │ 9 │
//	└───┼───┼───┘
//	    │ 0 │
//	    └───┘
	
	private static List<Integer> getPossiblePressButtons(Integer number) {
		List<Integer>result = new ArrayList<>();
		result.add(number);
		if(keyBoard.contains(number-3) && number-3>0) {
			result.add(number-3);
		}
		if(keyBoard.contains(number-1) && number-3>0) {
			result.add(number-1);
		}
		if(keyBoard.contains(number+1) && number !=0) {
			result.add(number+1);
		}
		if(keyBoard.contains(number+3) &&number !=0) {
			result.add(number+3);
		}
		if(number+3==11) {
			result.add(0);
		}
		if(number-3==-3) {
			result.add(8);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(getPINs("8"));
	}
	

} 
