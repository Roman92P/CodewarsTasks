package com.tasksCodewars;

public class ConwayLife {

	public static final int ALIVE = 1;
	public static final int DEAD = 0;
	
	public static void main(String[] args) {
		
		int[]cellsOne =   {1,1,1};
		int[]cellsTwo =   {0,1,0};
		int[]cellsThree = {1,1,0};
		int[]cellsFour =  {0,0,0};
		int[]cellsFive =  {1,1,1};
		
		int[][]cells = {cellsOne, cellsTwo, cellsThree, cellsFour, cellsFive};
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				System.out.print(cells[i][j]);
			}
			System.out.println("\n");
		}

	}
	public static int[][] getGeneration(int[][] cells, int generations) {
	    return null;
	  }
}
