package org.gallant.algorithm;

import static org.gallant.algorithm.SortUtil.println;

/**
 * 参考：https://www.geeksforgeeks.org/heap-sort/
 * @author 会灰翔的灰机
 * @date 2020/1/21
 */
public class HeapSortV2 {

    /**
     * 初始数组被看作一个完全二叉树
     * Input data: 4, 10, 3, 5, 1
     *          4(0)
     *         /   \
     *      10(1)   3(2)
     *     /   \
     *  5(3)    1(4)
     *
     * The numbers in bracket represent the indices in the array
     * representation of data.
     *
     * Applying heapify procedure to index 1:
     *          4(0)
     *         /   \
     *     10(1)    3(2)
     *     /   \
     * 5(3)    1(4)
     *
     * Applying heapify procedure to index 0:
     *         10(0)
     *         /  \
     *      5(1)  3(2)
     *     /   \
     *  4(3)    1(4)
     * The heapify procedure calls itself recursively to build heap
     *  in top down manner.
     */
    public static void heapSort(int[] data) {
        for (int i = data.length; i > 0; i--) {
            buildMaxHeap(data, i);
            swap(data, 0, i - 1);
        }
    }
    private static void buildMaxHeap(int[] data, int size){
        for (int i = size / 2; i >= 0 ; i--) {
            int rightIndex = (i + 1) * 2;
            int leftIndex = rightIndex - 1;
            if (rightIndex < size && data[rightIndex] > data[i]) {
                swap(data, i, rightIndex);
            }
            if (leftIndex < size && data[leftIndex] > data[i]) {
                swap(data, i, leftIndex);
            }
        }
    }
    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = SortUtil.randomArray(9);
        println(array);
        heapSort(array);
        println(array);
    }

}
