/*826. Most Profit Assigning Work
链接：https://leetcode.com/problems/most-profit-assigning-work/
Medium: 
We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job. 

Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i]. 

Every worker can be assigned at most one job, but one job can be completed multiple times.

For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.

What is the most profit we can make?

Example 1:
Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
Output: 100 
Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.


Notes:
1 <= difficulty.length = profit.length <= 10000
1 <= worker.length <= 10000
difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
*/

/*解题思路
这道题给了我们一堆工作，每个工作有不同的难度，且对应着不同的利润。现在还有一些工人，每个人能胜任工作的难度不同，题目说了没人最多只能干一项工作，但是每个工作可以被多个人做。现在问我们这些工人能产生的最大利润。题目中给了一个例子，但是这个例子会给我们一些错觉，实际上difficulty数组不一定是有序的，而且可能有相同难度的工作对应不同的利润。还有就是，难度大的工作不一定利润就大，忽略了这些隐藏条件，很容易做错。

我们要保证每一个工人选工作的时候对应的difficulty都是有序的，这样我们可以在某一个范围内取值。所有我们将difficulty和profit利用一个数组打个包。然后按照difficulty从小到大排个序。
然后就会发现这个题目和那种两个队列装箱子匹配的特别像了。我们先给worker也从小到大来排个序。然后我们用两个指针来从两个数组(一个是我们新创建的list还有一个是worker这个数组)的头部开始比较。同时还要用一个prevMax来记录我们遍历到此时的最大利润。如果当前的list当中的difficult[i]的难度超过了worker[j]的承受能力。那么此时对于worker[j]来说，他可以选择的工作的最大利润就是prevMax这个值。然后我们往后移动到下一个worker[j+1]。如果当前worker[j]的承受能力大于当前的difficulty,那么此时我们就更新preMax的值，看看当前的工作创造的价值高还是原来比这个难度小的工作创造的价值高。然后我们选择一个较大的值。因为此时worker[j]还能承受这个工作难度的事情，所以我们移动的是i, 到下一个工作。

我们最后还要检查一下是不是所有的worker都遍历完了，如果没有的话就给他们都赋予preMax所代表的工作。因为此时preMax已经是全局最大值了。

*/


class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int total =0;
        List<int[]> list = new ArrayList<int[]>();
        for(int i=0; i< difficulty.length; i++){
            list.add(new int[]{difficulty[i], profit[i]});
        }
        Collections.sort(list, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        Arrays.sort(worker);
        
        int i=0, j =0, prevMax = 0;
        while(i < list.size()){
            if(list.get(i)[0] <= worker[j]){
                if(list.get(i)[1] > prevMax){
                    prevMax = list.get(i)[1];
                }
                i++;
            }else{
                total+=prevMax;//这里计算的是接近且小于worker difficulty难度，且profit最大的
                j++; 
                if(j == worker.length) break;
            }
        }

        while(j < worker.length){
            total+=prevMax;
            j++;
        }
        
        return total;
    }
}

/*
这是一个有点投机取巧的办法，题目里面告诉我们每个任务的难度值最大不会超过100001.那么我们就建立一个长度为100001的数组。这样我们可以将每一个难度对应的价值映射到对应的数组位置上。然后我们从第一位开始往后遍历，每一次都更新当前位置的数值。使得当前数组位置的值是前面所有出现过的值得最大值。比如说按照上面的例子来看的话:
这是原始的Profit和difficulty的对应关系: 
[0,0,10,0,20,0,30,0,40,0,50]
如果我们从头往后更新每一个位置的最大值，则会是这样
[0,0,10,10,20,20,30,30,40,40,50]
所以我们此时只需要让每一个worker去数组对应的位置上取值就可以了，可以保证是在他的difficulty的范围内取到最大的值。
*/

class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[] array = new int[100001];
        
        for(int i=0; i<difficulty.length; i++){
            int diff = difficulty[i];
            array[diff] = Math.max(array[diff], profit[i]);
        }
        
        for(int i=1; i<100001; i++){
            array[i] = Math.max(array[i], array[i-1]);
        }
        
        int total = 0;
        for(int w: worker){
            total+=array[w];
        }
        return total;
    }

}

