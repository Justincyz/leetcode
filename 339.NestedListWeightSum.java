/*339. Nested List Weight Sum
Easy
Given a nested list of integers, return the sum of all integers in 
the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may 
also be integers or other lists.

Example 1:
Input: [[1,1],2,[1,1]]
Output: 10 
Explanation: Four 1's at depth 2, one 2 at depth 1.

Example 2:
Input: [1,[4,[6]]]
Output: 27 
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at 
depth 3; 1 + 4*2 + 6*3 = 27.

已经定义好的接口
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
*/

/*解题思路
直接就DFS， 有一个要注意的就是上面的方法都是作用在元素上而不是直接在list里面

*/

class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList.size() ==0 ) return 0;
        
        int sum = 0;
        for(NestedInteger n: nestedList){
            if(n.isInteger()) sum+=n.getInteger();
            else sum+=helper(n.getList(), 2);
        }
        return sum;
    }

    public int helper(List<NestedInteger> nestedList, int depth){
        if(nestedList.size() ==0 ) return 0;

        int sum = 0;
        for(NestedInteger n: nestedList){
            if(n.isInteger()) sum+=(n.getInteger()*depth);
            else sum+=helper(n.getList(), depth+1);
        }
        return sum;
    }
}