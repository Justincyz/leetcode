/*84. Largest Rectangle in Histogram
链接：https://leetcode.com/problems/largest-rectangle-in-histogram/submissions/
Hard: 
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

Example:

Input: [2,1,5,6,2,3]
Output: 10
*/

/*解题思路
就是遍历数组，每找到一个局部峰值（只要当前的数字大于后面的一个数字，那么当前数字就看作一个局部峰值，跟前面的数字大小无关），然后向前遍历所有的值，算出共同的矩形面积，每次对比保留最大值。这里再说下为啥要从局部峰值处理，看题目中的例子，局部峰值为 2，6，3，我们只需在这些局部峰值出进行处理，为啥不用在非局部峰值处统计呢，这是因为非局部峰值处的情况，后面的局部峰值都可以包括，比如1和5，由于局部峰值6是高于1和5的，所有1和5能组成的矩形，到6这里都能组成，并且还可以加上6本身的一部分组成更大的矩形，那么就不用费力气去再统计一个1和5处能组成的矩形了。代码如下：

*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length ==0) return 0;
        Stack<Integer> stack = new Stack<>();
        int res =0;
        for(int i=0; i<= heights.length; i++){
            int h = i == heights.length ? 0 : heights[i];
            while(!stack.isEmpty() && h< heights[stack.peek()]){
                int height = heights[stack.pop()];
                int start = stack.isEmpty()? -1: stack.peek();
                int area = height*(i-start -1);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }
}


class Solution {
	public static int largestRectangleArea(int[] height) {
	    if (height == null || height.length == 0) {
	        return 0;
	    }
	    int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
	    int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
	    lessFromRight[height.length - 1] = height.length;
	    lessFromLeft[0] = -1;

	    for (int i = 1; i < height.length; i++) {
	        int p = i - 1;

	        while (p >= 0 && height[p] >= height[i]) {
	            p = lessFromLeft[p];
	        }
	        lessFromLeft[i] = p;
	    }

	    for (int i = height.length - 2; i >= 0; i--) {
	        int p = i + 1;

	        while (p < height.length && height[p] >= height[i]) {
	            p = lessFromRight[p];
	        }
	        lessFromRight[i] = p;
	    }

	    int maxArea = 0;
	    for (int i = 0; i < height.length; i++) {
	        maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
	    }

	    return maxArea;
	}
}