/* Пусть дан произвольный список целых чисел.
1) Нужно удалить из него чётные числа
2) Найти минимальное значение
3) Найти максимальное значение
4) Найти среднее значение*/

package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Homework3 {
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();

        for (int i = 0; i < 21; i++) {
            myList.add(new Random().nextInt(100));
        }
        System.out.println(myList);

        ArrayList<Integer> oddNumbersList = deleteEvenNumbers(myList);
        System.out.println("Нечетные элементы списка: " + oddNumbersList);

        int minValue = findMinimalValue(myList);
        System.out.println("Минимальное значение в списке: " + minValue);

        int maxValue = findMaximalValue(myList);
        System.out.println("Максимальное значение в списке: " + maxValue);

        int averageValue = findAverageValue(myList);
        System.out.println("Среднее значение в списке: " + averageValue);
    }

    public static ArrayList<Integer> deleteEvenNumbers (ArrayList<Integer> list) { // метод по удалению четных чисел из списка
        ArrayList<Integer> list1 = new ArrayList<>();
        for (Integer integer : list) {
            if (integer % 2 != 0) list1.add(integer);
        }
        return list1;
    }

    public static Integer findMinimalValue (ArrayList<Integer> list) { // метод по нажождению минимального значения в списке
        list.sort(Comparator.naturalOrder());
        return list.get(0);
    }

    public static Integer findMaximalValue (ArrayList<Integer> list) { // метод по нажождению максимального значения в списке
        list.sort(Comparator.reverseOrder());
        return list.get(0);
    }

    public static Integer findAverageValue (ArrayList<Integer> list) { // метод по нажождению среднего значения в списке
        int sumValue = 0;
        for (Integer integer : list) {
            sumValue += integer;
        }
        return sumValue / list.size();
    }
}
