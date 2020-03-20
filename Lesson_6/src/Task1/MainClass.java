package Task1;

import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        System.out.println(Arrays.toString(newArray(array)));
    }

    public static int[] newArray(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 4) {
                int[] newArr = new int[arr.length - i - 1];
                for (int j = i + 1, newI = 0; j < arr.length; j++, newI++) {
                    newArr[newI] = arr[j];
                }
                return newArr;
            }
        }
        throw new RuntimeException("Нет 4-ки");
    }
}
