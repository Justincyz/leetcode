/*459. Repeated Substring Pattern
Easy
链接: https://leetcode.com/problems/repeated-substring-pattern/

Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.

Example 2:
Input: "aba"
Output: False

Example 3:
Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

*/


/*解题思路
这道题目让我们找到一个字符串是否存在一个子串，整个字符串都由这个子串构成。比如说例子1里面，整个字符串都是由"ab"组成的。
因为我们要找到子字符串，所以这个子字符串的最大的长度只能是s的一半。因为超过一半的话x2之后肯定超过了原来字符串的长度。
最简单的做法就是我们固定一个windows, 然后用这个固定window大小的substring和整个字符串进行对比。首先我们要看看这个window是不是可以被整个字符串的长度整除。如果可以整除的话我们直接遍历整个字符串。为了节约时间，如果中间有一段substring没有办法对的上，那么直接返回false。如果整个字符串都可以用target 的substring来代替的话。那么就是true。
*/

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        for(int window=1; window<=s.length()/2; window++){
            if(s.length()%window != 0) continue;
            String target = s.substring(0, window);
            if(backTrack(s, window, target)){
                return true;
            }
        }
        return false;
    }
    
    public boolean backTrack(String s, int window, String target){
        int i=window;
        for(;i+window <=s.length(); i+=window){
            if(!s.substring(i, i+window).equals(target)) return false;
        }
        return i == s.length();
    }
}


/*
还有另外一种时间复杂度较低的做法。有点类似于伪KMP的做法。
我们首先需要建立一个长度为s.length()+1的数组，这个数组当中存的数字是除了我们保留的pattern substring之外的可以配对的子串长度之和。
比如说s = "abcabc",
对应的dp的数组就是[0 0 0 0 1 2 3]。
这里最后对应的3指的就是除了一开始的pattern substring "abc"后面的那个"abc"，然后长度为3。
如果s= "abcabcabc"，那么dp数组为[0 0 0 0 1 2 3 4 5 6]，我们发现最后一个数字为6，那么表示重复的字符串为“abcabc”，有6个字符。 

然后我们看，还有两个参数，i代表的是除pattern substring以外的其余子串的遍历指针。而j代表的就是pattern substring的指针了。首先两个需要错位一位, 如果两个指针指向的字符是一样的话，那么我们在同时把两个指针后移的时候，还需要将dp[i]赋值为j。意思就是在dp[i]这一位，我们起码可以匹配上前j个前缀。
第二个if就是说，如果j已经无法匹配且可以回退的时候，我们只能挪动i去寻找下一位可以匹配的字符了。
比较让人难理解的就是第三个if语句了。
实际上这个才算是这个的一个亮点。
第三个if的意思是说，如果当前i和j位置无法匹配，并且j还不在起始位置0的话，那么因为i和j之前的位置上的字符都是可以匹配上的，所以我们的pattern substring, j可以回退到前一位能够匹配上的位置。这样就给我吗节约了时间。

那么怎么通过最后一个数字来知道原字符串是否由重复的子字符串组成的呢，首先当然是最后一个数字不能为0，而且还要满足dp[n] % (n - dp[n]) == 0才行，因为n - dp[n]是一个子字符串的长度，那么重复字符串的长度和肯定是一个子字符串的整数倍

*/

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int i=1, j=0, n = s.length();
        int[] dp = new int[n+1];
        int k =0;
        while(i<n){
            k++;
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j++;
                dp[i] = j;
            }else if(j == 0) i++;
            else{
                //回退到前一位，因为前一位是可以匹配上的
                j = dp[j];
                
            } 
        }

        
        return dp[n] >0 && (dp[n] % (n-dp[n]) == 0);
    }
}