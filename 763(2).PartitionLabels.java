/*763. Partition Labels
Medium: 
A string S of lowercase letters is given. We want to partition 
this string into as many parts as possible so that each letter 
appears in at most one part, and return a list of integers 
representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, 
because it splits S into less parts.
*/

/*解题思路
题目给出一堆乱序的小写字母，让我们找到分割这个字符串的方法，使得每个子字符串内出现
的字符不会在其他子字符串中出现。这道题目其实就是一个greedy的办法，因为每个字符串内
的字符在别的子字符串内不会出现，所以这个子字符串内每个字符最后出现的位置都必须在这个
子字符串内才行。那么我们首先对26个小写字母找到每个字母最后出现的位置。我们就用上面的
例子来说明一下算法。首先对于第一个字母a最后出现的位置是第8位。那么第一个子字符串
如果要囊括所有的a，我们第一个子字符串长度至少要为8。然后在2-8位之间，假设某一个字母最后出现
的位置大于8了，假设是10，那么说明我们必须要遍历到第十位才能使得这个子字符串中的字母不会
出现在别处。当然，例子中0-8就是第一个子字符串的长度，因为里面的b和c最后出现的位置都
小于8。我们用这种greedy的办法就可以在O(n)的时间范围内分割这个字符串S了。

*/

class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] list = new int[26];
        for(int i=0; i<S.length(); i++){
            char c = S.charAt(i);
            list[c-'a'] = i;
        }
        int slow = 0, fast = 0, max =0;
        List<Integer> res = new ArrayList<>();
        
        while(fast < S.length()){
            char c = S.charAt(fast);
            max = Math.max(max, list[c-'a']);
            if(fast == max){
                res.add(fast-slow+1);
                slow = fast+1;
            }
            fast++;

        }
      
        return res;
    }
}