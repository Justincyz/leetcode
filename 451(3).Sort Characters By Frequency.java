/*451. Sort Characters By Frequency
Medium
链接: https://leetcode.com/problems/sort-characters-by-frequency/submissions/

Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:
Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

*/

/*解题思路
这道题目给我们一个字符串，让我们按照字符串当中每一个字符出现的频率来重新排列字符串。
看例子应该很容易懂了。注意这个里面可能有大小写字母，也可能有数字或者其他元素。

第一种解法:  Time : O(nlogn), Space O(n)
这种解法是最简单的解法，我们首先用Map计算每一个字符及其出现的频率。
然后创建一个Node class打包每一个字符和其出现频率。最后用priorityqueue
来按照元素出现频率来给所有元素排序并且粘贴到一起。
*/

class Solution {
    public String frequencySort(String s) {
        char[] a = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        
        for(char c: a) map.put(c, map.getOrDefault(c, 0)+1);

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> b.freq-a.freq);
        
        
        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            Node n = new Node(entry.getKey(), entry.getValue());
            pq.add(n);
        }

        StringBuffer sb = new StringBuffer();
        
        while(!pq.isEmpty()){
            Node t = pq.poll();
            for(int i=0; i< t.num; i++){
                sb.append(t.c);
            }
        }
        
        return sb.toString();
        
    }
    
    class Node{
        char c;
        int num;
        public Node(char c, int num){
            this.c = c;
            this.num = num;
        }
    }
}

/*
第二种解法。
这是时隔半年后突然写出来的另外一种解法，时间复杂度和空间复杂度都是O(N)，
所以会快很多。

首先利用Map计算每一个字符及其频率这个基本路子没有变。但是我们知道，对于一个
长度为n的字符串，里面元素出现的频率最多也就是n次了。
那么我们可以创建一个长度为n的数组，数组类型为arraylist。 当我们从map当中
获取每一个元素时，我们按照字符的频率，将字符放置到正确的位置上。
比如说: aaabbbccd这样的简单字符串
我们知道频率出现最高的也就是七次而已。所以我们创建一个长度为(n+1) = 8的数组。
将array[3]上放a和b, array[2]上放c, array[1]上放d.这样我们最后只需要从后往前
按照频率由高到低遍历这个数组就可以了。
*/

class Solution {
    public String frequencySort(String s) {
        List<Character>[] bucket = new ArrayList[s.length()+1];
        Map<Character, Integer> frequencyMap = new HashMap<>();
        int len = s.length();
        
        recordFrequencyWithMap(s, frequencyMap);
        putCharactersInCorrectBucket(frequencyMap, bucket);
        return orderedResult(bucket, len);
    }
    
    public void recordFrequencyWithMap(String s,  Map<Character, Integer> frequencyMap){
        for(char c: s.toCharArray()){
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0)+1);
        }
    }
    
    public void putCharactersInCorrectBucket(Map<Character, Integer> frequencyMap,  List<Character>[] bucket){
         for(Map.Entry<Character, Integer> entry : frequencyMap.entrySet()){
            char c = entry.getKey();
            int freq = entry.getValue();
            if(bucket[freq] == null) bucket[freq] = new ArrayList<>();
            bucket[freq].add(c);
        }
    }
    
    public String orderedResult(List<Character>[] bucket, int len){
        StringBuffer sb = new StringBuffer();
        
        for(int i=len; i >=0; i--){
            if(bucket[i] == null) continue;
            
            for(char c: bucket[i]){
                for(int j=0; j< i; j++){
                    sb.append(c);
                }
            }
            
        }
        return sb.toString();
    }
}