package com.example.demo;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        boolean check = setAlarm(true, false);
        System.out.println(check);
        int[] ls ={0, 1, 2, 3, 4 };
        System.out.println(sumParts(ls));
    }

    public static int[] sumParts(int[] ls) {
        int sum = 0, parts[] = new int[ls.length + 1];
        for (int i = parts.length - 2; i >= 0; i--) {
            parts[i] = sum += ls[i];
            System.out.println(parts[i]);
        }
        return parts;
    }

    public static boolean setAlarm(boolean employed, boolean vacation) {
       if (employed == true && vacation == false) {
           return true;
       } return false;
    }

    public static int sum(int[] arr){
        int sum = Arrays.stream(arr)
              .filter(a -> a > 0)
              .sum();

        return sum;
    }
}
