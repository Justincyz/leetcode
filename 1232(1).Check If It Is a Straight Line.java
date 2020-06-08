/*1232. Check If It Is a Straight Line
Easy
链接: https://leetcode.com/problems/check-if-it-is-a-straight-line/

You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.

Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
Output: true


Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
Output: false

Constraints:
2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates contains no duplicate point.
*/



/*解题思路
一道平平无奇的题目，我们只要用coordinates[1]到coordinates[N-1]的所有点对coordinates[0]这个点计算一下斜率就可以了。
其实我这个写的不太好，我们应该计算两个点的交叉乘积是否相等，而不是两个点的斜率是否相等。比如说(x1,y1)和(x2,y2)。我们应该计算x1y2是不是等于x2y1。这样可以避免被除数为0的情况，x/0这样的情况会报错。
*/

class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        if(coordinates.length <=1) return true;
        
        float prev = (float)(coordinates[1][1]-coordinates[0][1])/(float)(coordinates[1][0]-coordinates[0][0]);
        for(int i=2; i< coordinates.length; i++){
            float cur = (float)(coordinates[i][1]-coordinates[0][1])/(float)(coordinates[i][0]-coordinates[0][0]);
            if(cur != prev) return false;
            prev = cur;
        }
        return true;
    }
}