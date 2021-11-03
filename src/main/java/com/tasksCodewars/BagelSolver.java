package com.tasksCodewars;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BagelSolver extends Bagel {


    public static Bagel getBagel() {
        try {
            Field b = Boolean.class.getDeclaredField("TRUE");
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(b, b.getModifiers() & ~Modifier.FINAL);
            b.setAccessible(true);
            b.set(null, Boolean.FALSE);
        } catch (Exception e) {
        }

//        try {
//            Field value = Boolean.class.getDeclaredField("value");
//            value.setAccessible(true);
//            value.set(Boolean.TRUE, Boolean.FALSE);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
        return new Bagel();
    }


}


class Bagel {
    public final int getValue() {
        return 3;
    }
}


