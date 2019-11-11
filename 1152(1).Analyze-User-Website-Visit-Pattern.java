/*1152. Analyze User Website Visit Pattern
链接：https://leetcode.com/problems/analyze-user-website-visit-pattern/
Medium: 
We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].

A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)

Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.

Example 1:
Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation: 
The tuples in this example are:
["joe", 1, "home"]
["joe", 2, "about"]
["joe", 3, "career"]
["james", 4, "home"]
["james", 5, "cart"]
["james", 6, "maps"]
["james", 7, "home"]
["mary", 8, "home"]
["mary", 9, "about"]
["mary", 10, "career"]
The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 

Note:

3 <= N = username.length = timestamp.length = website.length <= 50
1 <= username[i].length <= 10
0 <= timestamp[i] <= 10^9
1 <= website[i].length <= 10
Both username[i] and website[i] contain only lowercase characters.
It is guaranteed that there is at least one user who visited at least 3 websites.
No user visits two websites at the same time.
*/

/*解题思路
题目的意思是，我们有几个userId, 每个id对应的人会在不同时间段看不同的网页。
时间递增(subsequence)的三个网页算是一组，如果一个人浏览的网页不足三个则不考虑。那么问这种三个网页
出现频率最高的组合是什么？

这道题目沙雕到我都不想写注释。但是讽刺的是在写这道题的前一天刚被Lumi Lab
一个小厂考到了，那个面试官出的问题简化了一些，是连续的三个网页，(substring not subsequence)。这道题目太绕了。

*/
class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Node1> input = new ArrayList<>();
        for(int i=0; i< username.length; i++){
            Node1 t = new Node1(username[i], timestamp[i], website[i]);
            input.add(t);
        }
        Collections.sort(input, new Comparator<Node1>(){
            public int compare(Node1 a, Node1 b){
                return a.timestamp - b.timestamp;
            }
        });
        
        Map<String, List<String>> userId = new HashMap<>();
        for(int i=0; i< input.size(); i++){
            Node1 t = input.get(i);
            if(!userId.containsKey(t.username)) userId.put(t.username, new ArrayList());
            userId.get(t.username).add(t.website);
        }
        
        Map<String, Integer> freq = new HashMap<>();
        for(Map.Entry<String, List<String>> entry: userId.entrySet()){
            List<String> list = entry.getValue();
            if(list.size() <3) continue;
            Set<String> set = new HashSet<>();
            //这里要用hashset来记录，因为重复的不算，比如连续的两个时间段
            //停留在同一个页面这样子吧
            permutation(set, list, 0);
            for(String w : set){
                freq.put(w, freq.getOrDefault(w, 0)+1);
            }

        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node a, Node b){
                if(a.freq != b.freq)
                    return b.freq - a.freq; 
                else
                    return a.web.compareTo(b.web);
            }
        });
        
         for(Map.Entry<String, Integer> entry: freq.entrySet()){
             Node a = new Node(entry.getValue(), entry.getKey());
             pq.add(a);
         }
        String l = pq.poll().web;
        
        List<String> res = new ArrayList<>();
        for(String s : l.split("#")){
            res.add(s);
        }
      
        return res;
    }
    
    public void permutation(Set<String> set, List<String> list, int idx){
        if(idx > list.size()-2) return;
        
        for(int i=idx+1; i < list.size();i++){
            for(int j= i+1; j < list.size();j++){
                String web = list.get(idx)+"#"+list.get(i)+"#"+list.get(j);
                set.add(web);
            }
        }
        permutation(set, list, idx+1);
    }
}

class Node{
    int freq;
    String web;
    
    public Node(int freq, String web){
        this.freq = freq;
        this.web = web;
    }
}

class Node1{
    String username;
    int timestamp;
    String website;
    public Node1(String u, int t, String w){
        username = u;
        timestamp = t;
        website = w;
    }
}