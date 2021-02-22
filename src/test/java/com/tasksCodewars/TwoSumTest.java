package com.tasksCodewars;

import org.junit.Assert;
import org.junit.Test;


public class TwoSumTest {

    @Test
    public void doTest() {
        doTest(new int[]{1,2,3},          new int[]{0,2});
        doTest(new int[]{1234,5678,9012}, new int[]{1,2});
        doTest(new int[]{2,2,3},          new int[]{0,1});
    }

    private void doTest(int[] numbers, int[] expected) {
        int target = numbers[expected[0]] + numbers[expected[1]];
        int[] actual = TwoSum.twoSum(numbers, target);
        if ( null == actual )
        {
            System.out.format("Received a null\n");
            Assert.assertNotNull(actual);
        }
        if ( actual.length != 2 )
        {
            System.out.format("Received an array that's not of length 2\n");
            Assert.assertTrue(false);
        }
        int received = numbers[actual[0]] + numbers[actual[1]];
        Assert.assertEquals(target, received);
    }
}