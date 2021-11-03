package com.tasksCodewars;

import org.junit.Test;

import static org.junit.Assert.*;

public class BagelSolverTest {

    @Test
    public void testBagel() {
        Bagel bagel = BagelSolver.getBagel();
        System.out.println(bagel.getValue());
        org.junit.Assert.assertEquals(
                bagel.getValue() == 4,
                java.lang.Boolean.TRUE
        );
    }
}