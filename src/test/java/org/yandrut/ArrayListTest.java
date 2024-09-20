package org.yandrut;

import java.util.ArrayList;

public class ArrayListTest {
    public static void mainT() {
        long start = System.nanoTime();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("Object" + i);
        }
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
        long duration = (System.nanoTime() - start)/1000000;
        System.out.println("Ended in: " + duration + " ms.");
    }
}
