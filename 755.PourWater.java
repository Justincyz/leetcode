/*755. Pour Water
We are given an elevation map, heights[i] representing the height of the 
terrain at that index. The width at each index is 1. After V units of water fall 
at index K, how much water is at each index?

Water first drops at index K and rests on top of the highest terrain or water at 
that index. Then, it flows according to the following rules:

If the droplet would eventually fall by moving left, then move left.
Otherwise, if the droplet would eventually fall by moving right, then move right.
Otherwise, rise at it's current position.
Here, "eventually fall" means that the droplet will eventually be at a lower level 
if it moves in that direction. Also, "level" means the height of the terrain plus 
any water in that column.
We can assume there's infinitely high terrain on the two sides out of bounds of the
 array. Also, there could not be partial water being spread out evenly on more than 
 1 grid block - each unit of water has to be in exactly one block.

Example 1:
Input: heights = [2,1,1,2,1,2,2], V = 4, K = 3
Output: [2,2,2,3,2,2,2]
Explanation:
#       #
#       #
##  # ###
#########
 0123456    <- index

The first drop of water lands at index K = 3:

#       #
#   w   #
##  # ###
#########
 0123456    

When moving left or right, the water can only move to the same level or a lower level.
(By level, we mean the total height of the terrain plus any water in that column.)
Since moving left will eventually make it fall, it moves left.
(A droplet "made to fall" means go to a lower height than it was at previously.)

#       #
#       #
## w# ###
#########
 0123456    

Since moving left will not make it fall, it stays in place.  The next droplet falls:

#       #
#   w   #
## w# ###
#########
 0123456  

Since the new droplet moving left will eventually make it fall, it moves left.
Notice that the droplet still preferred to move left,
even though it could move right (and moving right makes it fall quicker.)

#       #
#  w    #
## w# ###
#########
 0123456  

#       #
#       #
##ww# ###
#########
 0123456  

After those steps, the third droplet falls.
Since moving left would not eventually make it fall, it tries to move right.
Since moving right would eventually make it fall, it moves right.

#       #
#   w   #
##ww# ###
#########
 0123456  

#       #
#       #
##ww#w###
#########
 0123456  

Finally, the fourth droplet falls.
Since moving left would not eventually make it fall, it tries to move right.
Since moving right would not eventually make it fall, it stays in place:

#       #
#   w   #
##ww#w###
#########
 0123456  

The final answer is [2,2,2,3,2,2,2]:

    #    
 ####### 
 ####### 
 0123456 
Example 2:
Input: heights = [1,2,3,4], V = 2, K = 2
Output: [2,3,3,4]
Explanation:
The last droplet settles at index 1, since moving further left would not cause it to eventually fall to a lower height.
Example 3:
Input: heights = [3,1,3], V = 5, K = 1
Output: [4,4,4]
*/

/*解题思路
很粗暴的解题思路
第一个是完全的粗暴方法。第二个比较巧妙也是最优解。雨滴的行进方向都是这样的，
1. 先向左边找到一个局部最低点，
解释: 会出现两个情况，第一是可以找到一个连续下降的Local minimal, 那么我们就选择这个点。如果左边的
高度都跟K位一样，那么左边就无法找到了。此时就往右边走。
2. 然后往右边看是否可以找到一个比当前位置低的。
解释: 如果local minimal可以在左边找到，那么我们要找到距离K点最近的那个minimal。 如果此时local 
minimal在左边无法找到，也就是说左边的高度都起码和K点的齐平了。那么我们就往右边走。范围是小于array.size()
3. 再往左找到位置比当前位置低的。
解释。如果进入到了最后这个while loop, 那么说明我们就在K的右边了。此时我们要往左走。如果右边还存在
比K点小的local minimal，那么我们要找到最左边的那一个。如果没有的话，说明右边的值和K的高度齐平。那么
我们就在K点的高度上增加1.

*/
//改进前
class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        for(int i=0; i<V; i++){
            int l = K, r= K;
            while(l>0 && heights[l-1]<=heights[l]) l--;
            while(l< heights.length-1&& heights[l]==heights[l+1]) l++;
            while(r<heights.length-1 && heights[r+1]<=heights[r]) r++;
            while(r>0&&heights[r-1] == heights[r]) r--;
            if(heights[l] < heights[K]) heights[l]++;
            else if(heights[r] < heights[K]) heights[r]++;
            else heights[K]++;
        }
        return heights;
    }
}
//改进后
class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        for(int i = 0; i < V; i++) {
            int cur = K;
            // Move left
            while(cur > 0 && heights[cur] >= heights[cur - 1]) {
                cur--;
            }
            // Move right
            while(cur < heights.length - 1 && heights[cur] >= heights[cur + 1]) {
                cur++;
            }
            // Move left to K
            while(cur > K && heights[cur] >= heights[cur - 1]) {
                cur--;
            }
            heights[cur]++;
        }

        return heights;
    }
}