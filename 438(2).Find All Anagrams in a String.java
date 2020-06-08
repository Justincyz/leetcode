/*438. Find All Anagrams in a String
Medium
链接: https://leetcode.com/problems/find-all-anagrams-in-a-string/

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".


Example 2:
Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/



/*解题思路
这道题目非常的经典，给我们两个字符串，s和p。让我们找到p在s中的所有anagram的起始位置。anagram的意思是同一字符串的不同排列。

这道题最简单的和经典的做法就是 sliding window的做法。因为题目限定p和s都是小写的字母。所以我们可以直接用一个长度为26的数组来计算p中所有字母出现的频率。我们首先把所有p当中的字母放入到array当中。然后就可以用sliding window的做法了。我们用right指针当做我们window的右边，left是window的左边。如果我们在array当中减去了当前的字符之后这个字符的位置吧变成了负数，那么说明要不当前字符不在我们的p当中，或者p当中这个字符串不够用了。此时我们就要挪动left指针直到补上这个被剪掉的字符才行。最后我们需要再判断一下，当前这个window的size是不是和p的长度是一样的，如果是的话，那么我们就把left坐标加入到最终结果当中。
我们每一次都要挪动右边的指针。

TIME: O(N)
SPACE: O(p的长度)
*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] array = new int[26];
        for(char c: p.toCharArray()){
            array[c-'a']++;
        }
        
        int left = 0, right = 0, pLen = p.length();
        
        while(right < s.length()){
            char c = s.charAt(right);
            array[c-'a']--;
            
            if(array[c-'a'] < 0){
                while(left <= right && array[c-'a'] < 0){
                    array[s.charAt(left)-'a']++;
                    left++;
                }
            }
            
            if(right-left+1 == pLen){
                res.add(left);
            }
            right++;
        }
        
        return res; 
    }
}