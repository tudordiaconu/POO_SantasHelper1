package main;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        a.add(5);
        a.add(3);
        a.add(1);
        b.add(2);
        b.add(4);

        System.out.println(a);
        System.out.println(b);
        Collections.reverse(a);
        System.out.println(a);
    }
}
