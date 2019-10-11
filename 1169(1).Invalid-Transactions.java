/*1169. Invalid Transactions
链接：https://leetcode.com/problems/invalid-transactions/
Medium: 
A transaction is possibly invalid if:

the amount exceeds $1000, or;
if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
Each transaction string transactions[i] consists of comma separated values representing the name, time (in minutes), amount, and city of the transaction.

Given a list of transactions, return a list of transactions that are possibly invalid.  You may return the answer in any order.

 

Example 1:

Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
Example 2:

Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]
Example 3:

Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]
*/

/*解题思路
没想到最佳的做法是O(N^2)的，对于每个人的每一个transaction, 都和其在60分钟内的
transaction尝试一遍就好

*/

class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        List<String> res = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        Map<String, List<Node>> map = new HashMap<>();   
        
        for(String tran: transactions){
            String[] info = tran.split(",");
            Node n = new Node(info[0], info[1], info[2], info[3], tran);
            if(!map.containsKey(info[0])) map.put(info[0], new ArrayList());
            map.get(info[0]).add(n);
        }
        
        for(Map.Entry<String, List<Node>> entry: map.entrySet()){
            List<Node> l = new ArrayList<>(entry.getValue());
            Collections.sort(l, new Comparator<Node>() {
                public int compare(Node a, Node b) { 
                    return a.mins - b.mins; 
                }
            });
            
            for(int i=0;i<l.size();i++) {
                for(int j=i+1;j<l.size()&&l.get(j).mins<=l.get(i).mins+60;j++) {
                    if(!l.get(i).city.equals(l.get(j).city)) {
                        set.add(l.get(i).sentence);
                        set.add(l.get(j).sentence);
                    } 
                }
                if(l.get(i).amount>1000) set.add(l.get(i).sentence);
            }
        }
        
        res.addAll(set);
        return res;
    }
}

class Node{
    String name;
    int mins;
    int amount;
    String city;
    String sentence;
    public Node(String name, String mins, String amount, String city, String sentence){
        this.name = name;
        this.mins = Integer.valueOf(mins);
        this.amount = Integer.valueOf(amount);
        this.city = city;
        this.sentence = sentence;
    }
}

/*
时间输入可能是乱序，如果连续的一段时间之内，比如说 10 - 70分钟内alice的所有账户
出现的城市都是不一样的，那么当我们把 10-70分钟内的标记为invalid之后，71分钟的
transaction开始就与前面无关了。其次，我们处理的时候，先看时间和城市是否有效，
再看金额是否有效，比如说alice在A城市的第20分钟取了100元，在B城市的第22分钟取了
1100元，虽然第二笔是无效的取款，但是我们也不可以说第一笔是有效的，即使第二笔并没有生效。
*/