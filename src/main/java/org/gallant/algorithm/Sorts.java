package org.gallant.algorithm;

import static org.gallant.algorithm.SortUtil.print;
import static org.gallant.algorithm.SortUtil.println;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import java.util.Arrays;
import java.util.List;

/**
 * 插入排序、希尔排序（基于选择排序、插入排序两个版本，以及错误的实现案例）、归并排序（循环版本、递归版本）
 * @author 会灰翔的灰机
 * @date 2019/1/15
 */
public class Sorts {

    /**
     * 插入排序
     * @param array :
     */
    public static void insertSort(int[] array) {
        println(Ints.asList(array));
        for (int i=1;i<array.length;i++) {
            int valueI = array[i];
            for (int j=0;j<i;j++) {
                int valueJ = array[j];
                if (valueJ > valueI) {
                    array[i] = valueJ;
                    array[j] = valueI;
                    valueI = valueJ;
                }
            }
        }
        println(Ints.asList(array));
    }

    /**
     * 希尔排序 基于选择排序（可以进一步优化选择排序，可以使用一个临时变量，最终选择最小值时再发生一次交换数据即可）
     * 步进按照数组长度每次/2
     * @param array :
     */
    private static void shellSort0(int[] array) {
        println(Ints.asList(array));
        int stepSize = array.length >>> 2;
        while (stepSize > 0) {
            for (int i = 0; i + stepSize < array.length; i++) {
                int left = array[i];
                int j = i + stepSize;
                while (j < array.length) {
                    int right = array[j];
                    if (left > right) {
                        array[i] = right;
                        array[j] = left;
                        left = right;
                    }
                    // 数组长度为5，步进为2时，最后一个元素不能忽略掉
                    if (j + stepSize <= array.length) {
                        j+=stepSize;
                    } else {
                        j++;
                    }
                }
            }
            stepSize >>>= 1;
        }
        println(Ints.asList(array));
    }

    /**
     * 希尔排序 基于插入排序，步进按照数组长度每次/2
     * @param array :
     */
    private static void shellSortByInsert(int[] array) {
        println(Ints.asList(array));
        int stepSize = array.length >>> 2;
        while (stepSize > 0) {
            for (int i = stepSize; i < array.length;) {
                int right = array[i];
                for (int j = 0; j < i; j+=stepSize) {
                    int left = array[j];
                    if (left > right) {
                        array[i] = left;
                        array[j] = right;
                        right = left;
                    }
                }
                // 数组长度为5，步进为2时，最后一个元素不能忽略掉
                if (i + stepSize <= array.length) {
                    i+=stepSize;
                } else {
                    i++;
                }
            }
            stepSize >>>= 1;
        }
        println(Ints.asList(array));
    }

    /**
     * 希尔排序：错误实现
     * @param array :
     */
    private static void shellSort(int[] array) {
        println(Ints.asList(array));
        int step = 4;
        while(step>0) {
            for (int i = 0; i + step < array.length; i++) {
                if (array[i] > array[i + step]) {
                    int valueI = array[i];
                    array[i] = array[i + step];
                    array[i + step] = valueI;
                }
            }
            step--;
        }
        println(Ints.asList(array));
    }

    /**
     * 希尔排序错误的实现
     * @param array : 
     */
    private static void shellSort2(int[] array) {
        println(Ints.asList(array));
        int[] steps = new int[]{5,2,1};
        for(int step:steps) {
            System.out.print(step+",");
            for (int i = 0; i + step < array.length; i++) {
                if (array[i] > array[i + step]) {
                    int valueI = array[i];
                    array[i] = array[i + step];
                    array[i + step] = valueI;
                }
            }
        }
        println(Ints.asList(array));
    }

    /**
     * 归并排序：循环实现
     * @param array :
     */
    private static void mergeSort(int[] array){
        println(Ints.asList(array));
//        分区切分
        List<int[]> list = split(array);
        print("split:");
        list.forEach(ints -> print(Ints.asList(ints)+","));
        println("");
//        归并排序
        while(list.size()>1) {
            list = mergeSort(list);
            list.forEach(ints -> println(Ints.asList(ints)));
        }
    }

    private static List<int[]> split(int[] array){
        List<int[]> list = Lists.newArrayList();
        for (int i = 0;i<array.length;i++){
            int[] tmp = new int[]{array[i]};
            list.add(tmp);
        }
        return list;
    }
    private static List<int[]> mergeSort(List<int[]> list){
        List<int[]> data = Lists.newArrayList();
        for (int i = 0;i<list.size();i+=2){
            int[] left = list.get(i);
            int[] right = i+1<list.size()?list.get(i+1):new int[0];
            int[] merge = merge(left,right);
            data.add(merge);
        }
        return data;
    }

    /**
     * 归并排序：递归实现
     * @param array :
     */
    private static void mergeSort2(int[] array) {
        println(Ints.asList(array));
        int[] newArray = mergeSortRecursive(array);
        println(Ints.asList(newArray));
    }

    private static int[] mergeSortRecursive(int[] array){
        if (array.length<2) {
            return array;
        }
        int middle = array.length/2;
        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);
        return merge(mergeSortRecursive(left), mergeSortRecursive(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = 0;
        int[] merged = new int[left.length + right.length];
        while(leftIndex < left.length && rightIndex < right.length) {
            int leftValue = left[leftIndex];
            int rightValue = right[rightIndex];
            if (leftValue >= rightValue) {
                merged[mergedIndex] = rightValue;
                rightIndex++;
                mergedIndex++;
            }
            if (leftValue < rightValue) {
                merged[mergedIndex] = leftValue;
                leftIndex++;
                mergedIndex++;
            }
        }
        while ( leftIndex < left.length) {
            int leftValue = left[leftIndex];
            merged[mergedIndex] = leftValue;
            mergedIndex++;
            leftIndex++;
        }
        while ( rightIndex < right.length) {
            int rightValue = right[rightIndex];
            merged[mergedIndex] = rightValue;
            rightIndex++;
            mergedIndex++;
        }
        return merged;
    }

    public static void main(String[] args) {
        int[] array = SortUtil.randomArray(15);
        println("---排序开始---");
        insertSort(array);
        println("---插入排序结束---");
        shellSort0(array);
        println("---正确的希尔排序基于选择排序结束---");
        shellSortByInsert(array);
        println("---正确的希尔排序基于插入排序结束---");
        shellSort(array);
        println("---错误的希尔排序1结束---");
        shellSort2(array);
        println("---错误的希尔2排序结束---");
        mergeSort(array);
        println("---归并循环排序结束---");
        mergeSort2(array);
        println("---归并递归排序结束---");
        println("---排序结束---");
    }

}
