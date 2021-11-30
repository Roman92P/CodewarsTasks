package com.tasksCodewars;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ObservedPin {
	
	public static void main(String[] args) {
		System.out.println(getPINs("11"));
	}
	
	private static final List<String> keyBoard = 
			Arrays.asList("n","n","n","n","1","2","3","n","4","5","6","n","7","8","9","n","n","0","n","n","n","n");
	
	public static List<String> getPINs(String observed) {
		List<Integer> listOfObservedNumbers = getListOfObservedNumbers(observed);
		Map<Integer, List<Integer>> allNumbers = new LinkedHashMap<Integer, List<Integer>>();
		for(int i = 0; i<listOfObservedNumbers.size(); i++){
			allNumbers.put(i, getPossiblePressButtons(listOfObservedNumbers.get(i)));
		}
		List<String> passwordsList = getPasswordsList(allNumbers,observed.length());
		
	return joinNumbersInEachStr(passwordsList);
	}
	
	private static List<String> joinNumbersInEachStr(List<String> passwordsList) {
		return passwordsList.stream()
				.map(s->s.replaceAll("\\D", ""))
				.sorted()
				.collect(Collectors.toList());	
	}

	public static List<Integer>getListOfObservedNumbers(String observed){
		String[] splited = observed.split("");
		List<Integer> resultIntList = Stream.of(splited)
				.mapToInt(s->Integer.parseInt(s))
				.boxed()
				.collect(toList());
		return resultIntList;
	}

	private static List<Integer> getPossiblePressButtons(Integer number) {
		List<Integer>result = new LinkedList<>();
		result.add(number);
		int leftNumber = getNeighborNumber(number, "left");
		int rightNumber = getNeighborNumber(number, "right");
		int upNumber = getNeighborNumber(number, "up");
		int downNumber = getNeighborNumber(number, "down");
		result.add(leftNumber);
		result.add(rightNumber);
		result.add(upNumber);
		result.add(downNumber);
		result = result.stream().filter(el->el!=10).collect(Collectors.toList());
		return result;
	}
	
	private static List<String> getPasswordsList(Map<Integer, List<Integer>> allNumbers, int numberLength) {
		List<String> result = new LinkedList<>();
		int possibleCombinations = 1;
		for(Map.Entry<Integer, List<Integer>> entry : allNumbers.entrySet()) {
			Integer key = entry.getKey();
			List<Integer> value = entry.getValue();
			possibleCombinations = possibleCombinations * value.size();
		}
		Map<Integer, List<int[]>> indexes = getIndexes(allNumbers);
		List<Integer> indexShifter = getShiftMomentForIndexes(indexes, possibleCombinations);
		int mainCount = 0;
		int mapKeyCount = 1;
		List<Integer> indexesMemList = new LinkedList<>();
		for (int i = 0; i < numberLength; i++) {
			indexesMemList.add(0);
		}
		for (int i = 0; i < possibleCombinations; i++) {
			int [] password = new int [numberLength];
			for (int j = 0; j < password.length; j++) {
				int numberIndex = getNumberIndex
						(mainCount, mapKeyCount, allNumbers, indexShifter, indexesMemList.get(mapKeyCount-1));
				password[j]=allNumbers.get(mapKeyCount-1).get(numberIndex);
				indexesMemList.set(mapKeyCount-1,numberIndex);
				mapKeyCount++;
			}
			mainCount++;
			result.add(Arrays.toString(password));
			mapKeyCount = 1;
		}
		return result;
	}
	
	private static int getNumberIndex
	(int mainCount, int mapKeyCount, Map<Integer, List<Integer>> allNumbers, List<Integer> indexShifter, Integer currentIndex) {
		List<Integer> list = allNumbers.get(mapKeyCount-1);
		Integer integer = indexShifter.get(mapKeyCount-1);
		int index = currentIndex;
		if( mainCount%integer == 0) {
			index = getNextIndex(currentIndex, allNumbers.get(mapKeyCount-1));
		}
		return index;
	}
	
	private static int getNextIndex(Integer currentIndex, List<Integer> list) {
		if(currentIndex==list.size()-1) return 0;
		return currentIndex+1;
	}
	private static List<Integer> getShiftMomentForIndexes(Map<Integer, List<int[]>> indexes, int maxPossibleCombinations) {
		List<Integer> linkedList = new LinkedList<>();
		int partFactorial = 1;
		for(Map.Entry<Integer, List<int[]>>entry : indexes.entrySet()) {
			partFactorial = partFactorial * entry.getValue().get(0).length;
			linkedList.add(maxPossibleCombinations/partFactorial);
		}
		return linkedList;
	}

	private static Map<Integer,List<int[]>> getIndexes(Map<Integer, List<Integer>> allNumbers) {
		Map<Integer, List<int[]>> map = new LinkedHashMap<Integer, List<int[]>>();
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
	
	private static int getNeighborNumber(Integer number, String direction) {
		switch (direction) {
		case "left":
			for (int i = 0; i < keyBoard.size(); i++) {
				if(String.valueOf(number).equals(keyBoard.get(i))) {
					if(!keyBoard.get(i-1).equals("n")) {
						return Integer.parseInt(keyBoard.get(i-1));
					}
				}
			}
			break;
		case "right":
			for (int i = 0; i < keyBoard.size(); i++) {
				if(String.valueOf(number).equals(keyBoard.get(i))) {
					if(!keyBoard.get(i+1).equals("n")) {
						return Integer.parseInt(keyBoard.get(i+1));
					}
				}
			}
			break;
		case "up":
			for (int i = 0; i < keyBoard.size(); i++) {
				if(String.valueOf(number).equals(keyBoard.get(i))) {
					if(!keyBoard.get(i-4).equals("n")) {
						return Integer.parseInt(keyBoard.get(i-4));
					}
				}
			}
			break;
		case "down":
			for (int i = 0; i < keyBoard.size(); i++) {
				if(String.valueOf(number).equals(keyBoard.get(i))) {
					if(!keyBoard.get(i+4).equals("n")) {
						return Integer.parseInt(keyBoard.get(i+4));
					}
				}
			}
			break;
		default:
			break;
		}
		
		return 10;
	}	
} 
