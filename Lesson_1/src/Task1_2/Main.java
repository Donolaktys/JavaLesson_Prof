package Task1_2;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] intArr = {1, 4, 6, 7, 8};
        Character[] charArr = {'a', 's', 'd', 'f', 'g', 'h'};

        System.out.println(Arrays.toString(intArr));
        System.out.println(Arrays.toString(swapElement(intArr, 1, 4)));
        System.out.println(convertToArrayList(intArr));

        System.out.println(Arrays.toString(charArr));
        System.out.println(Arrays.toString(swapElement(charArr, 1, 3)));
        System.out.println(convertToArrayList(charArr));

    }

    public static <T> T[] swapElement(T[] arr, int first, int second) {
        if (first >= 0 && first < arr.length && second >= 0 && second < arr.length) {
            T change = arr[first];
            arr[first] = arr[second];
            arr[second] = change;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr;
    }

    public static <T> ArrayList<T> convertToArrayList(T[] arr) {
        ArrayList<T> newArrayList = new ArrayList<>();
        for (T t : arr) {
            newArrayList.add(t);
        }
        return newArrayList;
    }
}

/**
 *         [1,4,6,7,8]
 *         [1,8,6,7,4]
 *         [1,8,6,7,4]
 *         [a,s,d,f,g,h]
 *         [a,f,d,s,g,h]
 *         [a,f,d,s,g,h]
 *
 *         Process finished with exit code 0
 */
