/*
    1. Организовать ввод и хранение данных пользователей. ФИО возраст и пол
    2. вывод в формате Фамилия И.О. возраст пол
    //3. добавить возможность выхода или вывода списка отсортированного по возрасту!)//
    3. *реализовать сортировку по возрасту с использованием индексов
    5. *реализовать сортировку по возрасту и полу с использованием индексов
*/


package org.example.Homework4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Homework4 {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Иван\\Desktop\\GB учеба\\7. Разработчик — Программист. Специализация\\" +
                "2. Java знакомство и как пользоваться базовым API\\Homeworks_GB\\src\\main\\java\\org\\example\\" +
                "Homework4\\userData.txt";

        // данные из файла скачиваются сразу при запуске программы, переводятся в список списков строк, который уже в дальнейшей работе и используется
        ArrayList<String> arrayList = null;  // создаем пустой список строк
        try {
            arrayList = new ArrayList<>(Files.readAllLines(Path.of(filePath)));  // заполняем данный список строками из файла
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        ArrayList<ArrayList<String>> arrArrList = new ArrayList<>();  // создаем пустой список списков строк, который будет иметь вид: [[ФИО], [возраст], [пол]]

        ArrayList<String[]> arrList = new ArrayList<>();  // создаем промежуточный пустой список массивов строк
        assert arrayList != null;
        for (String s : arrayList) {
            arrList.add(s.split(", "));   // заполняем его, будет в виде [[ФИО, возраст, пол], [ФИО, возраст, пол], [ФИО, возраст, пол]...]
        }
        for (int i = 0; i < arrList.get(0).length; i++) {  // переводим список массивов строк в список списков строк
            ArrayList<String> list = new ArrayList<>();
            for (String[] strings : arrList) {
                list.add(strings[i]);
            }
            arrArrList.add(list);
        }

        List<Integer> listOfIndexesStart = new ArrayList<>();  // создаем список индексов (от 0 до 5)
        for (int i = 0; i < arrArrList.get(0).size(); i++) {
            listOfIndexesStart.add(i);
        }

        Scanner sc = new Scanner(System.in);
        String menu = "1. Ввести данные пользователей и сохранить их.\n" +
                "2. Вывод данных пользователей.\n" +
                "3. Отсортировать и вывести список по возрасту (в порядке возрастания) с использованием индексов.\n" +
                "4. Отсортировать и вывести список по полу (сначала муж, потом жен) и возрасту (в порядке возрастания) с использованием индексов.\n" +
                "5. Выход.";
        System.out.println(menu);

        while (!sc.hasNext("5")) {
            if (sc.hasNext("1")) {
                while (!sc.hasNext("2")) {
                    inputAndSaveData(filePath);
                    System.out.println("Введите '1' для продолжения ввода данных, либо '2' для выхода");
                    sc.next();
                }
                System.out.println("\n" + menu);
                sc.next();
            } else if (sc.hasNext("2")) {
                printArray(arrArrList, listOfIndexesStart, menu);
                sc.next();
            } else if (sc.hasNext("3")) {
                List<Integer> listOfIndexesFinish = new ArrayList<>(listOfIndexesStart);
                listOfIndexesFinish.sort(Comparator.comparingInt(o -> Integer.parseInt(arrArrList.get(1).get(o))));
                printArray(arrArrList, listOfIndexesFinish, menu);
                sc.next();
            } else if (sc.hasNext("4")) {
                List<Integer> listOfIndexesFinish = sortForAgeAndSex(arrArrList);
                printArray(arrArrList, listOfIndexesFinish, menu);
                sc.next();
            } else {
                System.out.println("Вы ввели неверную цифру! Нужно от 1 до 5!");
                sc.next();
            }
        }
        sc.close();
    }

    public static void inputAndSaveData(String path) {  // метод, который осуществляет ввод данных и запись их в файл
        Scanner sc = new Scanner(System.in); // ввод ФИО
        System.out.println("Введите ФИО: ");
        String name = sc.nextLine();

        sc = new Scanner(System.in);  // ввод возраста (только натуральные числа)
        int age;
        do {
            System.out.println("Введите возраст (число больше 0): ");
            while (!sc.hasNextInt()) {
                System.out.println("Возраст должен быть числом!");
                sc.next();
            }
            age = sc.nextInt();
        } while (age <= 0);

        sc = new Scanner(System.in);  // ввод пола (строго муж или жен)
        System.out.println("Введите пол (муж или жен): ");
        while (!(sc.hasNext("муж") | sc.hasNext("жен"))) {
            System.out.println("Введите пол правильно: муж или жен!");
            sc.next();
        }
        String sex = sc.next();

        try (FileWriter fw = new FileWriter(path, true)) {  // запись введенных данных в файл построчно
            fw.write(name + ", " + age + ", " + sex + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void printArray(ArrayList<ArrayList<String>> arrayList, List<Integer> list, String str) {  // метод по выводу данных пользователей в соответствии с полученным массивом индексов
        int size = arrayList.size();
        for (int i = 0; i < arrayList.get(0).size(); i++) {
            int k = list.get(i);
            StringBuilder sb = new StringBuilder((i + 1) + ". ");
            for (int j = 0; j < size; j++) {
                sb.append(arrayList.get(j).get(k)).append(", ");
            }
            System.out.println(sb.delete(sb.length() - 2, sb.length() - 1));
        }
        System.out.println("\n" + str);
    }

    public static List<Integer> sortForAgeAndSex(ArrayList<ArrayList<String>> arrayList) { // метод, который выдает список индексов отсортированных по полу и возрасту пользователей
        List<Integer> list1 = new ArrayList<>();  // создаем два новых списка (один для индексов мужчин, другой для индексов женщин)
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < arrayList.get(2).size(); i++) {  // заполняем эти списки по половой принадлежности
            if (arrayList.get(2).get(i).equals("муж")) list1.add(i);
            else list2.add(i);
        }

        list1.sort(Comparator.comparingInt(o -> Integer.parseInt(arrayList.get(1).get(o))));  // сортируем списки по возрасту
        list2.sort(Comparator.comparingInt(o -> Integer.parseInt(arrayList.get(1).get(o))));

        list1.addAll(list2);  // добавляем отсортированный список индексов женщин в такой же список для мужчин
        return list1;
    }
}
