/*364. Nested List Weight Sum II
Medium: 

Given a nested list of integers, return the sum of all integers in 
the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may 
also be integers or other lists.

Different from the previous question where weight is increasing 
from root to leaf, now the weight is defined from bottom up. i.e., 
the leaf level integers have weight 1, and the root level integers 
have the largest weight.

Example 1:
Input: [[1,1],2,[1,1]]
Output: 8 
Explanation: Four 1's at depth 1, one 2 at depth 2.

Example 2:
Input: [1,[4,[6]]]
Output: 17 
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1;
 1*3 + 4*2 + 6*1 = 17.
*/

/*解题思路
时隔八个月又来做一次这个题目。一开始用的办法是Bottom-up, 但是[[-1],[[-1]]]这个例子没有过。
原因是bottom-up的时候没有办法解决平行的两个独立list中的权重问题。像这个例子里面，我的结果
是-2, 但是结果应该是-3。原因是从低往上走，每个都是-1, 但是事实上左边那个应该乘以2，因为右边
那个又比左边这个低一层。
所以就转变成用top-down的解法。思路比较简单，每一次往下走的时候，在list里新加入一个数值，
用阿里代表这一层的总和。因为我们是逐层递增的，所以插入新元素的时候只需要加在列表后面就可以了。
每一次如果有新的元素要被加进来，先看这一层是否已经被遍历过了。如果已经被遍历过的话，直接
把这个值先取出来，加完这一层所有整数后，再插入回List中。
最后遍历这个list, 因为我们首先插入的是最后一层。所以这一层应该为一，然后往前遍历list的时候
将level+1就可以了。

*/
class Solution {   
    public int depthSumInverse(List<NestedInteger> nestedList) {
        List<Integer> list = new ArrayList<>();
        helper(nestedList, list, 0);
        int sum = 0, s = list.size();
        for(int i=s-1; i>=0;i--){
            sum += list.get(i)*(s-i);
        }

        return sum;
    }

    public void helper(List<NestedInteger> nestedList, List<Integer> list, int level){
        if(nestedList.size() ==0 ) return;
        if(list.size()<=level) list.add(0);
        int temp  = list.get(level);
        for(NestedInteger n: nestedList){
            if(n.isInteger()) temp+=n.getInteger();
            else helper(n.getList(), list, level+1);
        }
        list.remove(level);
        list.add(level, temp);
    }
}


/*
这道题还有一个解题思路，其实我们也可以直接从上往下逐层遍历。
假设我们总共有三层。第一层总和是A, 第二层是B，第三层为C。
那我们从上往下遍历的时候就是，第一层是A，第二层是A+A+B, 第三层是A+A+A+B+B+C
相当于第一层多加了两次，第二层多加了一次。
*/


//循环累加的办法
class Solution {   
    public int depthSumInverse(List<NestedInteger> nestedList) {
    	int unweighted = 0, weighted = 0;
    	while(!nestedList.isEmpty()){
    		List<NestedInteger> nextLevel = new ArrayList<>();
    		for(NestedInteger n: nestedList){
    			if(n.isInteger()) unweighted+=n.getInteger();
    			else nextLevel.addAll(n.getList());//注意这里
    		}
    		weighted+=unweighted;
    		nestedList = nextLevel;
    	}
    	return weighted;
    }
}
