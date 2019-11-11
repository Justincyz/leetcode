/*281. Zigzag Iterator
Medium
链接: https://leetcode.com/problems/zigzag-iterator/
Given two 1d vectors, implement an iterator to return their elements alternately.

Example:

Input:
v1 = [1,2]
v2 = [3,4,5,6] 

Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,3,2,4,5,6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If 
"Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].


*/

/*解题思路
这道题目就是让我们顺序输出两个list当中的的元素。follow - up让我们输出k个list中
按照顺序输出元素，因为这个是按照顺序的，我们可以直接用一个queue<>对每个链表中的位置
进行追溯。

*/

public class ZigzagIterator {
    int p1 =0, p2 =0;
    List<Integer> v1, v2;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        if(hasNext() == false) return -1;
        int next = -1;
        if(p1 > p2){//此时p1的位置在p2之后，所以此时我们按道理要挪动p2
            if(p2 < v2.size()){//如果p2此时没到底
                next = v2.get(p2);
                p2++;
            }else{//如果p2已经到底了那我们只能挪动p1了
                next = v1.get(p1);
                p1++;
            }
        }else{//当p1等于p2或者小于p2的时候都应该取p1的值
            if(p1 < v1.size()){ //如果此时可以取到p1的值
                next = v1.get(p1);
                p1++;
            }else{//如果取不到p1的值，那我们只能往后挪动p2的值了
                next = v2.get(p2);
                p2++;
            }
        }
        return next;
    }

    public boolean hasNext() {
    	//如果两个都到底了就是false
        return p1 < v1.size() || p2 < v2.size();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */