/*986. Interval List Intersections
Medium

Given two lists of closed intervals, each list of intervals is 
pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the 
set of real numbers x with a <= x <= b.  The intersection of 
two closed intervals is a set of real numbers that is either 
empty, or can be represented as a closed interval.  For example, 
the intersection of [1, 3] and [2, 4] is [2, 3].)

Example 1:
Input: A = [[0,2],[5,10],[13,23],[24,25]], 
	   B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of 
Interval objects, and not arrays or lists.

*/

/*解题思路
对于所有的A片段来说，总共会出现以下三种情况。相反，B也可能出现下面三种情况(在这里就没有重复的写)

A: |---------|        A: |---------|         A:  |------|
B:   |----|           B:      |--------|     B:            |-----|

我们需要两个指针，pa和pb来遍历两个数组。
对于情况1来说，我们首先记录B的范围，因为B完全被A所包括。在这之后，我们应该将B的指针加一。
因为B的下一位指针还有可能和A[pa]的区间有所重合。
对于情况2来说，我们首先将B的起始位置和A的结束位置作为一个新的范围记录下来。在这之后我们应该
将A的指针往下移动一位，因为B的结束点在这两个线段靠后的位置，所以可能和A的下一位匹配。
对于情况3来说，我们应该移动的是A的坐标，因为这样才有可能和B的线段区间匹配。
综上所述，谁区间结束的早，我们应该将起移动到下一位
*/

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if(A.length == 0 || B.length==0) return new int[0][0];
    	List<int[]> list = new ArrayList<>();

    	int pa = 0, pb = 0;
    	while(pa<A.length && pb < B.length){
            
    		int[] ta = A[pa];
    		int[] tb = B[pb];
    		if(ta[0] >= tb[0] && ta[0] <= tb[1]){
    			if(ta[1] <= tb[1]){
    				list.add(ta);
                    pa++;
    			}else{
    				list.add(new int[]{ta[0], tb[1]});
                    pb++;
    			}
    		}else if(tb[0]>= ta[0] && tb[0]<=ta[1]){
    			if(tb[1] <= ta[1]){
    				list.add(tb);
                    pb++;
    			}else{
    				list.add(new int[]{tb[0], ta[1]});
                    pa++;
    			}
    		}else if(ta[0]>tb[1]) pb++;
    		else if(tb[0] > ta[1]) pa++;
    	}
        

        int[][] res = new int[list.size()][2];
    	for(int i=0; i< list.size(); i++) res[i] = list.get(i);
        return res;
    }
}

//另一种写法
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int numA = 0;
        int numB = 0;
        int start = 0;
        int end = 0;
        ArrayList<int[]> list = new ArrayList<>();
        while (numA < A.length && numB < B.length){
            start = Math.max(A[numA][0], B[numB][0]);
            if (A[numA][1] <= B[numB][1]){
                end = A[numA][1];
                if (end >= start){
                    list.add(new int[]{start, end});
                }
                numA++;
            }
            else{
                end = B[numB][1];
                if (end >= start){
                    list.add(new int[]{start, end});
                }
                numB++;
            }
        }
        int[][] result = new int[list.size()][];
        for (int i =0; i < result.length; i++){
            result[i] = list.get(i);
        }
        return result;
       
    }
}