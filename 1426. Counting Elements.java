/*1426 Counting Elements
链接：
Easy:

Given an integer array arr, count element x such that x + 1 is also in arr.

If there're duplicates in arr, count them seperately.

Example 1:
Input: arr = [1,2,3]
Output: 2
Explanation: 1 and 2 are counted cause 2 and 3 are in arr.

Example 2:
Input: arr = [1,1,3,3,5,5,7,7]
Output: 0
Explanation: No numbers are counted, cause there's no 2, 4, 6, or 8 in arr.

Example 3:
Input: arr = [1,3,2,3,5,0]
Output: 3
Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.

Example 4:
Input: arr = [1,1,2,2]
Output: 2
Explanation: Two 1s are counted cause 2 is in arr.
 
Constraints:
1 <= arr.length <= 1000
0 <= arr[i] <= 1000


*/

/*解题思路
这道题给我们一个数组，让我们按照以下方式统计数组中元素总个数。 对于元素n来说，如果
数组当中有n+1这一位数的话，我们就将结果加一。注意，我们是以前一位的个数来算的。
比如说[1,2,2,2], 结果是1，因为对于1来说，有存在1+1=2这一位，但是我们不管重复几次。

*/

//题目中元素规定最大元素为1000，所以我们只需要创造一个长度为1001的数组就可以了
class Solution {
    public int countElements(int[] arr) {
        int[] count = new int[1001];
        int res =0;
        
        for(int num: arr){
            count[num]++;
        }
        
        for(int i=0; i<=1000; i++){
            if(count[i] !=0  && i < 1000 && count[i+1] !=0){
                res+=count[i];
            }
        }
        return res;
    }
}

//直接用map也很快
class Solution {
    public int countElements(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>(); 
        int res =0;
        
        for(int num: arr){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        for(Integer key: map.keySet()){
            if(map.containsKey(key+1)){
                res+=map.get(key);
            }
        }
        return res;
    }
}