/*Сравнить время выполнения замены символа "а" на "А" любой строки содержащей >1000 символов средствами String и StringBuilder*/

package org.example.Homework2;

public class Task3 {
    public static void main(String[] args) {
        String str = "a".repeat(1000000);;
        StringBuilder strBuilder = new StringBuilder(str);

        long start = System.currentTimeMillis();
        str = str.replace("a", "A");
        System.out.println("String result: " + (System.currentTimeMillis() - start)); // при а=1.000.000 время=5

        start = System.currentTimeMillis();
        for (int i = 0; i < strBuilder.length(); i++) {
            strBuilder.replace(i, i + 1, "A");
        }
        System.out.println("StringBuilder result: " + (System.currentTimeMillis() - start)); // при а=1.000.000 время=9616
    }
}
