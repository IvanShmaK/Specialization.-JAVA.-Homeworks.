/*
    Реализуйте структуру телефонной книги с помощью HashMap.
Программа также должна учитывать, что во входной структуре будут повторяющиеся имена с разными телефонами, их необходимо
считать, как одного человека с разными телефонами. Вывод должен быть отсортирован по убыванию числа телефонов.
*/


package org.example.Homework5;

import java.util.*;

public class Homework5 {
    public static void main(String[] args) {

        Map<String, String> phoneBook = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        String menu = """
                1. Ввести данные пользователей.
                2. Вывод данных пользователей, отсортированных по убыванию числа телефонов.
                3. Выход.""";
        System.out.println(menu);

        while (!sc.hasNext("3")) {
            if (sc.hasNext("1")) {
                while (!sc.hasNext("2")) {
                    inputData().forEach((key, value) -> phoneBook.merge(key, value, (v1, v2) -> v1.equalsIgnoreCase(v2) ? v1 : v1 + ", " + v2));
                    // phoneBook дополняется значениями из введенных данных
                    System.out.println("Введите '1' для продолжения ввода данных, либо '2' для выхода");
                    sc.next();
                }
                System.out.println("\n" + menu);
                sc.next();
            } else if (sc.hasNext("2")) {
                ArrayList<String> list = new ArrayList<>(phoneBook.keySet());
                list.sort((o1, o2) -> phoneBook.get(o2).split(", ").length - phoneBook.get(o1).split(", ").length); // сортировка по убыванию количества номеров телефонов
                if (!phoneBook.isEmpty()) {
                    for (String str: list) {
                        for (String s : phoneBook.keySet()) {
                            if (str.equals(s)) System.out.println(s + ": " + phoneBook.get(s));
                        }
                    }

                } else System.out.println("Телефонная книга пуста!");
                System.out.println("\n" + menu);
                sc.next();
            } else {
                System.out.println("Вы ввели неверную цифру! Нужно от 1 до 5!");
                System.out.println("\n" + menu);
                sc.next();
            }
        }
        sc.close();

    }

    public static Map<String, String> inputData () {
        Map<String, String> pb = new HashMap<>();
        Scanner sc = new Scanner(System.in); // ввод ФИО
        System.out.println("Введите ФИО: ");
        String name = sc.nextLine();
        System.out.println("Введите телефон: ");
        String phone = sc.nextLine();
        pb.merge(name, phone, (o, n) -> o + ", " + n);
        return pb;
    }

}
