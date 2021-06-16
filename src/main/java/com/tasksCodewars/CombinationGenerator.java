package com.tasksCodewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//I    II   II   III  III  III
//1234 1342 1423 1243 1324 1432 //123 132
//2341 2413 2134 2314 2431 2143 //231 213
//3412 3124 3241 3421 3124 3214 //312 321
//4123 4231 4312 4132 4213 4321

//1234 1243 1342 1324 1423 1432

public class CombinationGenerator {
	public static void main(String[] args) {
		
		String str = "1234";

        int mustListSize = howManyPossibleComboCanBe(str);
        List<String> strings = allPossibleCombinatious(str, mustListSize);
        System.out.println(strings);

	}

	private static List<String> allPossibleCombinatious(String str, int mustListSize) {
		List<String> strings;
		strings = combo(str,1);
        int lap = 2;
        while (strings.size()<mustListSize){
            for (int i = 0; i < strings.size(); i++) {
                String s = strings.get(i).replaceAll("[\\[\\]\\s,]", "");
                List<String> combo = combo(s, lap);
                for (int j = 0; j < combo.size(); j++) {
                    if(!strings.contains(combo.get(j))){
                        strings.add(combo.get(j));
                    }
                }

            }
            lap = lap+1;
        }
		return strings;
	}

	private static List<String> combo(String strForComboRecurs, int lap) {
        List<String> primesNumbers = genPrimes(strForComboRecurs);
        List<String> result = new ArrayList<>();
        result.addAll(primesNumbers);
        for (String primesNumber : primesNumbers) {
            String primeString = primesNumber.replaceAll("[\\[\\]\\s,]", "");
            String startStr = primeString.substring(0, lap);
            List<Integer> collect1 = Arrays.stream(startStr.split(""))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            String toCombine = primeString.substring(lap, primeString.length());
            int i = howManyPossibleComboCanBe(toCombine);
            List<Integer> tempList = null;
            for (int j = 0; j < i; j++) {
                List<Integer> collect = Arrays.stream(toCombine.split("")).collect(Collectors.toList())
                        .stream().mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());
                tempList = new ArrayList<>();
                tempList.addAll(collect1);
                for (int k = 0; k < collect.size(); k++) {
                    if (isMaxNumber(collect, collect.get(k))) {
                        tempList.add(getMinValFromList(collect));
                    } else {
                        tempList.add(getNextGreaterNumber(collect, collect.get(k)));
                    }
                }
                String s = tempList.toString().replaceAll("[\\]\\[,\\s]", "");
                toCombine = s.substring(collect1.size());
                if (!result.contains(tempList.toString())) {
                    result.add(tempList.toString());
                }
            }
        }
        return result;
    }
    private static List<String> genPrimes(String str) {
        List<String> result = new ArrayList<>();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            List<Integer> collect = Arrays.stream(str.split("")).collect(Collectors.toList())
                    .stream().mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            while (true) {
                List<Integer> tmpList = new ArrayList<>();
                for (int j = 0; j < collect.size(); j++) {
                    if (!isMaxNumber(collect, collect.get(j))) {
                        tmpList.add(collect.get(j) + 1);
                    } else {
                        tmpList.add(getMinValFromList(collect));
                    }
                }
                if (result.contains(tmpList.toString())) {
                    break;
                }
                result.add(tmpList.toString());
                collect = tmpList;
            }
        }
        return result;
    }
    private static int getMinValFromList(List<Integer> listIndexModel) {
        return listIndexModel
                .stream()
                .mapToInt(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
    }

    public static int howManyPossibleComboCanBe(String indexesStr) {
        int result = 1;
        for (int i = 1; i < indexesStr.length() + 1; i++) {
            result = result * i;
        }
        return result;
    }

    private static Integer getNextGreaterNumber(List<Integer> collect, Integer integer) {
        do {
            integer = integer + 1;
        } while (!collect.toString().contains(String.valueOf(integer)));
        return integer;
    }

    private static boolean isMaxNumber(List<Integer> list, int toCheckNumber) {
        for (int a : list) {
            if (a > toCheckNumber) {
                return false;
            }
        }
        return true;
    }
}
