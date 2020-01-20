package org.gallant.algorithm;

import static org.gallant.algorithm.SortUtil.println;

/**
 * @author 会灰翔的灰机
 * @date 2020/1/19
 */
public class BubbleSort {

    public static void bubbleSort(int[] data) {
        int size = data.length;
        while (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                int left = data[i];
                int right = data[i + 1];
                if (left > right) {
                    data[i] = right;
                    data[i + 1] = left;
                }
            }
            size--;
        }
    }

    public static void main(String[] args) {
        int[] array = SortUtil.randomArray(9);
        println(array);
        bubbleSort(array);
        println(array);
    }

}
