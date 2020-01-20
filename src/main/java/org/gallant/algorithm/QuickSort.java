package org.gallant.algorithm;

import static org.gallant.algorithm.SortUtil.println;

/**
 * @author 会灰翔的灰机
 * @date 2020/1/19
 */
public class QuickSort {

    public static void quickSort(int[] data, int start, int end) {
        int pivotIndex = partitioning(data, start, end);
        if (pivotIndex > -1) {
            quickSort(data, start, pivotIndex);
            quickSort(data, pivotIndex, end);
        }
    }

    public static int partitioning(int[] data, int start, int end) {
        int pivot = data[start];
        int i = end, j = start + 1;
        while (i >= j) {
            int right = data[i];
            int left = data[j];
            // 相等的值放在右边子序列中
            boolean rightLesserPivot = right < pivot;
            boolean leftGreaterPivot = left >= pivot;
            // 哨兵i大于基准值，继续移动
            if (!rightLesserPivot) {
                i--;
            } else {
                // 哨兵j小于基准值,并且哨兵i已经发现大于基准值的值时，移动哨兵j
                if (!leftGreaterPivot) {
                    j++;
                }
            }
            if (leftGreaterPivot && rightLesserPivot) {
                // 哨兵i与哨兵j相遇前找到了比基准值大，比基准值小的数值，交换他们的位置
                swap(data, left, j, right, i);
                i--;
                j++;
            }
            // 哨兵i与哨兵j相遇
            if (i == j) {
                boolean isLesserPivot = data[i] < pivot;
                if (isLesserPivot) {
                    swap(data, pivot, start, data[i], i);
                }
                println(data);
                return i;
            }
        }
        return -1;
    }

    public static void swap(int[] data, int leftValue, int leftIndex, int rigthValue, int rightIndex){
        data[rightIndex] = leftValue;
        data[leftIndex] = rigthValue;
    }

    public static void main(String[] args) {
        int[] array = SortUtil.randomArray(15);
        println(array);
        quickSort(array, 0, array.length - 1);
        println(array);
    }

}
