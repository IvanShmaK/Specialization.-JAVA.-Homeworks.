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

        Map<Integer, Integer> mapOfIndexes = new HashMap<>();   // создаем HashMap-коллекцию, в которой ключами будут индексы в списке возрастов, а значениями - соответствующие им возрасты
        for (int i = 0; i < arrArrList.get(1).size(); i++) {
            mapOfIndexes.put(i, Integer.parseInt(arrArrList.get(1).get(i)));
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
                int [] arrayOfIndexStart = new int[arrArrList.get(0).size()];  // создаем массив индексов (от 0 до 5)
                for (int i = 0; i < arrayOfIndexStart.length; i++) {
                    arrayOfIndexStart[i] = i;
                }
                printArray(arrArrList, arrayOfIndexStart, menu);
                sc.next();
            } else if (sc.hasNext("3")) {
                ArrayList<String> ages = (ArrayList<String>) arrArrList.get(1).clone();
                ages.sort(Comparator.naturalOrder());  //для сортировки пришлось делать клон списка с возрастами. Иначе программа отсортированный список возрастов перемешивает
                printArray(arrArrList, indexesOfSortedAges(arrArrList, ages, mapOfIndexes), menu); // в соответствии с измененным массивом индексов отсортированного списка возрастов
                sc.next();
            } else if (sc.hasNext("4")) {
                ArrayList<ArrayList<String>> sortedUsersFiles = sortForAgeAndSex(arrArrList);
                ArrayList<String> ages = (ArrayList<String>) sortedUsersFiles.get(1).clone();
                printArray(arrArrList, indexesOfSortedAges(arrArrList, ages, mapOfIndexes), menu);
                sc.next();
            } else System.out.println("Вы ввели неверную цифру! Нужно от 1 до 4!");
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

    public static void printArray (ArrayList<ArrayList<String>> arrayList, int[] array, String str) {  // метод по выводу данных пользователей в соответствии с полученным массивом индексов
        int size = arrayList.size();
        for (int i = 0; i < arrayList.get(0).size(); i++) {
            int k = array[i];
            StringBuilder sb = new StringBuilder((i + 1) + ". ");
            for (int j = 0; j < size; j++) {
                sb.append(arrayList.get(j).get(k)).append(", ");
            }
            System.out.println(sb.delete(sb.length() - 2, sb.length() - 1));
        }
        System.out.println("\n" + str);
    }

    public static ArrayList<ArrayList<String>> sortForAgeAndSex (ArrayList<ArrayList<String>> arrayList) { // метод, который осуществляет сортировку по полу и возрасту пользователей
        ArrayList<ArrayList<String>> arList1 = new ArrayList<>(); // создаем два новых пустых списка списков строк (один для мужчин, другой для женщин)
        for (int i = 0; i < arrayList.size(); i++) {
            arList1.add(new ArrayList<String>());
        }
        ArrayList<ArrayList<String>> arList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            arList2.add(new ArrayList<String>());
        }

        for (int i = 0; i < arrayList.get(2).size(); i++) {  // заполняем эти списки по половой принадлежности
            if (arrayList.get(2).get(i).equals("муж")) {
                for (int j = 0; j < arrayList.size(); j++) {
                    arList1.get(j).add(arrayList.get(j).get(i));
                }
            } else {
                for (int j = 0; j < arrayList.size(); j++) {
                    arList2.get(j).add(arrayList.get(j).get(i));
                }
            }
        }

        arList1.get(1).sort(Comparator.naturalOrder());  // сортируем каждый список по возрасту
        arList2.get(1).sort(Comparator.naturalOrder());

        for (int i = 0; i < arrayList.size(); i++) {  // добавляем к первому списку соответствующие элементы из второго
            arList1.get(i).addAll(arList2.get(i));
        }
        return arList1;
    }

    public static int[] indexesOfSortedAges (ArrayList<ArrayList<String>> arrayList, ArrayList<String> arStr, Map<Integer,Integer> map) {  // метод, который выдает массив индексов отсортированного списка
        int[] arrayOfAges = new int[arStr.size()];   // создаем массив отсортированных возрастов
        for (int i = 0; i < arrayOfAges.length; i++) {
            arrayOfAges[i] = Integer.parseInt(arStr.get(i));
        }
        int [] arrOfIndexFinish = new int[arrayOfAges.length];   // создаем массив индексов отсортированных возрастов
        for (int i = 0; i < arrayOfAges.length; i++) {
            for (int j = 0; j < arrayOfAges.length; j++) {
                if (arrayOfAges[i] == map.get(j)) {
                    arrOfIndexFinish[i] = j;
                }
            }
        }
        return arrOfIndexFinish;
    }

}
