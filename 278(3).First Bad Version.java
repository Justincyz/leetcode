/*278. First Bad Version
链接: https://leetcode.com/problems/first-bad-version/
Easy

You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:

Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version. 
*/



/*解题思路
这道题目我在面试facebook的时候被考到过。印象很深刻。题目让我们找到一组产品当中第一个坏掉的产品。这个产品后面的所有产品都是坏掉的。我们就是用简单的二分法来做就可以了。注意这里我们需要调用一个API来检测某一个产品是否有问题。有问题的话会返回true, 没问题的话返回false;

SPACE: O(1)
TIME: O(NLOGN)

*/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 0, right = n;
        while(left < right){
            int mid = left+(right-left)/2;
            if(isBadVersion(mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }
}