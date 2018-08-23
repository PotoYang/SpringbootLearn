package sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/30 10:57
 * Modified By:
 * Description:
 */
public class AllSortMethods {
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int k = i;
            for (int j = k + 1; j < arr.length; j++) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }

            if (i != k) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
    }

    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static int kmpSearch(String source, String target) {
        int count = 0;
        // 转为字符型数组
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
        // 获取next数组
        int[] next = next(target);
        // 主串下标
        int i = 0;
        // 模式串下标
        int j = 0;
        while (i < s.length && j < t.length) {
            //若j!=-1,则必然会发生字符比较
            if (j != -1) {
                count++;
            }
            //=========
            if (j == -1 || s[i] == t[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }

        }
        System.out.println("KMP匹配法比较次数为：" + count);
        if (j == t.length) {
            return i - t.length;
        }
        return -1;
    }

    private static int[] next(String target) {
        char[] t = target.toCharArray();
        int[] next = new int[t.length];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < next.length - 1) {
            if (k == -1 || t[j] == t[k]) {
                k++;
                j++;
                // ===============
                // 较优化前的next数组求法，改变在以下四行代码。
                if (t[j] != t[k]) {
                    // 优化前只有这一行。
                    next[j] = k;
                } else {
                    // 优化后因为不能出现p[j] = p[ next[j ]]，所以当出现时需要继续递归。
                    next[j] = next[k];
                }
                // ===============
            } else {
                k = next[k];
            }
        }
        return next;
    }

    @Test
    public void testBubble() {
        int[] arr = {1, 5, 3, 10, 6, 9, 7};
//        selectionSort(arr);
        Arrays.sort(arr);
        String str = "asdasdasf";
        str.contains("asd");
        for (int anArr : arr) {
            System.out.print(anArr + "  ");
        }

        String source = "abchhabchabchabchcaaaabceabddh";
        String target = "abceab";
        System.out.println("匹配成功，下标为：" + kmpSearch(source, target));
    }
}
