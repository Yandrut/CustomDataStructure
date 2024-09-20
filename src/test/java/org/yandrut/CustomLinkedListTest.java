package org.yandrut;

import org.yandrut.CustomList.CustomLinkedList;
import org.yandrut.CustomList.CustomList;

public class CustomLinkedListTest {
    public static void main(String[] args) {
        CustomList<String> strings = new CustomLinkedList<>();
        strings.add("first");
        strings.add("second");
        strings.add("third");
        strings.add("fourth");
        strings.add("fifth");
        strings.add("sixth");
        strings.add("seventh");
        strings.add("ninth");

        for (String i : strings) {
            System.out.println(strings.remove(i));
            System.out.println(strings.size());
        }
    }
}
