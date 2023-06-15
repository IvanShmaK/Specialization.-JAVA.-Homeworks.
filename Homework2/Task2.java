/* Дополнительные задания
Дана json-строка (можно сохранить в файл и читать из файла)
[{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
Пример вывода:
Студент Иванов получил 5 по предмету Математика.
Студент Петрова получил 4 по предмету Информатика.
Студент Краснов получил 5 по предмету Физика.*/

package org.example.Homework2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Иван\\Desktop\\GB учеба\\7. Разработчик — Программист. Специализация\\2. " +
                "Java знакомство и как пользоваться базовым API\\Homeworks_GB\\src\\main\\java\\org\\example\\" +
                "Homework2\\sample1.json";

        String fileContent = null;
        try {
            fileContent = readFile(filePath, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(fileContent);

        if (fileContent != null) { //если файл не пустой, убираем лишние знаки и переводим его в массив строк
            fileContent = fileContent.replace("[", "").
                    replace("]", "").
                    replace("\"", "").
                    replace("{", "").
                    replace("}", "").
                    replace(":", " ").
                    replace(",", " ");

            String[] content = fileContent.split(" ");

            int size = content.length / 6;
            StringBuilder [] content1 = new StringBuilder[size]; // создаем пустой массив, в который запишем строки нужного нам вида

            for (int i = 0, j = 0; i < content.length; i += 6, j++) {
                content1[j] = new StringBuilder(j);
                content1[j].append("Студент ").append(content[i + 1]).append(" получил ").append(content[i + 3]).append(" по предмету ").append(content[i + 5]);
            }

            for (StringBuilder stringBuilder : content1) {
                System.out.println(stringBuilder);
            }

        } else System.out.println("В этом файле нет данных!");
    }

    public static String readFile(String path, Charset encoding) throws IOException { // метод, который возвращает строку с записанными в ней данными из указанного файла
        return FileUtils.readFileToString(new File(path));
    }
}
