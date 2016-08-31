package com.example.lawson.androidsummery.sort;

import java.util.Arrays;

/***
 * Created by Lawson on 2016/8/30.
 */
public class BubbleSort {

    //冒泡排序
    public static void main(String[] args) {

        int[] arr = new int[]{3, 6, 14, 21, 63, 8, 96, 34, 2};

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.print(Arrays.toString(arr));
    }

}
