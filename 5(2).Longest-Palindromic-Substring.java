/*5. Longest Palindromic Substring
Medium: 

Given a string s, find the longest palindromic substring in s. You may 
assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
*/

/*解题思路
两种办法都比较直接

*/

class Solution {
    public String longestPalindrome(String s) {
        if(s.length() < 2) return s;
 
        String res = "";
        for(int i=1; i<s.length(); i++){
            String s1 = check(i, true, s);  //check i as the center
            String s2 = check(i, false, s); //check i-1 and i as the center
            if(res.length() < s1.length()) res = s1;
            if(res.length() < s2.length()) res = s2;

        }
        return res;
    }
    
    public String check(int c, boolean flag, String s){
        char[] list = s.toCharArray();
        int l = c-1, r = 0;
        if(flag) r= c+1;      
        else r = c;
        
        while(l>=0 && r<list.length && list[l] == list[r]){
            l--;
            r++;
        }
        return (l+1) <= (r-1) ? s.substring(l+1, r) : String.valueOf(list[c]);
    }
}

/*
同样的用dp来做，速度比上面慢不少，估计因为上面的runtime是小于O(N^2)的，但是dp就完全是O(N^2)
*/

class Solution {
    public String longestPalindrome(String s) {
        if(s.length() < 2) return s;
    
        char[] list = s.toCharArray();
        int n = list.length;
        String res = String.valueOf(list[0]);
        boolean[][] dp = new boolean[n][n];
        
        for(int i=0; i<n; i++){
            dp[i][i] = true;
            for(int j=0; j<i; j++){
                if(list[i] == list[j] && ((i-j) <=1 || dp[j+1][i-1] == true)){
                    dp[j][i] = true;
                    if(i-j+1 > res.length()) res = s.substring(j, i+1);
                }
            }
        }
        return res;
    }
}