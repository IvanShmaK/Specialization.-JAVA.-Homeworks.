/*Сравнить время выполнения замены символа "а" на "А" любой строки содержащей >1000 символов средствами String и StringBuilder*/

package org.example.Homework2;

public class Task3 {
    public static void main(String[] args) {
        String str = "a";
        str = str.repeat(100000);

        StringBuilder strBuilder = new StringBuilder(str);

        long start = System.currentTimeMillis();
        str = str.replace("a", "A");
        System.out.println("String result: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < strBuilder.length(); i += 2) {
            strBuilder.replace(i, i + 1, "AA");
        }
        System.out.println("StringBuilder result: " + (System.currentTimeMillis() - start) * 2);
    }
}
