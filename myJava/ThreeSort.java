Copyright: Jack-Lee-Hiter

/**
 * 直接插入排序
 *
 * 1. 从第一个元素开始，该元素可以认为已经被排序
 * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 5. 将新元素插入到该位置后
 * 6. 重复步骤2~5
 * @param arr  待排序数组
 */
public static void insertionSort(int[] arr){
    for( int i=0; i<arr.length-1; i++ ) {
        for( int j=i+1; j>0; j-- ) {
            if( arr[j-1] <= arr[j] )
                break;
            int temp = arr[j];      //交换操作
            arr[j] = arr[j-1];
            arr[j-1] = temp;
            System_out_println("Sorting:  " + Arrays.toString(arr));
        }
    }
}

/**
 * 选择排序
 *
 * 1. 从待排序序列中，找到关键字最小的元素；
 * 2. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
 * 3. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复①、②步，直到排序结束。
 *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 * @param arr  待排序数组
 */
public static void selectionSort(int[] arr){
    for(int i = 0; i < arr.length-1; i++){
        int min = i;
        for(int j = i+1; j < arr.length; j++){    //选出之后待排序中值最小的位置
            if(arr[j] < arr[min]){
                min = j;
            }
        }
        if(min != i){
            int temp = arr[min];      //交换操作
            arr[min] = arr[i];
            arr[i] = temp;
            System_out_println("Sorting:  " + Arrays.toString(arr));
        }
    }
}

/**
 * 冒泡排序
 *
 * ①. 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * ②. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * ③. 针对所有的元素重复以上的步骤，除了最后一个。
 * ④. 持续每次对越来越少的元素重复上面的步骤①~③，直到没有任何一对数字需要比较。
 * @param arr  待排序数组
 */
public static void bubbleSort(int[] arr){
    for (int i = arr.length - 1; i > 0; i--) {      //外层循环移动游标
        for(int j = 0; j < i; j++){    //内层循环遍历游标及之后(或之前)的元素
            if(arr[j] > arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                System_out_println("Sorting: " + Arrays.toString(arr));
            }
        }
    }
}
