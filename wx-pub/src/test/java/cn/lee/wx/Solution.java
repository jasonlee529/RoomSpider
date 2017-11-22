package cn.lee.wx;

/**
 * Created by jason on 17-11-15.
 */
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] arrs=new int[n][n];
        for(int ar_i = 0; ar_i < n; ar_i++){
            String str = in.next();
            String[]temp =  str.split(" ");
            for(int i=0;i<n;i++){
                arrs[ar_i][i] = Integer.valueOf(temp[i]);
            }
        }
        int result = sum(n, arrs);
        System.out.println(result);
    }
    static int sum(int n,int [][] arrs){
        int result1 = 0;
        int result2 = 0;
        for(int i=0;i<n;i++){
            result1 += arrs[i][n-i-1];
        }
        for(int i=n-1;i>=0;i--){
            result2 += arrs[i][n-i-1];
        }
        return Math.abs(result1 - result2);
    }
}