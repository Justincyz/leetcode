/*143. Sort Colors II

链接：https://www.lintcode.com/problem/sort-colors-ii/description
Medium: 
给定一个有n个对象（包括k种不同的颜色，并按照1到k进行编号）的数组，将对象进行分类使相同颜色的对象相邻，并按照1,2，...k的顺序进行排序。

Example
样例1

输入: 
[3,2,2,1,4] 
4
输出: 
[1,2,2,3,4]
样例2

输入: 
[2,1,1,2,2] 
2
输出: 
[1,1,2,2,2]
Challenge
一个相当直接的解决方案是使用计数排序扫描2遍的算法。这样你会花费O(k)的额外空间。你否能在不使用额外空间的情况下完成？
*/

/*解题思路
首先这道题目最简单的解法就是开一个长度为K的数组，然后利用counting sort
的办法计算每一个值出现的频率。最后按顺序将新开数组内的元素输出到结果中。
Space： O(K), Time: O(N)


这道题目主要是问我们能不能在不使用额外空间的前提下完成。
算法时间复杂度要求到O(nlogk)，k为颜色个数
这种算法的思想类似与quickSort与mergeSort结合
quickSort的思想在于partition进行分割
mergeSort的思想在于直接取中间（这里表现为取中间大小的数），分为左右两个相等长度的部分，
在这里是自上而下的sort, 相当于是merge sort反过来
区别在于partition的判定条件变为了中间大小的元素而不是中间位置的元素


*/

public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }
        private void rainbowSort(int[] colors, int start, int end,int colorFrom,int colorTo) {
        if (colorFrom == colorTo) {
            return;
        }
        if (start == end) {
            return;
        }
        //区别在于partition的判定条件变为了中间大小的元素而不是中间位置的元素
        int colorMid = (colorFrom + colorTo) / 2;
        int left = start, right = end;
        while (left <= right) {
            //因此等号的取值可以只取一边也不会有影响
            while (left <= right && colors[left] <= colorMid) {
                left++;
            }
            while (left <= right && colors[right] > colorMid) {
                right--;
            }
            if (left <= right) {
                int temp = colors[left];
                colors[left] = colors[right];
                colors[right] = temp;
                left++;
                right--;
            }
        }

        rainbowSort(colors, start, right, colorFrom, colorMid);
        rainbowSort(colors, left, end, colorMid + 1, colorTo);
    }

}