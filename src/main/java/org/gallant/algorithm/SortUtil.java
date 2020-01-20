package org.gallant.algorithm;

import com.google.common.primitives.Ints;

/**
 * @author 会灰翔的灰机
 * @date 2020/1/20
 */
public class SortUtil {

    public static int[] randomArray(int size){
        int[] array = new int[size];
        for (int i=0;i<size;i++) {
            array[i] = (int)(Math.random()*100+i);
        }
        return array;
    }

    public static void println(Object o) {
        if (o instanceof int[]) {
            System.out.println(Ints.asList((int[]) o));
        } else {
            System.out.println(o);
        }
    }

    public static void print(Object o) {
        System.out.print(o);
    }
}
