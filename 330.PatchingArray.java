class Solution {
    public int minPatches(int[] nums, int n) {
        long miss =1; int result = 0, index = 0;
        while(miss <=n){
            if(index < nums.length && nums[index]<=miss){
                miss+= nums[index++];
            }else{
                miss+=miss;
                result++;
            }
        }
        return result;
    }
}

/*
这道题算法只有O(n), 解题思路比较巧妙。
首先解释参数。 miss代表的是在当前index的范围内，无法reach的最小的那个数。
index代表当前指针指向的数组的哪一位。result代表需要添加的数字的个数。
举个例子, [2,5] n=9
miss:  1
index: 0
result:0
代表了第一个假定的无法reach的数字是1，此时 nums[index] > miss.也就是说，我们无法用数组的第一位数，2(因为数组是升序的)，
来组合成为miss的数字，1. 所以到了else 语句，miss = 2, result = 1.意思是，在我们把无法reach的数字1加入到我们的数组中，
此时在不考虑数组的状态下，就可以reach 1了.然后下一个miss的数字是2.
miss: 2
index: 0
result: 1
此时 nums[index] = nums[0] = 2, 2<=miss。miss = miss+2 = 4.
意思是，下一位的miss可以被数组中的index所指的数字加上原来有的数字reach到了，也即是说在添加了数组中的2时，miss指代的原来不可reach的2
现在可以被reach到了。在把2放进了之后，下一个miss的值是4。index指针加一指向下一位  
miss: 4
index: 1
result: 1
重复一下，在miss之前的所有的值都可以通过某些组合达到。index指向5时，此时miss是小于5的。也就是说在miss和5之间
又一次出现了空隙。所以我们要再补一个4.因为4之前的所有n都可以被reach，所以，当我们加上4之后，4+原来可以被
reach到的1,2,3可以填补 5,6,7的位置，所以下一位 miss的就是8。
miss: 4
index = 5
result = 1
运行到这一步时 miss = miss+5, miss = 9. 此时最后一位9还不能被reach, 所有我们要再进行一轮。result++
*/