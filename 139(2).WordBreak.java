/*139. Word Break
Medium: 
Given a non-empty string s and a dictionary wordDict containing a 
list of non-empty words, determine if s can be segmented into a 
space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

/*解题思路
这道拆分词句问题是看给定的词句能分被拆分成字典里面的内容。注意字典里的单词可以重复使用
这里我们就用一个一维的 dp 数组，其中 dp[i] 表示范围 [0, i) 内的子串是否可以拆分，注
意这里 dp 数组的长度比s串的长度大1，是因为我们要 handle 空串的情况，我们初始化 dp[0] 
为 true，然后开始遍历。注意这里我们需要两个 for 循环来遍历，因为此时已经没有递归函数了，
所以我们必须要遍历所有的子串，我们用j把 [0, i) 范围内的子串分为了两部分，[0, j) 和 
[j, i)，其中范围 [0, j) 就是 dp[j]，范围 [j, i) 就是 s.substr(j, i-j)，其中 
dp[j] 是之前的状态，我们已经算出来了，可以直接取，只需要在字典中查找 s.substr(j, i-j) 
是否存在了，如果二者均为 true，将 dp[i] 赋为 true，并且 break 掉，此时就不需要再用j
去分 [0, i) 范围了，因为 [0, i) 范围已经可以拆分了。最终我们返回 dp 数组的最后一个值，
就是整个数组是否可以拆分的布尔值了：
*/

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        int m = wordDict.size();
        boolean[] dp = new boolean[n+1]; //注意这里还是创建n+1个位置，代表第一位到第n位的数字
        dp[0] = true;
        
        for(int i=1; i<=n; i++){
            for(int j=0; j<m; j++){
                String word = wordDict.get(j);
                if(word.length() >i){
                    continue;
                }
                int start = i-word.length();
                if(dp[start] && s.substring(start, i).equals(word)){
                    dp[i] = true;
                }
            }
        }
        
        return dp[n];
    }
}