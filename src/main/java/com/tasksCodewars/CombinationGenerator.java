package com.tasksCodewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


public class CombinationGenerator {
	public static void main(String[] args) {
		String str="abcdefgho";
		long start = System.currentTimeMillis();
        Set<String> result = generateStringsCombination(str);
        long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("Spend time: "+timeElapsed);
		System.out.println(result.size());
//		for(String s : result) {
//			System.out.println(s);
//		}
       
	}
	

	private static Set<String> generateStringsCombination(String str){
		String testStr  = str.replaceAll("[\\[\\],\\s]", "");
		Set<String> strSet = new HashSet<>();
		if(testStr.length()==1) {
			return Arrays.asList(str).stream().collect(Collectors.toSet());
		}
		else if(testStr.length()>0) {
			Map<Integer,String> strMap = new HashMap<>();
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 1; i < str.length()+1; i++) {
				stringBuilder.append(i);
				strMap.put(i, String.valueOf(str.charAt(i-1)));
			}
			String string = stringBuilder.toString();
			int mustListSize = howManyPossibleComboCanBe(string);
			Set<String> strings = allPossibleCombinatious(string, mustListSize);
        
			Set<int[]>indexArraysList = new HashSet<>();
        
			for(String s : strings) {
				int[] addIndexesToArray = addIndexesToArray(s.replaceAll("[\\[\\],\\s]", ""));
				indexArraysList.add(addIndexesToArray);
			}
			
        
			for(int[]indexes : indexArraysList) {
				StringBuilder stringBuilder2 = new StringBuilder();
				for (int i = 0; i < indexes.length; i++) {
					String mapVal = strMap.get(indexes[i]);
					stringBuilder2.append(mapVal);
				}
				strSet.add(stringBuilder2.toString());
			}
        
		}
		return strSet;
	}

	/*
	 * return List<String> with all possible combination of indexes
	 */
	private static Set<String> allPossibleCombinatious(String str, int mustListSize) {
		Set<String> strings = combo(str,1);
        int lap = 2;
        while (strings.size()<mustListSize){
        	Set<String> tempSet = new HashSet<>();
        	for (Iterator<String> it = strings.iterator(); it.hasNext(); ) {
        			String nextStr = it.next().replaceAll("[\\[\\]\\s,]", "");
        			tempSet.addAll(combo(nextStr, lap));		
            }
        	strings.addAll(tempSet);
        	
//            for (int i = 0; i < strings.size(); i++) {
//                String s = strings.get(i).replaceAll("[\\[\\]\\s,]", "");
//                Set<String> combo = combo(s, lap);
//                for (int j = 0; j < combo.size(); j++) {
//                    if(!strings.contains(combo.get(j))){
//                        strings.add(combo.get(j));
//                    }
//                }
//
//            }
            lap = lap+1;
        }
		return strings;
	}

	/*
	 * return List<String> 
	 */
	private static Set<String> combo(String strForComboRecurs, int lap) {
        Set<String> primesNumbers = genPrimes(strForComboRecurs);
        Set<String> result = new HashSet<>();
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
	/*
	 * function to get first possible numbers
	 * return List<String> with first possible indexes
	 */
    private static Set<String> genPrimes(String str) {
        Set<String> result = new HashSet<>();
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
    // auxiliary methods
    private static int[] addIndexesToArray(String s) {
		int[]indexArr = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			String x = String.valueOf(s.charAt(i));
			indexArr[i] = Integer.parseInt(x);
		}
		return indexArr;
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
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//class Permutations {
//    
//    public static List<String> singlePermutations(String str) {
//    String testStr  = str.replaceAll("[\\[\\],\\s]", "");
//		Set<String> strSet = new HashSet<>();
//      if(testStr.length()==1) {
//			return Arrays.asList(str);
//		}
//		if(testStr.length()>0) {
//		Map<Integer,String> strMap = new HashMap<>();
//		StringBuilder stringBuilder = new StringBuilder();
//		for (int i = 1; i < str.length()+1; i++) {
//			stringBuilder.append(i);
//			strMap.put(i, String.valueOf(str.charAt(i-1)));
//		}
//		
//		String string = stringBuilder.toString();
//	
//		int mustListSize = howManyPossibleComboCanBe(string);
//        List<String> strings = allPossibleCombinatious(string, mustListSize);
//        
//        List<int[]> indexArraysList = new ArrayList<>();
//        
//        for(String s : strings) {
//        	System.out.println(s.replaceAll("[\\[\\],\\s]", "")+"\n");
//        	int[] addIndexesToArray = addIndexesToArray(s.replaceAll("[\\[\\],\\s]", ""));
//        	indexArraysList.add(addIndexesToArray);
//        }
//        
//        for(int[]indexes : indexArraysList) {
//        	StringBuilder stringBuilder2 = new StringBuilder();
//        	for (int i = 0; i < indexes.length; i++) {
//        		String mapVal = strMap.get(indexes[i]);
//				stringBuilder2.append(mapVal);
//			}
//        	strSet.add(stringBuilder2.toString());
//        }
//        
//		}
//		return strSet.stream().collect(Collectors.toList());
//  }
//  private static List<String> allPossibleCombinatious(String str, int mustListSize) {
//		List<String> strings;
//		strings = combo(str,1);
//        int lap = 2;
//        while (strings.size()<mustListSize){
//            for (int i = 0; i < strings.size(); i++) {
//                String s = strings.get(i).replaceAll("[\\[\\]\\s,]", "");
//                List<String> combo = combo(s, lap);
//                for (int j = 0; j < combo.size(); j++) {
//                    if(!strings.contains(combo.get(j))){
//                        strings.add(combo.get(j));
//                    }
//                }
//
//            }
//            lap = lap+1;
//        }
//		return strings;
//	}
//
//	private static List<String> combo(String strForComboRecurs, int lap) {
//        List<String> primesNumbers = genPrimes(strForComboRecurs);
//        List<String> result = new ArrayList<>();
//        result.addAll(primesNumbers);
//        for (String primesNumber : primesNumbers) {
//            String primeString = primesNumber.replaceAll("[\\[\\]\\s,]", "");
//            String startStr = primeString.substring(0, lap);
//            List<Integer> collect1 = Arrays.stream(startStr.split(""))
//                    .mapToInt(Integer::parseInt)
//                    .boxed()
//                    .collect(Collectors.toList());
//            String toCombine = primeString.substring(lap, primeString.length());
//            int i = howManyPossibleComboCanBe(toCombine);
//            List<Integer> tempList = null;
//            for (int j = 0; j < i; j++) {
//                List<Integer> collect = Arrays.stream(toCombine.split("")).collect(Collectors.toList())
//                        .stream().mapToInt(Integer::parseInt)
//                        .boxed()
//                        .collect(Collectors.toList());
//                tempList = new ArrayList<>();
//                tempList.addAll(collect1);
//                for (int k = 0; k < collect.size(); k++) {
//                    if (isMaxNumber(collect, collect.get(k))) {
//                        tempList.add(getMinValFromList(collect));
//                    } else {
//                        tempList.add(getNextGreaterNumber(collect, collect.get(k)));
//                    }
//                }
//                String s = tempList.toString().replaceAll("[\\]\\[,\\s]", "");
//                toCombine = s.substring(collect1.size());
//                if (!result.contains(tempList.toString())) {
//                    result.add(tempList.toString());
//                }
//            }
//        }
//        return result;
//    }
//    private static List<String> genPrimes(String str) {
//        List<String> result = new ArrayList<>();
//        int length = str.length();
//        for (int i = 0; i < length; i++) {
//            List<Integer> collect = Arrays.stream(str.split("")).collect(Collectors.toList())
//                    .stream().mapToInt(Integer::parseInt)
//                    .boxed()
//                    .collect(Collectors.toList());
//            while (true) {
//                List<Integer> tmpList = new ArrayList<>();
//                for (int j = 0; j < collect.size(); j++) {
//                    if (!isMaxNumber(collect, collect.get(j))) {
//                        tmpList.add(collect.get(j) + 1);
//                    } else {
//                        tmpList.add(getMinValFromList(collect));
//                    }
//                }
//                if (result.contains(tmpList.toString())) {
//                    break;
//                }
//                result.add(tmpList.toString());
//                collect = tmpList;
//            }
//        }
//        return result;
//    }
//    private static int[] addIndexesToArray(String s) {
//		int[]indexArr = new int[s.length()];
//		for (int i = 0; i < s.length(); i++) {
//			String x = String.valueOf(s.charAt(i));
//			indexArr[i] = Integer.parseInt(x);
//		}
//		return indexArr;
//	}
//    private static int getMinValFromList(List<Integer> listIndexModel) {
//        return listIndexModel
//                .stream()
//                .mapToInt(v -> v)
//                .min().orElseThrow(NoSuchElementException::new);
//    }
//
//    public static int howManyPossibleComboCanBe(String indexesStr) {
//        int result = 1;
//        for (int i = 1; i < indexesStr.length() + 1; i++) {
//            result = result * i;
//        }
//        return result;
//    }
//
//    private static Integer getNextGreaterNumber(List<Integer> collect, Integer integer) {
//        do {
//            integer = integer + 1;
//        } while (!collect.toString().contains(String.valueOf(integer)));
//        return integer;
//    }
//
//    private static boolean isMaxNumber(List<Integer> list, int toCheckNumber) {
//        for (int a : list) {
//            if (a > toCheckNumber) {
//                return false;
//            }
//        }
//        return true;
//    }
//}
