/*475. Heaters
链接：https://leetcode.com/problems/heaters/
Easy: 
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.
 

Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 
Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
*/

/*解题思路
这道题目告诉我们有一组houses的坐标和一组heaters的坐标，跟例子中不一样，
坐标不一定是连续的。问，所有heater的最小半径需要多大才能覆盖周围的所有house
的位置。除了以上例子，[1,2,3,5,15],[2,30].Heater的最小半径要达到13，就可以
覆盖所有houses的坐标了，在这里我们就是使用第一个坐标在2上的heater,就可以
覆盖所有的范围了。
首先把heaters Sort好，然后遍历houses，把每一个房子找到heaters中排序好的
位置。然后查看这个房子到达左右两个heater的距离，左右两边选取距离小的。然后
在全局范围内选择一个最大的半径。就是结果了。

这个Arrays.binarySearch(heaters, house)也是一个知识点，如果house
在heaters中有对应的位置，那么就直接返回位置。否则会返回一个负数，这个
负数加一再取相反数就是在heaters中对应的位置。

*/
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int res = Integer.MIN_VALUE;
        for(int house: houses){
            int pos = Arrays.binarySearch(heaters, house);
            if(pos < 0) pos = -(pos+1);
            
            int left = pos-1 <0? Integer.MAX_VALUE: house - heaters[pos-1];
            int right = pos >= heaters.length? Integer.MAX_VALUE: heaters[pos]-house;
            res = Math.max(res, Math.min(left, right));
        }
        return res;
    }
}