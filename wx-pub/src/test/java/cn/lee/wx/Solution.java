package cn.lee.wx;

import java.util.Scanner;


/**
 * Created by jason on 17-11-15.
 */
public class Solution {
    static String timeConversion(String s) {
        // Complete this function
        String time = s.substring(0, 8);
        String am = s.substring(8, 10);
        String hour = time.substring(0, 2);
        if (hour.equalsIgnoreCase("12")) {
            if (am.equalsIgnoreCase("am")) {
                hour = "00";
                time = hour + time.substring(2);
            }
        } else if (am.equalsIgnoreCase("pm")) {
            int _h = Integer.valueOf(hour) + 12;
            hour = String.valueOf(_h == 24 ? "00" : _h);
            time = hour + time.substring(2);
        }
        return time;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //String s = in.next();
        String s = "12:45:54PM";
        String result = timeConversion(s);
        System.out.println(result);
        solve(new int[]{73, 67, 38, 33});
        solveAppleAndOranges(7, 11, 5, 15, 3, 2, new int[]{-2, 2, 1}, new int[]{5, -6});
        solveAppleAndOranges(2, 3, 1, 5, 1, 1, new int[]{2}, new int[]{-2});
        getTotalX(new int[]{2,4},new int[]{16,32,96});
    }

    static int[] solve(int[] grades) {
        // Complete this function
        for (int i = 0; i < grades.length; i++) {
            grades[i] = round(grades[i]);
        }
        return grades;
    }

    static int round(int in) {
        if (in < 38) {
            return in;
        } else {
            int mod = 5 - in % 5;
            if (mod < 3) {
                return in + mod;
            } else {
                return in;
            }
        }
    }

    static int[] solveAppleAndOranges(int s, int t, int a, int b, int m, int n, int[] apples, int[] oranges) {
        int appleIn = 0;
        int orangeIn = 0;
        for (int p : apples) {
            int pos = a + p;
            appleIn = pos >= s && pos <= t ? appleIn + 1 : appleIn;
        }
        for (int p : oranges) {
            int pos = b + p;
            orangeIn = pos >= s && pos <= t ? orangeIn + 1 : orangeIn;
        }
        System.out.println(appleIn);
        System.out.println(orangeIn);
        return new int[]{appleIn, orangeIn};
    }

    static String kangaroo(int x1, int v1, int x2, int v2) {
        String yes = "YES";
        String no = "NO";
        if (x1 == x2) {
            return yes;
        }
        if ((x1 < x2 && v1 <= v2) || (x1 > x2 && v1 >= v2)) {
            return no;
        }
        if ((x2 - x1) % (v2 - v1) == 0) {
            return yes;
        }

        return no;
    }

    static int getTotalX(int[] a, int[] b) {
        System.out.println("=========getTotalX===============");
        // Complete this function
        int result = 0;
        int[] arr = new int[101];

        for (int t = 1; t <= 100; t++) {
            for (int i : a) {
                if (t % i == 0) {
                    arr[t]++;
                }
            }
            for (int i : b) {
                if (i % t == 0) {
                    arr[t]++;
                }
            }
        }
        int length = a.length + b.length;
        for (int t = 1; t < arr.length; t++) {
            if (arr[t] >= length){
                result++;
                System.out.println(t);
            }
        }
        System.out.println(result);
        return result;
    }
}
