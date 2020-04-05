/*424. Longest Repeating Character Replacement
链接：https://leetcode.com/problems/longest-repeating-character-replacement/
Medium:

Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.

In one operation, you can choose any character of the string and change it to any other uppercase English character.

Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:
Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
 

Example 2:
Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 
*/

/*解题思路 sliding windows + two pointer
题目给我们一个只包含大写字母的string, 让我们找到一个substring, 其中我们
随意置换里面的k个字符，使得这个substring只存在一种字符。让我们找到一个最长的
满足条件的substring。这道题目会跟 Longest Substring with At Most K Distinct Characters，
和 1156. Swap For Longest Repeated Character Substring 比较像。都是要用到sliding window
的办法。
我们可以这样来转换想这个问题。首先，对于这个String来说，如果题目问的是让我们买把这个string当中
的所有的字符都转化成同一个字符，那么最少的转化次数是多少。那么我们其实就是找到这个string当中
出现频率最高的字符，然后将其余的字符都转化成这个字符。 假设总共有n个字符，那么需要转换的次数
就是 n-(highest frequent characters) = k。在这里，这个window size变成了整个字符串的长度。

那么我们再来看这个题目，首先在这里我们有一个限制条件k。我们利用两个pointer保持一个窗口的大小。
在这个window当中我们需要知道出现频率最高的字符是什么，这样其余的就可以用k个自由字符代替了。
假设窗口内除了最大频率的字符的其余字符个数大于k了，那么我们就需要移动左边的指针，直到需要被
改变的个数小于等于k个。注意，此时maxChar指代的字符可能会因为左边边界的移动而变化，这个变化只能
在下一次判断的时候体现出来。
网上的解释是(需要注意的是，当滑动窗口的左边界向右移动了后，窗口内的相同字母的最大个数貌似可能会
改变啊，为啥这里不用更新 maxCnt 呢？这是个好问题，原因是此题让求的是最长的重复子串，maxCnt 相
当于卡了一个窗口大小，我们并不希望窗口变小，虽然窗口在滑动，但是之前是出现过跟窗口大小相同的符
合题意的子串，缩小窗口没有意义，并不会使结果 res 变大，所以我们才不更新 maxCnt 的，)


类似的题目还有: 1156. Swap For Longest Repeated Character Substring
*/

class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0, right = 0, maxWindowSize=0, maxCharCnt = 0;
               
        while(right < s.length()){
            char c = s.charAt(right);
            count[c-'A']++;
            maxCharCnt = Math.max(maxCharCnt, count[c-'A']);
            //最多可替换字符个数 : right - left - maxChar + 1
            
            while(right - left - maxCharCnt + 1 > k){
                count[s.charAt(left)-'A']--;
                left++;
            }
            maxWindowSize = Math.max(maxWindowSize, right-left+1);
            right++;
        }
       
      
        return maxWindowSize;
    }
}
