/*593. Valid Square
链接：
Medium: 
Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
 

Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.
*/

/*解题思路
题目给我们四个坐标点，让我们验证是不是正方形。验证办法倒是挺简单，对于每个点
计算和其他三个点的距离。如果是正方形的话起码有两个线的距离是一样的。如果可以
判断两个线是一样的，我们起码知道这是一个菱形了，此时我们在前面的条件下再
计算一下两个对角线是不是一样长的就可以判断了。
因为我们判断了四个点，然后每一对直角边都相等，所以四条边一定都是相等的。
我们用验证两个对角线相等而不是两个边程直角，是因为如果两个边垂直或者平行于
坐标轴的话斜率是无限大或者0，无法计算。

*/

class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if(check(p1, p2, p3, p4) && check(p2, p1, p3, p4) && check(p3, p2, p1, p4) && check(p4, p2, p3, p1)) return true;
      
        return false;
    }
    
    public boolean check(int[] a, int[] b, int[] c, int[] d){
        double len1 = Math.sqrt(Math.pow(a[0]-b[0], 2)+ Math.pow(a[1]-b[1], 2));
        double len2 = Math.sqrt(Math.pow(a[0]-c[0], 2)+ Math.pow(a[1]-c[1], 2));
        double len3 = Math.sqrt(Math.pow(a[0]-d[0], 2)+ Math.pow(a[1]-d[1], 2));
        
        //判断会不会有两个一样的点
        if(len1 == 0 || len2 == 0 || len3 == 0) return false;
        
        double len4 = 0.0;
        double len5 = 0.0;
       
        if(len1 == len2){
            len4 = Math.sqrt(Math.pow(a[0]-d[0], 2)+ Math.pow(a[1]-d[1], 2));
            len5 = Math.sqrt(Math.pow(b[0]-c[0], 2)+ Math.pow(b[1]-c[1], 2));
            if(len4 == len5) return true;
        }else if(len1 == len3){
            len4 = Math.sqrt(Math.pow(a[0]-c[0], 2)+ Math.pow(a[1]-c[1], 2));
            len5 = Math.sqrt(Math.pow(b[0]-d[0], 2)+ Math.pow(b[1]-d[1], 2));
            if(len4 == len5) return true;
        }else if(len2 == len3){
            len4 = Math.sqrt(Math.pow(a[0]-b[0], 2)+ Math.pow(a[1]-b[1], 2));
            len5 = Math.sqrt(Math.pow(c[0]-d[0], 2)+ Math.pow(c[1]-d[1], 2));
            if(len4 == len5) return true;
        }
        
        return false;
    }
}