package org.example.Homework6;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class seminar6 {
    public static void main(String[] args) {
        SetImitation setImitation = new SetImitation();
        System.out.println(setImitation.add(1));
        System.out.println(setImitation.add(2));
        System.out.println(setImitation.add(1));

        System.out.println(setImitation.delete(1));
        System.out.println(setImitation.delete(1));

        System.out.println(setImitation.size());
        System.out.println(setImitation.isEmpty());
        System.out.println(setImitation.contains(1));
        System.out.println(setImitation.contains(2));

        for (int i = 0; i < 10; i++) {
            setImitation.add(new Random().nextInt(1000));
        }

        Iterator<Integer> iterator = setImitation.iterator();
        while (iterator.hasNext()){
            int i = iterator.next();
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println(setImitation.getIndexOf(2));

        System.out.println(setImitation);
    }
}

class SetImitation<E> {
    private HashMap<E, Object> map = new HashMap<>();
    private static final Object OBJECT = new Object();
    public boolean add (E num) {
        return map.put(num, OBJECT) == null;
    }
    public boolean delete (E num) {
        return map.remove(num, OBJECT);
    }
    public int size () {
        return map.size();
    }
    public boolean isEmpty () {
        return map.isEmpty();
    }
    public boolean contains (E num) {
        return map.containsKey(num);
    }
    public Iterator<E> iterator () {
        return map.keySet().iterator();
    }
    public E getIndexOf (int index) {
        return (E) map.keySet().toArray()[index];
    }
    public String toString () {
        return map.keySet().toString();
    }

}
