/*91. Decode Ways
A message containing letters from A-Z is being encoded to numbers
 using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the 
total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

/*解法
这道题的思路就是dp，需要O(n) 的时间。第一种解法需要O(n)的空间，
第二种解法可以缩短到O(1）的空间。其实核心思想就是，在第 i 步的最大值是 
1）可以选择前 i-1 位的解法加1种解法 
(2)可以选择前 i-2位加上第 i-1和i位作为组合的解法。两种解法的和就是当前
位的合。注意因为中间有可能出现0，所以一定要避免这种情况也被加进去，因为0
不代表任何字母
这道题要特别注意关于这个10这种组合。如果当前位是0的话，前面必须有一个1或者2
才行。否则就为0.
*/

class Solution {
    public int numDecodings(String s) {
        if(s.length() ==0  ) return 0;
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] =1;
        dp[1] = s.charAt(0) != '0'? 1 : 0;
        
        for(int i=2; i<=len; i++){
            //前一位和前两位
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if(first != 0){
                dp[i] += dp[i-1];
            }
            if(second >=10 && second <=26){
                dp[i]+=dp[i-2];
            }
        }
        
        return dp[len];
    }
}


/* 
降维度
s = "104218"
c1: (0, 1), 1, 1, 2, 3
c2: 0,      1, 1, 1, 2
*/

class Solution {
    public int numDecodings(String s) {
        if(s.length() ==0 || s.charAt(0)=='0' ) return 0;
        int len = s.length();
 
        int c1 = 1; //c1代表了截止上一位为止可以产生的组合数量
        int c2 = 1; //c2代表了截止到上两位为止可以产生的组合数量

        for(int i=1; i<len; i++){
            if(s.charAt(i)=='0') c1 = 0; 
            if(s.charAt(i-1) == '1' || s.charAt(i-1) == '2' && s.charAt(i) <='6'){     
                int temp = c1;
                c1= c1+c2; //如果是类似于 1101这样的数字,当i = 2时，截止到当前第i位为止的值只能和在i-2位时一样。因为中间只能组合为10 
                c2= temp;  
            }
            else{
                c2=c1; //如果当前位只能代表一个数字，那么上一位 = 上上一位
            }
        }
        
        return c1;
    }
}
