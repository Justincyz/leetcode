/*370. Range Addition
Medium

Assume you have an array of length n initialized with all 0's and are given 
k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc]
which increments each element of subarray A[startIndex ... endIndex] 
(startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:
Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]
Explanation:

Initial state:
[0,0,0,0,0]

After applying operation [1,3,2]:
[0,2,2,2,0]

After applying operation [2,4,3]:
[0,2,5,5,3]

After applying operation [0,2,-2]:
[-2,0,3,5,3]


*/

/*解题思路
这道题目有点tricky。做法是这样的，我们将起始的index加上increment, 在结束的index+1的位置
减去increment，最后对array做一个累加，多余的那一位可以不用管它，因为已经结束了。我们单看
一个update的时候，在起始index之后相当于这一段的值都为起始index 的累加的sum，当遇到结束index+1
的时候就相当于回归原样。
*/

class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length+1];
        for(int[] i : updates){
        	res[i[0]] += i[2];
        	res[i[1]] -= i[2];
        }
        int sum = 0;
        int[] output = new int[length];
        for(int i=0; i< length;i++){
        	output[i] = res[i]+sum;
        	sum+=res[i];
        }
        return output;
    }
}