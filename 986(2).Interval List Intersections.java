/*986. Interval List Intersections
Medium
链接: https://leetcode.com/problems/interval-list-intersections/

Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

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

/*
因为这两个数组内的区间互相不交叉，我们还可以用扫描线的做法来做这道题目。
扫描线的经典套路就是首先将每个区间的起始位置标注为1，结束位置标志为-1。然后我们用一个整数代表扫描过所有区间的线。碰到区间的起始位置的时候加一，结束位置减一。这样这个整数就可以代表了重合的区间段有多少。在这道题目里面我们也首先这样子将A数组和B数组当中的所有元素都加入到我们的list当中，每一个新的元素我们用一个简单的数组来表示，数组的第一位是这个元素出现的位置，第二位代表了这个元素是起始点还是终点。我们遍历A和B两个数组之后首先将所有的元素放入List当中，然后需要将新的list按照位置排个序。

我们需要三个变量，第一个变量count代表的是当前的扫描线，也就是累积起来的重合区间的个数。prevCount代表的是在遇到当前线段(头/尾)的前一个重合的区间个数。prev代表的是上一个线段(头/尾)的位置。

我们观察例子中的模式，只有两种情况下区间会被加入到结果中。
第一种是，prevCount = 0且 count = 1，并且前一位的结尾(prev)正好等于当前位置pos[0]的情况。代表的是前一个区间的结尾和当前区间的开头正好重合的情况。

A: |------|
B:         |-----|

或者:

B:         |-----|
A: |------|

第二种情况就是prevCount = 2且count= 1的情况，代表的是有两段区间重合，并且此时其中某一段区间已经结尾的情况。
可以是:

A: |---------|
B:   |----|

或者

A: |---------|
B:          |--------|

所有的情况都不外乎这两种情况。所以每一次遇到这两种情况其中之一我们就可以将[prev, pos[0]]这一段区间加入到结果中了。

这一个做法的缺点就是在给所有元素排序的时候需要O(NLOGN)的时间复杂度，当然，这个可以通过两个指针顺序遍历两个数组优化到O(N)的时间复杂度
*/

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if(A.length == 0 || B.length == 0) return new int[][]{};
        List<int[]> list = new ArrayList<>(), res = new ArrayList<>();
        for(int[] a : A){
            list.add(new int[]{a[0], 1});
            list.add(new int[]{a[1], -1});
        }
        
        for(int[] b : B){
            list.add(new int[]{b[0], 1});
            list.add(new int[]{b[1], -1});
        }
        
        Collections.sort(list, (a,b) -> a[0]-b[0]);
        int count =0, prev =-1, prevCount=0;

        for(int[] pos : list){
            prevCount = count;
            count+=pos[1];

            if(prevCount == 0 && count == 1){
                if(pos[0]-prev > 0) continue;
                res.add(new int[]{prev, pos[0]});
            }else if(prevCount == 2 && count ==1){
                res.add(new int[]{prev, pos[0]});
            }
            prev = pos[0];
        }
     
        int[][] anw = new int[res.size()][2];
        for(int i=0; i< res.size(); i++) anw[i] = res.get(i);
        return anw;
      
    }
}