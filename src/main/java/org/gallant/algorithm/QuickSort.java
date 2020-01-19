package org.gallant.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author kongyong
 * @date 2020/1/19
 */
public class QuickSort {

    public static void bubbleSort(List<Integer> data) {
        int size = data.size();
        while (size > 1) {
            for (int i = 1; i < size; i++) {
                int left = data.get(i - 1);
                int right = data.get(i);
                if (left > right) {
                    data.set(i - 1, right);
                    data.set(i, left);
                }
            }
            size--;
        }
    }

    public static void quickSort(List<Integer> data) {

    }

    public static void main(String[] args) {
        List<Integer> data = randomData(10);
        System.out.println(data);
        bubbleSort(data);
        System.out.println(data);
    }

    public static List<Integer> randomData(int size){
        List<Integer> data = new ArrayList<>(10);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data.add(random.nextInt(100));
        }
        return data;
    }

}
