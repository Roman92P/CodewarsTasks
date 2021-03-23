package com.tasksCodewars;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParseStringToInt {

//	In this kata we want to convert a string into an integer. 
//	The strings simply represent the numbers in words.

//Examples:
//
//"one" => 1
//"twenty" => 20
//"two hundred forty-six" => 246
//"seven hundred eighty-three thousand nine hundred and nineteen" => 783919
//Additional Notes:
//
//The minimum number is "zero" (inclusively)
//The maximum number, which must be supported is 1 million (inclusively)
//The "and" in e.g. "one hundred and twenty-four" is optional, 
//	in some cases it's present and in others it's not
//All tested numbers are valid, you don't need to validate them

	public static void main(String[] args) {
		// TODO Auto-generated method stub 757118
//										   700118
//		System.out.print(intparseIntSec("seven hundred fifty seven thousand one hundred eighteen"));
		System.out.print(intparseIntSec("seven hundred thousand"));
		
//		String[]testarr = {"test", "test"};
//		
//		System.out.println(Arrays.toString(removeAnd(testarr)));
	}
	
	public static int intparseIntSec(String numStr) {
			String str = numStr.toLowerCase();
			if (str.equals("zero")) {
				return 0;
			}
			if(str.length()==0) {
				return -1;
			}
	
			String[] simpleNumbers = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	
			String[] tensNumbers = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
					"ninety", };
	
			String[] teenNumbers = { "ten","eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
					"eighteen", "nineteen" };
	
			String[] hundredNumbers = { "hundred", "thousand", "million" };
	
			String[] arr = str.split("\\W");
			
			String[] split = removeAnd(arr);
			
			int result = 0;
			
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < split.length; i++) {
			
				if(split[i].equals("zero")) {
					stringBuilder.append("0");
				}
				
				for (int j = 0; j < simpleNumbers.length; j++) {
					if (split[i].equals(simpleNumbers[j])) {
						stringBuilder.append(j);
					}
				}
				
				for (int k = 0; k < tensNumbers.length; k++) {
					if (split[i].equals(tensNumbers[k])) {
						if (split[split.length - 1].endsWith("y")) {
							stringBuilder.append(k + "0");
						}else {
							stringBuilder.append(k);
						}
					}
				}
				
				for (int l = 0; l < teenNumbers.length; l++) {
					if (split[i].equals(teenNumbers[l])) {
						stringBuilder.append("1" + l);
					}
				}
				
				for (int m = 0; m < hundredNumbers.length; m++) {
					if (split[i].equals(hundredNumbers[m]) && split[split.length - 1].equals("thousand")
							&& split[split.length - 2].equals("hundred")) {
						stringBuilder.append("00");
						break;
					}
					 if (split[i].equals(hundredNumbers[m]) && split[split.length - 1].equals("hundred")) {
						stringBuilder.append("00");
						break;
					}
					 if(split[i].equals(hundredNumbers[m]) && presentIn(simpleNumbers, split[split.length-1])
							&& split[split.length-2].equals("hundred")) {
						stringBuilder.append("0");
						break;
					}
					 if (split[i].equals(hundredNumbers[m]) && split[split.length - 1].equals("thousand")
							&& split[i].equals("thousand")) {
						stringBuilder.append("000");
						break;
					}
					 if (hundredNumbers[m].equals("million") && split[split.length - 1].equals("million")) {
						stringBuilder.append("000000");
						break;
					}
									
				}
			}
		
			String string = stringBuilder.toString();
			try {
			result = Integer.parseInt(string);
			}catch(NumberFormatException e) {
				return 0;
			}
	
			return result;
	}
	
	private static String[] removeAnd(String[] split) {
		List<String> asList = Arrays.asList(split);
		List<String> collect = asList.stream().filter(s->!s.equalsIgnoreCase("and")).collect(Collectors.toList());
		String[]newArr = new String[collect.size()];
		for (int i = 0; i < collect.size(); i++) {
			newArr[i]=collect.get(i);
		}
		return newArr;
		
	}

	public static boolean presentIn(String [] array, String str) {
		for (String s : array) {
			if(s.equals(str)) {
				return true;
			}
		}
		return false;
	}
	
// old one
//	public static int intparseInt(String numStr) {
//		String str = numStr.toLowerCase();
//		if (str.equals("zero")) {
//			return 0;
//		}
//		if(str.length()==0) {
//			return -1;
//		}
//
//		String[] simpleNumbers = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" };
//
//		String[] tensNumbers = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
//				"ninety", };
//
//		String[] teenNumbers = { "","eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
//				"eighteen", "nineteen" };
//
//		String[] hundredNumbers = { "hundred", "thousand", "million" };
//
//		String[] split = str.split("\\W");
//		int result = 0;
//		StringBuilder stringBuilder = new StringBuilder();
//		for (int i = 0; i < split.length; i++) {
//			for (int j = 0; j < simpleNumbers.length; j++) {
//				if (split[i].equals(simpleNumbers[j])) {
//					stringBuilder.append(j);
//				}
//			}
//			for (int k = 0; k < tensNumbers.length; k++) {
//				if (split[i].equals(tensNumbers[k])) {
//					if (split[split.length - 1].endsWith("y")) {
//						stringBuilder.append(k + "0");
//					}else {
//						stringBuilder.append(k);
//					}
//				}
//			}
//			for (int l = 0; l < teenNumbers.length; l++) {
//				if (split[i].equals(teenNumbers[l])) {
//					stringBuilder.append("1" + l);
//				}
//			}
//			for (int m = 0; m < hundredNumbers.length; m++) {
//				if (split[i].equals(hundredNumbers[m])) {
//					if (hundredNumbers[m].equals("hundred") && split[split.length - 1].equals("hundred")) {
//						stringBuilder.append("00");
//					}
//					if (hundredNumbers[m].equals("hundred") && split[split.length - 1].endsWith("en") 
//							&& !split[split.length - 1].equals("seven")) {
//						stringBuilder.append("0");
//					}
//					if (hundredNumbers[m].equals("thousand") && split[split.length - 1].equals("thousand")) {
//						stringBuilder.append("000");
//					}
//					if (hundredNumbers[m].equals("thousand") && split[split.length - 1].endsWith("y")) {
//						stringBuilder.append("0");
//					}
//					if (hundredNumbers[m].equals("thousand") && split[split.length - 1].endsWith("n")
//							&& !split[split.length - 1].equals("seven")) {
//						stringBuilder.append("00");
//					}
//					if (hundredNumbers[m].equals("million") && split[split.length - 1].equals("million")) {
//						stringBuilder.append("000000");
//					}
//				}
//			}
//		}
//		String string = stringBuilder.toString();
//		try {
//		result = Integer.parseInt(string);
//		}catch(NumberFormatException e) {
//			return 0;
//		}
//
//		return result;
//	}

}
