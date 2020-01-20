package org.gallant.algorithm;

import static org.gallant.algorithm.SortUtil.print;
import static org.gallant.algorithm.SortUtil.println;

/**
 * @author 会灰翔的灰机
 * @date 2020/1/20
 */
public class CountingSort {

    public static void countingSort(int[] data) {
        int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
        // 1. 查找最大值、最小值
        for (int i = 0; i < data.length; i++) {
            int currentValue = data[i];
            if (currentValue > max) {
                max = currentValue;
            }
            if (currentValue < min) {
                min = currentValue;
            }
        }
        // 2. 将数据根据最小值映射放入对应的桶内（index下标处）
        println("max:"+max+",min:"+min);
        int[] count = new int[max - min + 1];
        for (int i = 0; i < data.length; i++) {
            int currentValue = data[i];
            print(currentValue+",");
            count[currentValue - min] += 1;
        }
        println("");
        println(count);
        // 3. 根据计数桶还原数据到原数据中。计数桶中的数据由于映射的算法可知是有序的
        int currentDataIndex = 0;
        for (int i = 0; i < count.length; i++) {
            int cnt = count[i];
            if (cnt > 0) {
                for (int j = 0; j < cnt; j++) {
                    data[currentDataIndex] = min + i;
                    currentDataIndex++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = SortUtil.randomArray(11);
        println(array);
        countingSort(array);
        println(array);
    }

}
