/*1163. Last Substring in Lexicographical Order
链接：https://leetcode.com/problems/last-substring-in-lexicographical-order/
Hard: 
Given a string s, return the last substring of s in lexicographical order.

 

Example 1:

Input: "abab"
Output: "bab"
Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
Example 2:

Input: "leetcode"
Output: "tcode"
*/

/*解题思路
题目问我们，找到给定string的substring,并且输出字典顺序最大的那一个substring.
首先明确一下字典顺序，
如果是"aab"和"aaba"的话，因为前半部分完全一样。那么哪一个长哪个字典顺序就大。
如果是"abb"和"aab"的话，因为第位的b字典顺序大于a，所以第一个字符串字典顺序大。
如果是"z"和"yzzz"的话，z会大于yzzz，因为从第一个开始匹配的话 z>y，所以不管
y之后挂了多少其他的东西，也是z大.

根据这样的规则我们知道，所有的substring都是要取到最后一位。否则即使都从i位开始，
但是因为包含最后一位的string长度肯定比没有包含的长。
我们首先看第一个写法，很简单，但是TLE了。就是从头到尾遍历一次string, 然后
用目前字典顺序最大的string和 s.substring(i, n)对比。如果新的substring更大，则更新。

第二种写法就beats 95%。
具体做法这样。我们用c保持一个目前为止遇到最大字典顺序的character。 如果有新的
char大于当前最大的，则更新我们维护的最大substring的起始位置。
假设当前遇到的char和我们已经遍历并且保存最大的char一样大(第三个if)，说明产生了一个竞争者。
此时我们设置len = 1, cand = i。 len =1的意思是当我们需要比较下一位的时候，
那么和cand下一位char相比较的应该是maxL+len这一个char。
当我们遍历到下一位时，如果s.charAt(maxL+len)和当前char一样大，说明两个
substring的前缀到目前为止还是一样的。
如果原来maxL+len这一位的char比当前位小的话，说明cand开始的substring一定
会大于原来的substring。那么我们就更新所有的值。
如果maxL+len这一位的char比当前位大的话，说明这个cand就不用管它了因为肯定比
maxL小。那我们就重置cand = -1;
*/
//过了95%的test case, 有两个特别长的超时了
class Solution {
    public String lastSubstring(String s) {
        String res = "";
        int n = s.length();
        for(int i=n-1; i>=0;i--){
            if(res.compareTo(s.substring(i,n)) <0){
                res = s.substring(i,n);
            }
        }
        return res;
    }
}


/*
注意第二个和第三个 if statement不能交换
*/

class Solution {
    public String lastSubstring(String s) {
        
        int n = s.length(), maxL=0, cand =0, len=0;
        char c = 'a';
        for(int i=0; i<n;i++){
            char cur = s.charAt(i);
            if(cur > c){
                maxL = i;
                c = cur;
                len =0;
                cand = 0; //just updated the maximum length, so there is no candidate to compete with it
            }else if(cand != -1){
                if(s.charAt(maxL+len) == cur){
                    len++;
                }else if(s.charAt(maxL+len) < cur){
                    maxL = cand;
                    c = s.charAt(cand);
                    cand = -1;
                }else{
                    cand = -1;
                }    
                
            }else if(cur == c){
                len = 1;
                cand = i;
            }
        }
        
        return s.substring(maxL);
    }
}
