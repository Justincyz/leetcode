/*302. Smallest Rectangle Enclosing Black Pixels
Hard: 

An image is represented by a binary matrix with 0 as a white pixel 
and 1 as a black pixel. The black pixels are connected, i.e., there 
is only one black region. Pixels are connected horizontally and vertically. 
Given the location (x, y) of one of the black pixels, return the area 
of the smallest (axis-aligned) rectangle that encloses all black pixels.

Example:

Input:
[ "0010",
  "0110",
  "0100"]
and x = 0, y = 2
Output: 6
*/

/*解题思路
这道题没想到是一道hard题目。其实抽象出来就很简单。就是找到这个黑色面积四个边的顶点。
然后把长宽相乘就可以得到最小的囊括这个'1'字区域的长方形了。我用的是DFS的方法来写的。2ms
过了。一看发现还有用二分法来做的。针对这个题目能达到1ms的速度。方法其实也很简单粗暴。
也是从给的(x,y)点出发。以初始点分成上下左右四个方向来找边界。对于row方向，left和right要遍历
以row为纵坐标的所有横坐标的点，也就是image[x][row] (0<=x<起始点 和 起始点<=x< image.length)。
col方向也是一样。其实就是用四条线不断地逼近黑色区域的边界。
*/

class Solution {
	int left, right, up, down;
	int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
    public int minArea(char[][] image, int x, int y) {
    	left =y;
    	right = y;
    	up= x;
    	down = x;
		helper(image, x, y);
		return (right-left+1)*(down - up+1);       
    }

    public void helper(char[][] image, int x, int y){
    	if(x<0|| y<0 || x>=image.length|| y>= image[0].length || image[x][y] == '0') return;
    	image[x][y] = '0';
    	if(y<left) left = y;
    	else if(y>right) right = y;
    	if(x>down) down =x;
    	else if(x < up) up = x;
    	helper(image, x, y+1);
    	helper(image, x, y-1);
    	helper(image, x-1, y);
    	helper(image, x+1, y);
    }
}


//binary search的另一个方法，可能对1占得面积大的区域速度会更快些
class Solution {
    public int minArea(char[][] image, int x, int y) {
        int left = leftMost(image, 0, y, true);
        int right = rightMost(image, y, image[0].length-1, true);
        int top = leftMost(image, 0, x, false);
        int bottom = rightMost(image, x, image.length-1, false);
        return (right-left+1)*(bottom-top+1);
    }
    
    private int leftMost(char[][] image, int low, int high, boolean horizental) {
          while(low < high) {
              int mid = low + (high - low)/2;
              if(!hasBlack(image, mid, horizental)) {
                  low = mid+1;
              }
              else {
                  high = mid;
              }
          }
         return low;
    }
    
        private int rightMost(char[][] image, int low, int high, boolean horizental) {
          while(low < high) {
              int mid = low + (high - low + 1)/2;
              if(!hasBlack(image, mid, horizental)) {
                  high = mid - 1;
              }
              else {
                  low = mid;
              }
          }
         return low;
      }
    
     private boolean hasBlack(char[][] image, int i, boolean horizental) {
         if(horizental) {
             for(int k=0; k<image.length; k++) {
                 if(image[k][i] == '1') return true;
             }
         }
         else {
             for(int k=0; k<image[0].length; k++) {
                 if(image[i][k] == '1') return true;
             }
             
         }
         return false;
     }
}