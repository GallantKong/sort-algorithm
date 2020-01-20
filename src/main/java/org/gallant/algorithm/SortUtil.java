package org.gallant.algorithm;

import com.google.common.primitives.Ints;
import java.util.Random;

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

    public static int[] randomArray2(int size){
        int[] array = new int[size];
        Random random = new Random();
        for (int i=0;i<size;i++) {
            int randomInt = -1;
            if (i % 2 == 0) {
                randomInt = random.nextInt(10);
            } else {
                randomInt = random.nextInt(100);
            }
            array[i] = (int)(randomInt + i);
        }
        return array;
    }

    public static void println(Object o) {
        if (o instanceof int[]) {
            System.out.println(Ints.asList((int[]) o));
        } else if (o instanceof Integer[][]) {
            Integer[][] buckets = (Integer[][]) o;
            for (int i = 0; i < buckets.length; i++) {
                Integer[] bucket = buckets[i];
                print(i+":");
                for (int j = 0; j < bucket.length; j++) {
                    print(bucket[j] + ",");
                }
                println("");
            }
        } else {
            System.out.println(o);
        }
    }

    public static void print(Object o) {
        System.out.print(o);
    }
}
