/*Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки.
Если значение null, то параметр не должен попадать в запрос.
Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
В итоге должно получится select * from students where name=Ivanov, country=Russia, city=Moscow*/



package org.example.Homework2;

public class Task1 {
    public static void main(String[] args) {
        String textJson = "{\"name\":\"Ivanov\", \"surname\":\"null\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\", \"work\":\"GB\"}";
        String sqlQuery = "select * from students where ";

        textJson = textJson.replace("{", "") //парсим json-строку, удаляя ненужные знаки, и заменяя двоеточие на равно
                .replace("}", "")
                .replace("\"", "")
                .replace(":", "=");

        if (textJson.contains("null")) sqlQuery += deleteNullParameter(textJson); // если получившаяся строка содержит значение null, удаляем содержащие его параметры методом deleteNullParameter и и формируем строку для вывода
        else sqlQuery += textJson;  // иначе оставляем получившуюся строку как есть и формируем строку для вывода

        System.out.println(sqlQuery);
    }

    public static String deleteNullParameter (String str) { // метод по удалению параметров со значением null
        String [] tmp = str.replace(",", "").split(" ");  // создаем массив строк, предварительно убрав запятые
        StringBuilder tmpStringBuilder = new StringBuilder();
        for (String s : tmp) {  // если элемент массива содержит null, его пропускаем, инача добавляем в созданный tmpStringBuilder через запятую и пробел
            if (s.contains("null")) continue;
            else tmpStringBuilder.append(s).append(", ");
        }
        return tmpStringBuilder.delete(tmpStringBuilder.length() - 2, tmpStringBuilder.length() - 1).toString(); // полученный tmpStringBuilder переводим в строку, предварительно убрав последние запятую и пробел
    }
}
