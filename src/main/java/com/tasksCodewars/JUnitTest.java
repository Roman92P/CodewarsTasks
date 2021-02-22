package com.tasksCodewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {
    private int x = 2;
    private int y = 2;
    private int z = 3;

    boolean a  = (x==y);
    boolean b  = (y==z);

    @Test
    public void givenTwoNumbersShouldBeEqual(){
        assertEquals(y, x);
    }
    @Test
    public void givenTwoNumbersShouldBeNotEqual(){
        assertNotEquals(z, y);
    }

    @Test
    public void givenConditionIsTrue(){
        assertTrue(a);
    }
    
    @Test
    public void givenConditionIsFalse(){
        assertFalse(b);
    }
}
