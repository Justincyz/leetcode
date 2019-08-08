/*373. Find K Pairs with Smallest Sums
Medium

You are given two integer arrays nums1 and nums2 sorted in ascending order and 
an integer k.

Define a pair (u,v) which consists of one element from the first array and one 
element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]] 
Explanation: The first 3 pairs are returned from the sequence: 
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence: 
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
*/

/*解题思路
首先常规的思路说一下。就是nums1数组和nums2数组里面每个取K个元素(如果数组长度
小于K就取数组长度)。因为最多取K个就够了，即使一个array的数字比另一个都小，也
可以都取到。然后可以用自定义得arrays.sort()或者用priorityqueue()都可以。用
priorityqueue()的时候有个小坑要注意，不能用for(int i: pq)来取pq中的元素，
只能每一次remove()否则的话出来的数字不是按照大小排列的。

来看一个不太常规的思路。主数组我们用的是priorityqueue。首先，我们把nums2[]中的元素
和所有的nums1[]中的元素组队，然后放进pq中按照和的大小排序。但是同时，我们放进pq
的list<>中，还要包含一下nums2[] array中放进元素的位置。当然，第一次我们只放了
nums2[]位置在0的元素用来组队。那么接下来就比较巧妙了。此时我们只需要最多遍历K次(或者)
遍历到两个数组没有更多的新组合为止)。原理如下:
首先不用说，最小的一定是两个头部数字的组合。那么在已经考虑了num2[]中最小的和所有
num1[]中的组合之后，下一位应该放进pq的应该就是，刚刚被找到最小对，属于nums2中
那个元素的下一位。因为其余所有的可行组合都比这个组合大。同时在num1[]中，我们还是
要挑选刚刚被找到最小对中，属于nums1中的那个元素。因为比nums1还要小的元素在之前轮
已经被选走了或者还在pq中，所以不可能存在比当前组合更小的组合了。所以每一次我们都是
找余下组合中最小的一对。 直到找到K个元素为止。

*/

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums1.length == 0 || nums2.length == 0) return res;
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a, b) -> a.get(0)+a.get(1) - b.get(0)-b.get(1));

        for(int i=0; i< nums1.length && i<k; i++){
            List<Integer> temp = new ArrayList<>();
            temp.add(nums1[i]);
            temp.add(nums2[0]); 
            temp.add(0);      
            pq.add(temp);
        }
        
        
        while(k-- >0 && !pq.isEmpty()){
            List<Integer> temp =pq.remove();  
            int pos = temp.remove(2);
            res.add(temp);
            if(pos == (nums2.length -1)) continue;//如果nums2已经到头了，只能在余下的pq中挑选了
            List<Integer> list = new ArrayList<>();
            list.add(temp.get(0)); list.add(nums2[pos+1]); list.add(pos+1); 
            pq.add(list);
        }
    
        return res;
    }
}