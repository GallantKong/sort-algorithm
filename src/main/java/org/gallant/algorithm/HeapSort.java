package org.gallant.algorithm;

import static org.gallant.algorithm.SortUtil.println;

/**
 * 参考：https://www.geeksforgeeks.org/heap-sort/
 * @author 会灰翔的灰机
 * @date 2020/1/21
 */
public class HeapSort {

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
    public static void heapSort(int[] array) {
        int heapSize = array.length;
        // 1. 构建最大堆。最大堆的每个非叶子节点都是左右子树的最大节点。即：构建之后0索引处为整棵树的最大值
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            heapify(array, heapSize, i);
        }
        for (int i = array.length - 1; i >= 0 ; i--) {
            // 2. 将最大值交换至最后，剩余[0, n - 2]恢复非最大堆状态。n - 1索引处为当前最大堆中的最大值
            swap(array, i, 0);
            // 3. 重复步骤1，2，直至整个堆有序
            heapify(array, i, 0);
        }
    }

    public static void heapify(int[] array, int heapSize, int largestNodeIndex) {
        // 初始化根节点
        int largest = largestNodeIndex;
        // 根节点左子节点索引
        int leftChild = 2 * largestNodeIndex + 1;
        // 根节点右子节点索引
        int rightChild = 2 * largestNodeIndex + 2;
        // 如果左子树大于根节点，更新根节点索引
        if (leftChild < heapSize && array[leftChild] > array[largest]) {
            largest = leftChild;
        }
        // 如果右子树大于根节点，更新根节点索引
        if (rightChild < heapSize && array[rightChild] > array[largest]) {
            largest = rightChild;
        }
        // 根节点索引发生变更，更新根节点
        if (largest != largestNodeIndex) {
            swap(array, largest, largestNodeIndex);
            // 左子树或右子树发生变更，递归更新发生变更的子树
            heapify(array, heapSize, largest);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = SortUtil.randomArray(9);
        println(array);
        heapSort(array);
        println(array);
    }

}
