package org.gallant.algorithm;

import static org.gallant.algorithm.SortUtil.println;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author 会灰翔的灰机
 * @date 2020/1/20
 */
public class BucketSort {

    public static void bucketSort(int[] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        // 1. 查找 数字长度 最大值、最小值
        for (int currentValue : array) {
            int len = getIntegerLength(currentValue);
            if (len > max) {
                max = len;
            }
            if (len < min) {
                min = len;
            }
        }
        // 2. 将数据根据数字长度以及最小长度，放入对应的桶中
        println("max:"+max+",min:"+min);
        Integer[][] count = new Integer[max - min + 1][array.length];
        for (int i = 0; i < array.length; i++) {
            int currentValue = array[i];
            Integer[] bucket = count[getIntegerLength(currentValue) - min];
            for (int j = 0; j < bucket.length; j++) {
                if (bucket[j] == null) {
                    bucket[j] = currentValue;
                    break;
                }
            }
        }
        println(count);
        // 3. 根据映射函数可知，桶的下标大的桶中的所有数据大于下标小的桶中的数据
        // 所以将每个桶排序后直接拼接结果便可以得到全局有序的集合
        int currentDataIndex = 0;
        for (int i = 0; i < count.length; i++) {
            Integer[] bucketObj = count[i];
            if (bucketObj.length > 0) {
                int[] bucket = Arrays.stream(bucketObj).filter(Objects::nonNull).mapToInt(Integer::intValue).toArray();
                Sorts.insertSort(bucket);
                for (int j = 0; j < bucket.length; j++) {
                    array[currentDataIndex] = bucket[j];
                    currentDataIndex++;
                }
            }
        }
    }

    public static int getIntegerLength(int value) {
        return Integer.toString(value).length();
    }

    public static void main(String[] args) {
        int[] array = SortUtil.randomArray2(10);
        println(array);
        bucketSort(array);
        println(array);
    }

}
