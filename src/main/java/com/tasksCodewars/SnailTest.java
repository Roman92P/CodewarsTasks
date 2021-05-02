package com.tasksCodewars;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class SnailTest {

	public static void main(String[] args) {

		int one[] = {   1, 2,  3,  4 };
		int two[] = {   5, 6,  7,  8 };
		int three[] = { 9, 10, 11, 12};
		int four[] = { 13, 14, 15, 16};
		int five[] = { 17, 18, 19, 20};

		int[][] array = { one, two, three, four, five };
		
//		array = deleteRightSide(array);
//		
//		System.out.println(array[0][2]);
//		System.out.println(array[1][2]);
//		System.out.println(array[2][2]);
//		System.out.println(array[3][2]);
//		System.out.println(array[4][2]);

		int[] snail = snail(array);

		for (int i : snail) {
			System.out.print(i);
		}
	}
	
	private static int[] snail(int[][] array) {
		int[]result= {};
		int[][] temp = Arrays.copyOf(array, array.length);
		int laps = temp.length/2;
		for (int i = 0; i < laps; i++) {
			int[] firstLine = getFirstLine(temp);
			temp = ArrayUtils.remove(temp, 0);
			int[] lastReversedLine = getLastReversedLine(temp);
			temp = ArrayUtils.remove(temp, temp.length-1);
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

	private static int[][] deleteRightSide(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (ArrayUtils.indexOf(array[i], array[i][j]) == array[i].length-1) {
					array[i] = ArrayUtils.removeElement(array[i], array[i][j]);
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
				if (ArrayUtils.indexOf(array[i], array[i][j]) == 0) {
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
			for (int j = 0; j < array[i].length; j++) {
				if (ArrayUtils.indexOf(array[i], array[i][j]) == array[i].length - 1) {
					result = addElToArray(result, array[i][j]);
				}
			}
		}
		return result;
	}

	private static int[] getLastReversedLine(int[][] array) {
		int[] arr = array[array.length - 1];
		ArrayUtils.reverse(arr);
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
