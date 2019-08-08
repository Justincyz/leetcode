/*767. Reorganize String    

Medium: 
Given a string S, check if the letters can be rearranged so that 
two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return 
the empty string.

Example 1:
Input: S = "aab"
Output: "aba"

Example 2:
Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
*/

/*解题思路 (1054. Distant Barcodes)
题意是说，给定一个字符串，检查是否可以重新组合使每个字符和旁边的两个都不一样。
首先就是计算每一个字符出现的次数，如果次数最大的字符已经超过了长度的一半加一，说明
没有办法构成指定形式的字符串，直接返回空。加一的原因是有可能长度为5的字符串里有三个都是
一样的，这样也可以排列成功。如果可以构成的话，那么我们只需要把次数出现最多的字符从头开始
间隔1来排列就可以了。将其余的也是按照间隔一的办法排列。相当于我们首先把奇数位置排满，再
遍历储存的数组，将偶数为也排满，然后就可以了。不用考虑字符串长度溢出的问题，
因为创造的数组跟原字符串长度是一致的。

*/

//0ms
class Solution {
    public String reorganizeString(String S) {
        int[] list = new int[26];
        int n = S.length();
        for(int i=0; i<S.length();i++){
            char c = S.charAt(i);
            list[c-'a']++;
        }
        
        int max = list[0];
        int c = 0;
        for(int i = 1; i< 26; i++){
            if(max < list[i]){
                max = list[i];
                c = i;
            }
        }
        
        if(max > (S.length()+1)/2) return "";
        
        char[] res = new char[n];
        int idx = 0;
        for(int i=0; i<max; i++){
            res[idx] = (char)(c+'a');
            list[c]--;
            idx+=2;
        }
        for(int i=0; i<26; i++){
            if(list[i] !=0){
                while(list[i] -- >0){
                    if(idx+1 >n) idx = 1;
                    res[idx] = (char)(i+'a');
                    idx+=2;
                }
            }
        }
        return new String(res);
    }
}

/*
这个用PriorityQueue的方法就是每次取出出现频率最高的字符。为了保证不会
重复取出同一个字符，每一次先保存当前取出的字符到prev中，然后取出新的字符。
再将上一次取出的放入pq中。最后将当前的赋值给上一个。
*/
//33ms PriorityQueue
public String reorganizeString(String S) {
    PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    int[] m = new int[26];
    for(int i = 0; i < S.length(); i++) m[S.charAt(i) - 'a']++; // map of char counts

    for(int i = 0; i < 26; i++) {
        if(m[i] != 0) {
            q.offer(new int[] {i, m[i]}); // add char counts to priority queue
        }
    }

    int[] prev = new int[] {-1,0};
    StringBuilder sb = new StringBuilder();
    while(!q.isEmpty()) {
        int[] cur = q.poll();
        if(prev[1] > 0) q.offer(prev); // add back last used character

        sb.append((char)(cur[0] + 'a')); // append current character
        cur[1]--; // decrease count of current char since it's used
        prev = cur; // set this character as previous used
        if(q.isEmpty() && prev[1] > 0) return ""; // if we left with anything return ""
    }
    return sb.toString();
}

