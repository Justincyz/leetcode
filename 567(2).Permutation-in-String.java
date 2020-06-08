/*567. Permutation in String
Medium
链接: https://leetcode.com/problems/permutation-in-string/

Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

 

Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False

*/

/*解题思路
这道题给了两个字符串s1和s2，问我们s1的全排列的字符串任意一个是否为s2的字串。虽然题目中有全排列的
关键字，但是跟之前的全排列的题目的解法并不一样，如果受思维定势影响比较深的话，很容易遍历s1所有全
排列的情况，然后检测其是否为s2的子串，这种解法是非常不高效的，估计OJ不会答应。 这道题的正确做法应该是使用滑动窗口Sliding Window 的思想来做，可以使用两个哈希表来做，或者是使用一个哈希表配上双指针来做。
下面这种解法是利用一个哈希表加上双指针，我们还是先统计s1中字符的出现次数，然后遍历s2中的字符，对于每个遍历到的字符，我们在哈希表中对应的字符次数减1，如果次数次数小于0了，说明该字符在s1中不曾出现，或是出现的次数超过了s1中的对应的字符出现次数，那么我们此时移动滑动窗口的左边界，对于移除的字符串，哈希表中对应的次数要加1，如果此时次数不为0，说明该字符不在s1中，继续向右移，直到更新后的次数为0停止，此时到达的字符是在s1中的。如果次数大于等于0了，我们看此时窗口大小是否为s1的长度，若二者相等，由于此时窗口中的字符都是在s1中存在的字符，而且对应的次数都为0了，说明窗口中的字符串和s1互为全排列，返回true即可。
*/


class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        int[] freq = new int[26];
        for(int i=0; i<s1.length(); i++) freq[s1.charAt(i)-'a']++;       

            
        int slow =0, fast =0, n = s2.length();
        while(fast < n){
            char c = s2.charAt(fast);
            if(--freq[c-'a'] < 0){
                /*注意这里的条件是不等于0，因为只有我们刚刚减掉的那一个是-1，所以为了在找到一个
                所以只有当 ++freq[s2.charAt(slow)-'a'] == 0的时候才是刚好弥补了被
                减掉的那一个。注意被减掉的那一个不一定是出现在S1中的字符。
                */
                while( ++freq[s2.charAt(slow)-'a'] != 0){
                    slow++;
                }  
                slow++;
            }else if(fast-slow+1 == s1.length()){
                return true;
            }      
            fast++;
        }  

        return false;
    }
}



//------第二次写的，基本一致
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] array = new int[26];
        
        for(char c: s1.toCharArray()){
            array[c-'a']++;
        }
        
        int left = 0, right = 0;
        while(right < s2.length()){
            char c = s2.charAt(right);
            
            array[c-'a']--;
            if(array[c-'a'] < 0){
                while(left <= right && array[c-'a'] <0){
                    array[s2.charAt(left)-'a']++;
                    left++;
                }
            }
            
            if(right -left+1 == s1.length()) return true;
            right++;
        }
        return false;
    }
}

