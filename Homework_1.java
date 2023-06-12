/* Первый семинар.
1. Выбросить случайное целое число в диапазоне от 0 до 2000 и сохранить в i
2. Посчитать и сохранить в n номер старшего значащего бита выпавшего числа
3. Найти все кратные n числа в диапазоне от i до Short.MAX_VALUE сохранить в массив m1
4. Найти все некратные n числа в диапазоне от Short.MIN_VALUE до i и сохранить в массив m2

Пункты реализовать в методе main
*Пункты реализовать в разных методах
int i = new Random().nextInt(k); //это кидалка случайных чисел!)*/

package org.example;

import java.util.Arrays;
import java.util.Random;

public class Homework_1 {
    public static void main(String[] args) {
        int i = new Random().nextInt(2000) + 1;
        System.out.println(i);

        int n = msb(i);
        System.out.println(n);

        int[] m1 = array_1(i, n);
        System.out.println(Arrays.toString(m1));

        int[] m2 = array_2(i, n);
        System.out.println(Arrays.toString(m2));
    }

    public static int msb(int i) {    // метод по нахождению старшего значащего бита числа
        String a = Integer.toBinaryString(i);
        return a.length();
    }

    public static int[] array_1 (int k, int n) {    // метод по созданию массива, в котором все числа в диапазоне от i до Short.MAX_VALUE (включая i и Short.MAX_VALUE) кратны n
        int size = (Short.MAX_VALUE - k + n) / n; // находим длину массива
        int a = 0;
        for (int i = k; i < k + n; i++) { // находим в заданном диапазоне первое число, кратное n
            if (i % n == 0) a = i;
        }
        int[] arr = new int[size]; // создаем массив размером size
        arr[0] = a; // первым элементом будет число а
        for (int i = 1; i < size; i++) { // цикл по заполнению массива числами, кратными n
            arr[i] = arr[i - 1] + n;
        }
        return arr;
    }

    public static int[] array_2 (int k, int n) {    // метод по созданию массива, в котором все числа в диапазоне от Short.MIN_VALUE до i (включая i и Short.MIN_VALUE) некратны n
        int size = (k - Short.MIN_VALUE + 1) - (k - Short.MIN_VALUE + n) / n; // находим длину массива
        int[] arr = new int[size]; // создаем массив размером size
        for (int i = 0, j = Short.MIN_VALUE; i < size; i++, j++) { // цикл по заполнению массива числами, некратными n
            if (j % n == 0) j++;
            arr[i] = j;
        }
        return arr;
    }
}