/*354. Russian Doll Envelopes
Hard

You have a number of envelopes with widths and heights given as 
a pair of integers (w, h). One envelope can fit into another if 
and only if both the width and height of one envelope is greater
 than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? 
(put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3 
Explanation: The maximum number of envelopes you can Russian 
doll is 3 ([2,3] => [5,4] => [6,7]).
*/

/*解题思路
这道题有两种解法，第一种是dp,第二种是binary search.
1. DP
首先就是排序，我们对envelopers的宽度先从小到大排序，然后在宽度一致的情况下。
高度小的排在前面。然后我们遍历每一个排序好的envelops, 比较他前面所有比他长宽
小的envelope里可以装最多的其他envelopes， 然后储存。 也就是dp[i] = Math.max(dp[i], dp[j]+1)
当然，每一个信封都是独立存在的，所以我们要先把所有的信封初始值设置为1.

2.Binary Search
上面一种方法速度比较慢，而且是将近O(n^2)的时间复杂度。这里我们可以用binary search的办法
替代。首先这个方法是基于300题, longest increasing subsequence(LIS)的binary search做出来的。
首先我们还是要排序。但是稍微有点不一样的是当我们遇到宽度一致的情况时，我们把高度大的信封
排在前面。 此时我们就把问题转换成找所有信封高度的LIS的问题了。因为我们已经知道信封是按照
宽度排列的，那么不同宽度的信封排序是升序的，而同一宽度而高度不同信封排列是降序的。(所以同样宽度的
区间内我们最多只能取一个值)那我们只需要从头到尾遍历envelopes找到最长的升序高度的subsequence就
可以了。具体为什么用binary search讲解在300题写的很清楚，用做参考。
*/

// O(n!)
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length == 0) return 0;
        //sort the array first
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] a, int[]b){
                if(a[0]!=b[0]) return a[0]-b[0];
                else return a[1]-b[1];
            }
        });
        int n = envelopes.length;
        int[]dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if(envelopes[i][0]>envelopes[j][0] && envelopes[i][1]>envelopes[j][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);//记得要更新最大值
        }
        return max;
    }
}

//dp solution O(nlogn) 
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length == 0) return 0;
        //sort the array first
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] a, int[]b){
                if(a[0]!=b[0]) return a[0]-b[0];
                else return b[1]-a[1];
            }
        });
        
        int n = envelopes.length;
        int[]dp = new int[n+1];
        int end =1;//长度不管怎么样都起码有一个
        dp[end] = envelopes[0][1];
        for(int[] env : envelopes){
            int height = env[1];
            if( height< dp[0]) dp[0] = env[1];
            else if(height > dp[end]) dp[++end] = env[1];
            else{
                int left = 0, right= end;
                while(left < right){
                    int mid = left + (right-left)/2;
                    if(dp[mid] < height) left = mid+1;
                    else right = mid;
                }
                dp[right] = height;
            }
        }
        return end;
    }
}


