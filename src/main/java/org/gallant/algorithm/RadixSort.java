package org.gallant.algorithm;

import static org.gallant.algorithm.SortUtil.println;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author 会灰翔的灰机
 * @date 2020/1/20
 */
public class RadixSort {

    public static void radixSort(int[] array) {
        int maxLen = 0;
        for (int i = 0; i < array.length; i++) {
            int currentValue = array[i];
            int len = Integer.toString(currentValue).length();
            if (len > maxLen) {
                maxLen = len;
            }
        }
        // 1. 从个位数至最高位，按每个位的数字进行分桶，逐个执行桶排序
        for (int i = 0; i < maxLen; i++) {
            Integer[][] buckets = new Integer[10][array.length];
            double divisor = Math.pow(10, i);
            for (int j = 0; j < array.length; j++) {
                int currentValue = array[j];
                int index = (int) ((currentValue / divisor) % 10);
                Integer[] bucket = buckets[index];
                for (int k = 0; k < bucket.length; k++) {
                    if (bucket[k] == null) {
                        bucket[k] = currentValue;
                        break;
                    }
                }
            }
            // 2. 根据映射函数可知，桶的下标大的桶中的所有数据大于下标小的桶中的数据
            // 所以将每个桶排序后直接拼接结果便可以得到全局有序的集合
            int currentDataIndex = 0;
            for (int l = 0; l < buckets.length; l++) {
                Integer[] bucketObj = buckets[l];
                if (bucketObj.length > 0) {
                    int[] bucket = Arrays.stream(bucketObj).filter(Objects::nonNull).mapToInt(Integer::intValue).toArray();
                    Sorts.insertSort(bucket);
                    for (int m = 0; m < bucket.length; m++) {
                        array[currentDataIndex] = bucket[m];
                        currentDataIndex++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = SortUtil.randomArray2(9);
        println(array);
        radixSort(array);
        println(array);
    }

}
