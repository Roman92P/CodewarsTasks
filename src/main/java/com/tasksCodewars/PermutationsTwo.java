package com.tasksCodewars;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PermutationsTwo {

	public static void main(String[] args) {
		
		 
		// 1234 //2134 //3124 //4123   //12345 //1234
		// 1342 //2341 //3241 //4231   //13452
		// 1423 //2413 //3412 //4312   //14523 
		// ___________________________ //15234
		// 1243 //2143 //3142 //4132   
		// 1324 //2314 //3214 //4213
		// 1432 //2431 //3421 //4321
		// 24 combo

		// 1234 //2134 //3124 //4123  
		// 1243 //2143 //3142 //4132
		// 1324 //2314 //3214 //4213
		// 1342 //2341 //3241 //4231
		// 1423 //2413 //3412 //4312
		// 1432 //2431 //3421 //4321
		
		// 9    // 9   //18   //9
		// 18   // 27  //27   //18
		// 9    // 18  //9    //9
		
		//12345
		//13452
		//14523
		//15234
		
		
		
		String testStr = "aabb";

		// convert string into list
		String[] split = testStr.split("");
		List<String> collect = Stream.of(split).collect(Collectors.toList());
		// list size
		int c = collect.size();
		// create map with indexes
		int count = 1;
		Map<String, String> strIndexMap = new LinkedHashMap<>();
		for (int i = 0; i < collect.size(); i++) {
			strIndexMap.put(String.valueOf(count), collect.get(i));
			count++;
		}
		// generate all possible combinations of key places
		Set<String> keySet = strIndexMap.keySet();
		String keySetStr = String.valueOf(keySet).replaceAll("[\\[\\],\\s]", "");
		List<String> indexes = new ArrayList<>();
		// how many possible combination
		int combinations = 1;
		for (String string : keySet) {
			combinations = Integer.parseInt(string) * combinations;
		}

	}

	private static String generateNextIndexesString(String tempStr, int index) {
		List<String> result = new ArrayList<>();

		return null;
	}
}
