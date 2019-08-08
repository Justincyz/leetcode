/*347. Top K Frequent Elements
Medium: 

Given a non-empty array of integers, return the k most frequent elements.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1   
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n 
is the array's size.
*/

/*解题思路
首先讲一个O(n)的算法，这个算法是基于Bucket sort, 但是每一个bucket放的是频率。而不是数字本身，
而频率一定不会超过input 的长度。所以bucket 的space只需要O(n) 的空间就可以了。我们一开始用一个
map记录每个数字出现的频率，然后根据频率放入特定的Bucket中。注意，bucket的类型是 List<Integer>[]
因为有可能同一个频率的数字出现多次。
其次就是常规的O(nlogn) 的解法了，还是先用map记录每个数字出现的频率，然后扔到PriorityQueue中，找出
频率最高的k个。
*/


class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int num: nums){
            if(!map.containsKey(num)) map.put(num, 0);
            map.put(num, map.get(num)+1);
        } 

         List<Integer>[] freq = new List[nums.length+1];

        for(int key: map.keySet()){
            int pos = map.get(key);
            if(freq[pos]==null) freq[pos] = new ArrayList<>();
            freq[pos].add(key);
        }

        for(int i= freq.length-1; i>=0; i--){
            if(freq[i]!=null){
                List<Integer> temp= freq[i];
                for(int n: temp){
                    if(k == 0) break;
                    list.add(n);
                    k--;
                    
                }
            }
        }       
        return list;
    }
}

//这个速度就慢很多了，但是也可以beats 45%
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i=0; i<nums.length;i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        PriorityQueue<int[]> pQueue = new PriorityQueue<>((a,b)->(b[1]-a[1])); 
        for(Map.Entry<Integer, Integer> entry :  map.entrySet()){
            pQueue.add(new int[]{entry.getKey(), entry.getValue()});
        }
        
        for(int i=1;i<=k;i++){
            int[] temp= pQueue.poll();
            result.add(temp[0]);
        }
        
        
        return result;
    }
}

