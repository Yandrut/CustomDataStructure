package org.yandrut;

import org.yandrut.CustomList.CustomArrayList;
import org.yandrut.CustomList.CustomList;

public class CustomArrayListTest {
    public static void main(String [] args) {
        long start = System.nanoTime();

        CustomList<String> list = new CustomArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("Object" + i);
        }
        long duration = (System.nanoTime() - start)/1000000;

        System.out.println("Duration: " + duration + " ms.");
        System.out.println("Size before: " + list.size());

        CustomList<String> al = new CustomArrayList<>();
        al.add("One");
        al.add("Two");
        al.add("Three");
        al.add("Four");
        al.add("Five");
        al.add("Six");

        list.addAll(al);
        System.out.println("Size after: " + list.size());

        for (String s : list) {
            System.out.println(s);
        }
    }
}