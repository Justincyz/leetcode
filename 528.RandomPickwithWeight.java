/*528. Random Pick with Weight
Medium: Array
Given an array w of positive integers, where w[i] describes the weight of index i, 
write a function pickIndex which randomly picks an index in proportion to its weight.

Example 1:
Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]

Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
*/

/*解题思路
首先需要解释一下这个题目的意思。对应一开头call solution这个位置会输入一个array。每一个数字代表
自己的权重。比如说[1,3], 那么说明第一个位置上的权重是1，第二个是3.所以取值的时候，第一个被取到的
概率是25%, 第二个是 75%。一般语言自带的random只能取随机的数字。那么如何让位置0被取到的是25%而
位置1被取到的是75%呢。第一个想法是，创造一个arraylist, 按照权重往里面加值。比如说对于[1,3],那就往
list加入1个0和三个1.这个解过了55/57个test case，可是最后超时了。说明思路没问题，那么在这个基础上
可以迭代出第二个解。那就是用presum的办法算出来一个区间范围，然后权重相当于是一个区间。还是一样的例子
我们可以算出来preSum[]是[1,4]。 那么我们随机一个integer，然后mod total sum。比如说 11%4 = 3。
那么3就落到了1-4的区间内。比如说另外的一个数字 4, 4%4 = 0.那就落到了0-1的区间内。然后我们利用
binary search的办法，可以快速找到随机值落到的区间。

*/

import java.util.Random;
class Solution {
    int[] preSum;
    int total;
    Random rand=new Random();

    public Solution(int[] w) {
        preSum = new int[w.length];
        int sum = 0;
        for(int i=0; i<w.length; i++){
            preSum[i] = sum+w[i];
            sum+=w[i];
        }
        total = sum;
    }
    
    public int pickIndex() {
        int r = rand.nextInt(Integer.MAX_VALUE)%total;
        int start = 0, end = preSum.length-1;
        while(start < end){
            int mid = start+ (end-start)/2;//一定要记住，这种做法是向下取整的，所以总是start = mid+1
            if(preSum[mid] > r){
                end = mid;
            }else{
                start = mid+1;
            }
        }
        return start;
    }
}