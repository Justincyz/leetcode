/*1124. Longest Well-Performing Interval
Medium: 

We are given hours, a list of the number of hours worked per day for 
a given employee.

A day is considered to be a tiring day if and only if the number of 
hours worked is (strictly) greater than 8.

A well-performing interval is an interval of days for which the number 
of tiring days is strictly larger than the number of non-tiring days.

Return the length of the longest well-performing interval.

Example 1:
Input: hours = [9,9,6,0,6,6,9]
Output: 3
Explanation: The longest well-performing interval is [9,9,6].
*/

/*解题思路
题意：一天工作大于8小时算是工作量饱和。如果在连续若干天内，工作量饱和的天数大于不饱和天数，
则这个天数成为工作良好天数。求最大的工作良好天数。首先想到的是pre-sum加sliding window
的解法。这样时间复杂度是O(N^2)，TLE了。

那么如何将这道题目时间缩短，甚至缩短到O(N)呢？我们重新审视一下这道题目，可以发现，实际上
这道题目可以转化成 -1,1的题目。意思就是，小于等于8的数字我们就当成-1，大于8的数字我们
就当成1。当总和大于0的时候，说明这个subarray是可以被取得的。
然后我们需要考虑当sum小于等于0的时候应该怎么办？注意，这里的sum指的是从第0位开始累加的。
假设此时sum的值是 -7。为了让sub-array总和大于0，那么我们要找的就是pre-sum在之前的累加中，
有没有出现比-7还小的值。首先抛出结论，如果有比-7小的元素，那么当取(-7-1 = -8)时，此时
-8的位置距离-7最远，也就是我们可以取到最长的连续sub-array。证明如下：
首先，对于从0到i位的pre-sum来说，前后绝对值相差为1。map中存入的是第一次出现unique pre-sum
的位置(这样可以保证出现要找的pre-sum值的时候，我们可以取到距离当前位最远的那个target value)。
我们用反证法来证明最优解是 sum[i]-1。

假设最优答案是k，即sum[k] < sum[i] <= 0。
那么在sum[k]肯定是sum[i]-1。
反证法：假设最优答案sum[k] < sum[i]-1，不妨假设位置是L，值sum[L] < sum[i]-1。
由于前缀和的严格连续性，从起始位置到位置L之间肯定存在-1, -2, ..., sum[i]-1, ..., sum[L]的前缀和。
这样，在位置L之前肯定存在另一个位置，值是sum[i]-1，比位置L更优。
假设不成立。
所以，最优答案是sum[i]-1。注意，这个主要是因为当取到的值是为负数。
*/

class Solution {
    public int longestWPI(int[] hours) {
        int sum=0, max =0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        for(int i =0; i<hours.length; i++){
            sum += hours[i] >8 ?1 : -1;
            if(sum >0){
                max = i+1;
            }else{
                if(map.containsKey(sum-1)) max = Math.max(i-map.get(sum-1), max);
            }
            if(!map.containsKey(sum)){
                map.put(sum, i);
            }    
        }
        
        return max;
    }
}


