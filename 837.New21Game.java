/*837. New 21 Game
Medium: dp
Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K 
points.  During each draw, she gains an integer number of points randomly 
from the range [1, W], where W is an integer.  Each draw is independent 
and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the 
probability that she has N or less points?

Example 1:
Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.

Example 2:
Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.

Example 3:
Input: N = 21, K = 17, W = 10
Output: 0.73278
Note:   
*/

/*解题思路
这道题目给定了三个数，W指的是每一次可以取得的一个值得范围[1, W]。取得这个值得概率是平均分布的。
K指的是，如果超过了这个值，那么游戏就算结束了。当游戏结束的时候，我们用结束的值和N比较。看
最后的结果是小于N的概率有多少。
我们首先判断(W+K)和N的比较。如果 W+K <=N的话，那么最后取值小于N的概率就是 100%。因为当我们取值到K-1
的时候，此时再随便取一个[1,W]的值，这个值加上K-1的值不管怎么样，结果都大于等于K，游戏此时就结束了。
所以在游戏结束之前可以取到的最大值就是W+K. 如果这个最大值都小于等于N，那么概率就是100%

我们维护一个大小为N的dp[] array。这道题其实是一道条件概率的题目：dp[i]表示可以到达i分数的概率总和。
假设我们的W为10的话，抽到(1 - 10)每张牌的概率都是1/10。那么我们只需要从dp[i-10]开始加就可以了。
所以相当于维持一个size为W的window。比如12这个数，我们可以由抽到2的概率（dp[2]）乘以1/10或者抽到3的概率（dp[3]）
乘以1/10得来...一直到dp[11] * 1/10来取得。

将W推倒到一般情况，那么对于获取某个数字的概率来说:
dp[i] = dp[i-W]*1/W + dp[i-W+1]*1/W + dp[i-W+2]*1/W + ... +dp[i-1] * 1/W，
也就是 dp[i] = (dp[i-W] + dp[i-W+1] + ... + dp[i - 1])/W，这里的dp[1] + dp[2] + ... + dp[i - 1]就是Wsum
，所以dp[i] = Wsum / W。换句话来说Wsum就是通过一次抽排就能到当前分数的概率之和。

现在到我们要计算结果的时候了。也即是计算大于K的时候。对于大于K小于N的这一部分来说都是不可以抽牌的。
所以这一部分Wsum只会递减，而不会再递增了。除此之外我们就可以开始计算这一部分的概率有
多大了，用result来累加这一部分的概率和。最后return result



*/

class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K + W) return 1;

        double result = 0;
        double Wsum = 1; 
        double dp[] = new double[N + 1];
        dp[0] = 1;


        for (int i = 1; i <= N; i++) {
            dp[i] = Wsum / W; 
            // when points is less than K, all previous card could go to current i by only drawing one card
            if (i < K) {
                Wsum += dp[i];
            }
            // when points is equal to or more than K, all probability will be accumulated to results
            else {
                result += dp[i];
            }

            // when i is over than W, then we need to move the window
            if (i - W >= 0) {
                Wsum -= dp[i - W];
            }
        }
        return result;
    }
}


