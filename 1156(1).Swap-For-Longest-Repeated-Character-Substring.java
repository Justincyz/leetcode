/*1156. Swap For Longest Repeated Character Substring
Medium
链接: https://leetcode.com/problems/swap-for-longest-repeated-character-substring/
Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.


Example 1:
Input: text = "ababa"
Output: 3
Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.

Example 2:
Input: text = "aaabaaa"
Output: 6
Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.

Example 3:
Input: text = "aaabbaaa"
Output: 4

Example 4:
Input: text = "aaaaa"
Output: 5
Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.

Example 5:
Input: text = "abcdef"
Output: 1
*/

/*解题思路
这道题目给我们一个字符串，字符串当中全部是小写的英文字母。有一次机会两个字符做一次
交换，目的是组合成一个重复子字符串。让我们找到最长的子字符串有多长。详细可见例子。


第一种解法:
一开始我想到的是用一个双指针，但是后来发现corner case太多了。不好做，看了一下hint, 
Hint告诉我们，只有两种情况，第一个是两个相同字符的子字符串当中有一个元素不相同，或者是单独
出现的一个相同字符的子字符串。
根据这个hint, 我想到，可以用一个哈希表。Key是字符，value是这个字符出现的区间在哪里。
比如说对于字符串"aaabaabba"来说，
a -> [[0,2],[4,5],[8,8]]
b -> [[3,3],[6,7]]

接下来要做的就是检查这些区间是否有融合的可能性了。
直接在代码里标注一些不同的corner cases吧

最后，时间复杂度是O(N)，空间复杂度也是O(N)
*/

class Solution {
    public int maxRepOpt1(String text) {
        Map<Character, List<Interval>> sameCharWithInterval = new HashMap<>();
        int pre =0;
        
        //之所以要让i <=text.length()而不是只是小于text.length()，是因为要考虑最后一段substring也能被囊括。
        for(int i=1; i<=text.length(); i++){
            if(i < text.length() &&  text.charAt(pre) == text.charAt(i)){
                continue;
            }else{
                if(!sameCharWithInterval.containsKey(text.charAt(pre))){
                    sameCharWithInterval.put(text.charAt(pre), new ArrayList());
                }
                sameCharWithInterval.get(text.charAt(pre)).add(new Interval(pre, i-1));
                if(i < text.length()) pre = i;
            }
        }
      
        
        int longestSubstring = 0;
        
        for(Map.Entry<Character, List<Interval>> entry: sameCharWithInterval.entrySet()){
            //获取同一个字符的所有区间信息
            List<Interval> charList =  entry.getValue();

            //首先单独判断第一个区间，原因是我们不知道这个链表有多长
            Interval first = charList.get(0);
            if(charList.size() > 1)
                /*如果区间个数大于1，说明最少可以拿一个字符贴到当前字符串。比如说:
                "aaabbaa", 对于第一个连续的a来说，我们可以通过交换一个b和后面区间
                的a达到 - > aaaabba这样的结果。所以要加上一个元素 
                */
                longestSubstring = Math.max(longestSubstring, first.end - first.start + 2);
            else
                /*如果区间个数等于1，说明当前子字符串只能自己独立成块。比如说:
                "aaabb", 对于第一个连续的a来说，没有其他的a区间可以贴上第一个a区间。
                */
                longestSubstring = Math.max(longestSubstring, first.end - first.start + 1);
            
            for(int i=1; i< charList.size(); i++){
                Interval prevInterval = charList.get(i-1);
                Interval curInterval = charList.get(i);
                
                //循环里保证有两个或者以上的区间，所以不管怎么样当前区间都可以通过一次swap增加长度1
                longestSubstring = Math.max(longestSubstring, curInterval.end - curInterval.start+2);
                
                int gap = curInterval.start - prevInterval.end-1;
                
                //是否Gap可以通过一次swap被去掉
                if(gap == 1){
                    /*当区间块个数大于2，我们可以通过将第三个区间块的元素swap过来。
                    比如说， aaabaabbca, -> aaaaaabbbc
                    */
                    if(charList.size() > 2)
                        longestSubstring = Math.max(longestSubstring, curInterval.end - prevInterval.start + 1);
                    else 
                        /*如果区间块只有两个，那么只能通过交换这两个区间块其中一个的节点达到连通的效果。
                        比如说， aaabaabbc, -> aaaaabbbc
                        */
                        longestSubstring = Math.max(longestSubstring, curInterval.end - prevInterval.start);
                }
            }
        }
        
        return longestSubstring;
    }
}

class Interval{
    int start;
    int end;
    public Interval(int s, int e){
        start = s;
        end =e;
    }
}

/*
第二种解法: sliding windows + two pointers

这道题目的做法其实跟424. Longest Repeating Character Replacement 很像。
详见424的解法总结。
我们首先计算下每一个字符总共出现的次数。然后我们从第一个元素开始，构建一个
window。在这个window当中我们保证两个点。第一是保证只有至多一个元素被替换，第二
是这个window范围内以出现频率最高的那一个元素作为唯一的替换标准。

请看代码注释
*/
class Solution {
    public int maxRepOpt1(String s) {
        int[] cntSoFar = new int[26], cntOfChar = new int[26];
 
        for(char c:s.toCharArray()) cntOfChar[c-'a']++;
        
        int start = 0, maxCharCnt = 0, maxLength = 0;
        
        for (int end = 0; end <  s.length(); end++) {
            //每一次都更新window之内最高频率的元素
            maxCharCnt = Math.max(maxCharCnt, ++cntSoFar[s.charAt(end) - 'a']);
            
            /*(如果被替换的元素超过了一个) 或 (没有可以被替换的相同元素了)
            没有可以被替换的相同元素，指的是"aaabaaa"，虽然最长的字符串保证了至多
            一个元素被替换(在这里是b)，但是在范围[0,6]直接因为没有多余的"a"可以
            替换"b"，所以我们左边的指针不得不往前移动一个，匀出来一个a用以替换中间
            的b.
            */
            while (end - start + 1 - maxCharCnt > 1  || end - start + 1 > cntOfChar[s.charAt(start)-'a']) {
                cntSoFar[s.charAt(start) - 'a']--;
                start++;
                maxCharCnt=0;
                //为下一个window区间找到一个最高频率的字符
                for(int i = 0; i < 26; i++){
                    maxCharCnt = Math.max(maxCharCnt, cntSoFar[i]);
                }
            }
            
            maxLength = Math.max(maxLength, end - start + 1);
        }
        
        return maxLength;
    }
}


//进一步简化，这个严格保证了O(N)的时间和空间复杂度
class Solution {
    public int maxRepOpt1(String text) {
        int windowSize = 0;
        int[] total = new int[26];
        char[] ch = text.toCharArray();
        for (char c : ch) {
            total[c - 'a']++;
        }
        char maxChar = ch[0];
        int max = 0;
        int[] count = new int[26];
        for (int i = 0; i < ch.length; i++) {
            count[ch[i] - 'a']++;
            if (count[ch[i] - 'a'] > max) {
                max = count[ch[i] - 'a'];
                maxChar = ch[i];
            }
            if (max > windowSize) {
                windowSize++;
            } else if (max == windowSize && total[maxChar - 'a'] > max) {
                windowSize++;
            } else {
                count[ch[i - windowSize] - 'a']--;
            }
        }
        return windowSize;
    }
}