/*849. Maximize Distance to Closest Person
链接：https://leetcode.com/problems/maximize-distance-to-closest-person/
Easy:
In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty. 
There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 
Return that maximum distance to closest person.


在一排座位（ seats）中，1 代表有人坐在座位上，0 代表座位上是空的。
至少有一个空座位，且至少有一人坐在座位上。
亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
返回他到离他最近的人的最大距离。

Example 1
Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.


Example 2:
Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.

Note:
- 1 <= seats.length <= 20000
- seats contains only 0s or 1s, at least one 0, and at least one 1.
*/

/*解题思路  Array，Two Pointer
这道题目让我们找到数组当中的为0的位置，这个位置距离两边的1是最大的。比如说例子1当中，我们坐在array[2]这个位置上的时候，两边有人坐的位置距离这个位置最少也有2，所以结果结果就是2。第二个例子是一个corner case, 就是如果数组的两端没有人坐的的，那么这个位置的周围最多只会出现一个人，所以只要计算一边的情况就可以了。那我们实际上用两个pointer记录每两个1之间的空位有多少就好了。然后每一次更新在maxDist里面。

这道题目的follow up是 LeetCode 855. Exam Room,说的是多个人

我们对于每一个全部是0的空当来说，用left记录这个空当左边的1出现的位置，right记录右边1出现的位置。首先需要将left初始化为-1，因为我们要考虑到左边没有人的这个corner case。当左右都为1的时候，记住我们要计算的这个空当中间距离左右两边的较小的位置。而不是这个坐标的相对位置，所以我们是用right减去left再除以2。无论中间的距离是奇数还是偶数，整数除二是向下取整的所以没关系。当我们遍历完整个数组的时候，不要忘记还有这个人可能会坐在最末尾的这一个corner case。所以要用数组的长度减去最后一次出现1的位置再减一和maxDist进行一次比较。
TIME: O(N)
SPACE: O(1)

*/


class Solution {
    public int maxDistToClosest(int[] seats) {
        int left = -1, right = 0, maxDist = 0;
        while(right < seats.length){
            if(seats[right] == 1){
                if(left == -1){                   
                    maxDist = Math.max(maxDist, right);
                }else if(seats[left] == 1){
                    maxDist = Math.max(maxDist, (right-left)/2);
                }
                left = right;
            }
            right++;
        }
        
        maxDist = Math.max(maxDist, seats.length-left-1);
        return maxDist;
    }
}