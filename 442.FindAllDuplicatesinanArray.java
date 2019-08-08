/*442. Find All Duplicates in an Array
Medium: 
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some 
elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]

*/

/*解题思路
题目要求的是找到数组中重复的数字(最多重复两次)。这道题目给出的数字都是在1 ≤ a[i] ≤ n之间的。不然的话
必须要借助额外的空间(例如hashset)才能找到重复的数字了。
首先我想到的是，每一次运行到新的一位时，将当前位的数字移动到它该在的地方。举个例子
[4,3,1,2,3],当我们i=0指向4的时候，应该把4和2进行交换。变成[2,3,1,4,3]。
此时i=0指向的数字还是没在正确的位置上，于是我们继续交换2，3变成[3,2,1,4,3]
此时i=0指向的数字还是没在正确的位置上，于是我们继续交换3，1变成[1,2,3,4,3]
现在1已经在第一位了。于是我们继续往下交换。发现直到最后一个3之前所有的数字都在正确
的位置上，那么当我们想交换3的时候，发现第三位(nums[2])已经被放上了正确的数字。那么就
继续，不用管这一位了。
最后我们还需要遍历一次数组，如果有数字不在属于自己应该在的位置上，那就加入到duplication
List里面。

注意，这种方法踩了一个坑，就是当我们发现正确位置上已经有正确元素的时候，我们不可以直接
将当前元素加入到duplication中，否则会出现重复计算的情况。因为这个重复的元素因为无法被
放在正确的位置上，而他自己又占用了别人的正确位置。当这个元素后面的元素和这个重复元素进行
交换时，在之后又遇到这个元素的时候会又计算一遍重复
*/

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>(); 
        for(int i=1; i<=nums.length; i++){
            while(nums[i-1] !=i){
                int store = nums[i-1];
                if(store == nums[store-1]) break; 
                nums[i-1] = nums[store-1];
                nums[store-1] = store;     
            }
        }
       
        for(int i=1; i<=nums.length; i++){
            if(nums[i-1] != i) list.add(nums[i-1]);
        
        }
        return list;
    }
}



/*解题思路 2
由于之前做过一道类似的题目Find the Duplicate Number，来看一种正负替换的方法，
这类问题的核心是就是找nums[i]和nums[nums[i] - 1]的关系，我们的做法是，对于每
个nums[i]，我们将其对应的nums[nums[i] - 1]取相反数，如果其已经是负数了，说明
之前存在过，我们将其加入结果res中即可

*/

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        
        List<Integer> list = new ArrayList<>(); 
        for(int i=1; i<=nums.length; i++){
            int idx = Math.abs(nums[i-1]);//只是取反，并没有改变或者交换位置
            if(nums[idx-1] <0) list.add(idx);
            nums[idx-1] = -nums[idx-1];
        }
       
        return list;
    }
}





