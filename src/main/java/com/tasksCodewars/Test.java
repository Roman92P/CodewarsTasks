package com.tasksCodewars;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
	public static void main(String[] args) {

		String testStr = "3412";
		//3412
		//3124
		//3241
		
		//1234
		//1342
		//1423
	    List<String> generateCombo = generateCombo(testStr);
	    generateCombo.stream().forEach(System.out::println);
	}

	private static List<String> generateCombo(String testStr) {
		List<String>result = new ArrayList<>();
		
		while(true) {
			List<Integer> tempForAdding = Stream.of(testStr.split("")).map(el -> Integer.parseInt(el))
					.collect(Collectors.toList());
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(tempForAdding.get(0));
			
			for (int j = 1; j < tempForAdding.size(); j++) {
				int a = tempForAdding.get(j);
				if(a+1>tempForAdding.get(0))
				stringBuilder.append(tempForAdding.get(0)+1);
			}
			if(result.contains(stringBuilder.toString())) break;
			result.add(stringBuilder.toString());
			testStr = stringBuilder.toString();
		}
		
		return result;
	}
	
//	private static List<String> generateCombo2(String testStr) {
//		List<String>result = new ArrayList<>();
//		
//		while(true) {
//			List<Integer> tempForAdding = Stream.of(testStr.split("")).map(el -> Integer.parseInt(el))
//					.collect(Collectors.toList());
//			StringBuilder stringBuilder = new StringBuilder();
//			stringBuilder.append(tempForAdding.get(0));
//			
//			for (int j = 1; j < tempForAdding.size(); j++) {
//				if (tempForAdding.get(j) < tempForAdding.size()
//						&& tempForAdding.get(j) + 1 != tempForAdding.get(0)) {
//					int num = tempForAdding.get(j) + 1;
//					stringBuilder.append(String.valueOf(num));
//				}
//				if (tempForAdding.get(j) == tempForAdding.size()) {
//					int num2 = tempForAdding.get(0) + 1;
//					stringBuilder.append(num2);
//				}
//				if (tempForAdding.get(j) + 1 == tempForAdding.get(0)) {
//					stringBuilder.append("1");
//				}
//			}
//			if(result.contains(stringBuilder.toString())) break;
//			result.add(stringBuilder.toString());
//			testStr = stringBuilder.toString();
//		}
//		
//		return result;
//	}
}
