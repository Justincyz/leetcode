/*10. Regular Expression Matching
Hard: 
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:
s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.

Example 1:
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

Example 5:
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
*/

/*解题思路 相似题目: 44: Wildcard Matching
详细讲解可以去九章算法视频第六课看，在一小时左右。
这道题目还是典型的dp做法，时间复杂度是O(MN)。我们可以通过分析s和p的最后两个
字符可能出现的情况来推倒出来一般的情况。同时要特别注意边界条件。
这道题目有个重点就是，在分析星号的时候，一定要带着前一位一起分析。因为星号离开
了前一位的话没有任何意义。
*/

class Solution {
    public boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] pat = p.toCharArray();
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        
        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(i==0 && j==0){//空串对空串是true
                    dp[i][j] = true;
                    continue;
                }
                
                if(j==0){ //空的pattern不能匹配长度大于0的s
                    continue;
                }
                
                // j>0
                //如果pattern的最后一位不是*，所以最后一位是普通字符或者点
                if(pat[j-1] != '*'){
                	//千万别忘记要判断 i > 0
                    if(i>0 && (pat[j-1] == str[i-1] || pat[j-1] =='.')){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else{ //当前遇到的是星号的话
                    if(j>1){//默认可以不要第j-1个字符，那就是取dp[i][j-2]的值就可以了
                        dp[i][j] |= dp[i][j-2];
                    } 
                    /*如果pattern的前一位j-2可以和str的当前位i-1匹配的话。前一位就可以重复使用。
                    我们取的就是 dp[i-1][j]的值，意思是是否成功匹配到当前位取决于str的i-1位是否可以匹配。
                    重点注意i和j的取值范围。
                    */
                    if(i>0 && j>1 && (pat[j-2] == str[i-1] || pat[j-2] =='.')){
                        dp[i][j] |= dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}


/*
这是用递归来做的，还是要注意 (1)边界问题 (2)遇到星号要带着星号跑
这个做法比较慢
*/

class Solution {
    public boolean isMatch(String s, String p) {
        if(p.length() == 0 ) return s.equals(p);        
         
        if(p.length() > 1 && p.charAt(1) == '*'){//注意这里判断的是P的第一位是否是*
            if(isMatch(s, p.substring(2))) 
                return true;
            else if(s.length() > 0 && (p.charAt(0) =='.' || s.charAt(0) == p.charAt(0)))
                return isMatch(s.substring(1),p);
        }else{
            if(s.length() >0 && (p.charAt(0) =='.' || s.charAt(0) == p.charAt(0)))
                return isMatch(s.substring(1),p.substring(1));    
        }
        
        return false;
    }
}



