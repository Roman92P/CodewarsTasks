package com.tasksCodewars;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class SnailTest {

	public static void main(String[] args) {
		
//		int one[] =  { 1, 2, 3, 9};
//		int two[] =  { 4, 5, 6, 4};
//		int three[] = {7, 8, 9, 1};
//		int four[] = { 1, 2, 3, 4};
		
		int one[] =  { 1, 2, 3};
		int two[] =  { 4, 5, 6};
		int three[] = {7, 8, 9};
		int four[] = { 1, 2, 3};
	
		
		int[][] array = { one, two, three, four};
		
		int[] snail = snail(array);

		for (int i : snail) {
			System.out.print(i);
		}
		
	}

	private static int[] snail(int[][] array) {
		int[] result = {};
		int[][] temp = Arrays.copyOf(array, array.length);
		int laps = findNumberOfLaps(temp);
		for (int i = 0; i < laps; i++) {
			int[] firstLine = getFirstLine(temp);
			temp = ArrayUtils.remove(temp, 0);
			int[] lastReversedLine = getLastReversedLine(temp);
			temp = removeLastLine(temp);
			int[] rightSide = getRightSide(temp);
			temp = deleteRightSide(temp);
			int[] leftReverseSide = getLeftReverseSide(temp);
			temp = deleteLeftSide(temp);

			int[] stepOne = ArrayUtils.addAll(firstLine, rightSide);
			int[] stepTwo = ArrayUtils.addAll(stepOne, lastReversedLine);
			int[] addAll = ArrayUtils.addAll(stepTwo, leftReverseSide);

			result = ArrayUtils.addAll(result, addAll);
		}

		return result;
	}

	private static int[][] removeLastLine(int[][] temp) {
		if(temp.length>1) {
			temp = ArrayUtils.remove(temp, temp.length - 1);
		}
		return temp;
	}

	private static int findNumberOfLaps(int[][] array) {
		int result = 0;
		int length = array.length;
		if (length % 2 == 0) {
			result = length / 2;
		}
		if (length % 2 != 0) {
			result = length / 2 + 1;
		}
		return result;
	}

	private static int[][] deleteRightSide(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			int arrILenght = array[i].length;
			for (int j = 0; j < array[i].length; j++) {
				if (j == arrILenght-1) {
					array[i] = ArrayUtils.remove(array[i], j);
				}
			}
		}
		return array;
	}

	private static int[][] deleteLeftSide(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (j == 0) {
					array[i] = ArrayUtils.removeElement(array[i], array[i][j]);
				}
			}
		}
		return array;
	}

	private static int[] getLeftReverseSide(int[][] array) {
		int[] result = {};
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (j== 0) {
					result = addElToArray(result, array[i][j]);
				}
			}
		}
		ArrayUtils.reverse(result);
		return result;
	}

	private static int[] getRightSide(int[][] array) {
		int[] result = {};
		for (int i = 0; i < array.length; i++) {
			int arrILength = array[i].length;
			for (int j = 0; j < array[i].length; j++) {
				if (j==arrILength-1) {
					result = ArrayUtils.add(result, array[i][j]);
				}
				
			}
		}
		return result;
	}

	private static int[] getLastReversedLine(int[][] array) {
		int[] arr = {};
		if (array.length > 1) {
			arr = array[array.length - 1];
			ArrayUtils.reverse(arr);
		}
		return arr;
	}

	private static int[] getFirstLine(int[][] array) {
		return array[0];
	}

	private static int[] addElToArray(int[] arr, int el) {
		int[] newArr = Arrays.copyOf(arr, arr.length + 1);
		newArr[newArr.length - 1] = el;
		return newArr;
	}
}
