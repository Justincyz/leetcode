/*
链接：
Hard: 
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

Example: 具体例子见图
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
*/

/*解题思路
找到最高的柱子，然后从两边往中间遍历

*/


/*先找到最高的点，然后从两端往中间扫。重点: 在遇到最高点之前，水位会增加，注意不是持续增加。所以只需要记录每一次遇到的最高点，然后计算此最高点和之前遇到的最高点之间的容量减去其中的的blocks就可以了*/
class Solution {
    public int trap(int[] height) {
        int pos = 0;
        for(int i=0; i< height.length; i++){
            pos = height[pos] > height[i] ? pos : i;
        }
        int maxPos = 0, area = 0, blocks = 0;
        
        for(int i = 1; i<= pos; i++){
            if(height[i] ==0 ) continue;
            if(height[i] >= height[maxPos]){
                area += (i - maxPos-1)*(height[maxPos]);
                area -= blocks;
                blocks = 0;
                maxPos = i;
            }else{
                blocks+= height[i];
            }
        }
     
        maxPos = height.length-1; blocks = 0;   
        for(int i = height.length -2; i>= pos; i--){
            if(height[i] >= height[maxPos]){
                area += (maxPos-i-1)*(height[maxPos]);
                area -= blocks;
                blocks = 0;
                maxPos = i;
            }else{
                blocks+= height[i];
            }
        }
        
        return area;
    }
}


//2019/9/26 第二次写
class Solution {
    public int trap(int[] height) {
        int pos =0, n = height.length;
        for(int i=0; i< n; i++){
            if(height[i] > height[pos]) pos = i;
        }
      
        Stack<Integer> stack = new Stack();
        int res = 0;
        for(int i=0; i< pos; i++){
            int h = height[i];
            if(!stack.isEmpty()){
                if(height[stack.peek()] <= h){
                    stack.pop();
                    stack.push(i);
                }else{
                    res += (height[stack.peek()] - h);
                }
            }else{
                stack.push(i);
            }
        }
        
        stack = new Stack();
  
        for(int i=n-1; i> pos; i--){
            int h = height[i];
            if(!stack.isEmpty()){
                if(height[stack.peek()] <= h){
                    stack.pop();
                    stack.push(i);
                }else{
                    res += (height[stack.peek()] - h);
                    
                }
            }else{
                stack.push(i);
            }
        }
        
        return res;
    }
}