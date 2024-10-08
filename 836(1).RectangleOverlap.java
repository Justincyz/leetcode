/*836. Rectangle Overlap
Easy: 
A rectangle is represented as a list [x1, y1, x2, y2], 
where (x1, y1) are the coordinates of its bottom-left corner, 
and (x2, y2) are the coordinates of its top-right corner.

Two rectangles overlap if the area of their intersection is positive.  
To be clear, two rectangles that only touch at the corner or edges 
do not overlap.

Given two (axis-aligned) rectangles, return whether they overlap.

Example 1:

Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
Output: true
Example 2:

Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
Output: false
*/

/*解题思路
这道题让我们求两个矩形是否是重叠，矩形的表示方法是用两个点，左下和右上点来定位的。
首先，在玩2D之前，先看下1D上是如何运作的。对于两条线段，它们相交的话可以是如下情况：

           x3             x4
           |--------------|
 |--------------|
 x1             x2
我们可以直观的看出一些关系： 

x1 < x3 < x2 && x3 < x2 < x4

可以稍微化简一下：

x1 < x4 && x3 < x2

就算是调换个位置：

           x1             x2
           |--------------|
 |--------------|
 x3             x4
还是能得到同样的关系：

x3 < x2 && x1 < x4

好，下面我们进军2D的世界，实际上2D的重叠就是两个方向都同时满足1D的重叠条件即可。
由于题目中说明了两个矩形的重合面积为正才算overlap，就是说挨着边的不算重叠，那么
两个矩形重叠主要有这四种情况：

1）两个矩形在矩形1的右上角重叠：

           ____________________x4,y4
          |                   |
   _______|______x2,y2        |
  |       |______|____________|
  |      x3,y3   |
  |______________|
 x1,y1

满足的条件为：x1 < x4 && x3 < x2 && y1 < y4 && y3 < y2

 

2）两个矩形在矩形1的左上角重叠：

   ___________________  x4,y4
  |                   |
  |            _______|____________x2,y2
  |___________|_______|           |
x3,y3         |                   | 
              |___________________|
            x1,y1

满足的条件为：x3 < x2 && x1 < x4 && y1 < y4 && y3 < y2

 

3）两个矩形在矩形1的左下角重叠：

           ____________________x2,y2
          |                   |
   _______|______x4,y4        |
  |       |______|____________|
  |      x1,y1   |
  |______________|
 x3,y3

满足的条件为：x3 < x2 && x1 < x4 && y3 < y2 && y1 < y4

 

4）两个矩形在矩形1的右下角重叠：

   ___________________  x2,y2
  |                   |
  |            _______|____________x4,y4
  |___________|_______|           |
x1,y1         |                   | 
              |___________________|
            x3,y3

满足的条件为：x1 < x4 && x3 < x2 && y3 < y2 && y1 < y4

仔细观察可以发现，上面四种情况的满足条件其实都是相同的，只不过顺序调换了位置，
所以我们只要一行就可以解决问题了，碉堡了。。。 
*/



class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        
        if((rec1[0]<rec2[2] && rec1[2]>rec2[0]) && (rec1[1]<rec2[3] && rec1[3]>rec2[1])){
            return true;
        }
       
        return false;
    }
}