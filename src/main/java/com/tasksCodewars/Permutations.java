package com.tasksCodewars;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Permutations {
	public static void main(String[] args) {
		
		
		//input string
		String testStr = "aabb";
		//convert string into list
		String[] split = testStr.split("");
		List<String> collect = Stream.of(split).collect(Collectors.toList());
		//list size
		int c = collect.size();
		// create map with indexes
		int count = 0;
		Map<String, String> strIndexMap = new LinkedHashMap<>();
		for (int i = 0; i < collect.size(); i++) {
			strIndexMap.put(String.valueOf(count), collect.get(i));
			count++;
		}
		//generate all possible combinations of key places
		Set<String> keySet = strIndexMap.keySet();
		String keySetStr = String.valueOf(keySet).replaceAll("[\\[\\],\\s]", "");
		System.out.println("Keyset:"+keySetStr);
		List<String> indexes = new ArrayList<>();
		
		for (int i = 0; i < c; i++) {
			String firstRowIndexString = createNextFirstRowIndexes(c, i).replaceAll("[\\[\\],\\s]", "");
			indexes.add(firstRowIndexString);
			String str2="";
			while(!indexes.contains(str2)) {
				String str=firstRowIndexString;
				String nextIndexesStr = generateNewIndexesString(str, i);
				str = nextIndexesStr;
				indexes.add(str);
				str2 = nextIndexesStr;

			}
		}
		
		for(String s : indexes) {
			System.out.print(s+"\n");
		}


	}

	private static String generateNewIndexesString(String tempStr, int index) {
		char[] charArray = tempStr.toCharArray();
		char[] result = new char[charArray.length];
		result[0] = String.valueOf(index).toCharArray()[0];
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] != String.valueOf(index).toCharArray()[0]) {
				if (Integer.parseInt(String.valueOf(charArray[i])) < tempStr.length()) {
					int parseInt = Integer.parseInt(String.valueOf(charArray[i]));
					parseInt = parseInt + 1;
					result[i] = (String.valueOf(parseInt).toCharArray()[0]);
				}
				if (Integer.parseInt(String.valueOf(charArray[i])) == tempStr.length() - 1) {
					int parseInt = Integer.parseInt(String.valueOf(charArray[i]));
					parseInt = index + 1;
					result[i] = (String.valueOf(parseInt).toCharArray()[0]);
				}

			}
		}
		return String.valueOf(result);
	}
	
	private static String createNextFirstRowIndexes(int listSize, int currentIndex) {
		List<Integer>arr = new ArrayList<Integer>();
		arr.add(currentIndex);
		for (int i = 1; i <listSize; i++) {
			if(i == currentIndex) {
				arr.add(0);
			}
			arr.add(i);
		}
		arr = arr.stream().distinct().collect(Collectors.toList());
		return arr.toString();
	}

}
