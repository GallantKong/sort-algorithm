package org.gallant.algorithm;

import static org.gallant.algorithm.SortUtil.println;

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
            // 2. 分桶
            for (int j = 0; j < array.length; j++) {
                int currentValue = array[j];
                String currentValueStr = String.valueOf(currentValue);
                // 自右向左的游标
                int cursorIndex = (currentValueStr.length() - 1) - i;
                int index = cursorIndex >= 0 ? Character.getNumericValue(currentValueStr.charAt(cursorIndex)) : 0;
                Integer[] bucket = buckets[index];
                for (int k = 0; k < bucket.length; k++) {
                    if (bucket[k] == null) {
                        bucket[k] = currentValue;
                        break;
                    }
                }
            }
            int j = 0;
            // 2. 分桶后，按照桶顺序重组数组
            for (int i1 = 0; i1 < buckets.length; i1++) {
                Integer[] bucket = buckets[i1];
                System.out.print("当前桶 "+ i1 + " : ");
                for (Integer integer : bucket) {
                    if (integer != null) {
                        System.out.print(integer + ",");
                        array[j++] = integer;
                    }
                }
                System.out.println();
            }
            System.out.println("由低位至高位遍历分桶排序，当前位数：" + (i + 1) + "，重组后数组顺序");
            println(array);
        }
    }

    public static void main(String[] args) {
        int[] array = SortUtil.randomArray2(9);
        println(array);
        radixSort(array);
        println(array);
    }

}
