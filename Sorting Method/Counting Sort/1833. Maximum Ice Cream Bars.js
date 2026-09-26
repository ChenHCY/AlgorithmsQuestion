/* It is a sweltering summer day, and a boy wants to buy some ice cream bars.
At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of the ith ice cream bar in coins. The boy initially has coins to spend, and he wants to buy as many ice cream bars as possible. 
Note: The boy can buy the ice cream bars in any order.
Return the maximum number of ice cream bars the boy can buy with coins 
You must solve the problem by counting sort.

Example 1:
Input: costs = [1,3,2,4,1], coins = 7
Output: 4
Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.

Example 2:
Input: costs = [10,6,8,7,7,8], coins = 5
Output: 0
Explanation: The boy cannot afford any of the ice cream bars.

Example 3:
Input: costs = [1,6,3,1,2,5], coins = 20
Output: 6
Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.

Constraints:
costs.length == n
1 <= n <= 10^5
1 <= costs[i] <= 10^5
1 <= coins <= 10^8
*/

/**
 * @param {number[]} costs
 * @param {number} coins
 * @return {number}
 */
// because we need to buy the maximum ice cream with limit conis
// so we need to buy the cheapest one
// could using arrays.sort() is quick sort => so time complexity is O(nlogn)
// using couting sort => time complexity is O(n + m) much better
var maxIceCream = function(costs, coins) {
    // 1 <= n <= 10^5
    const freq = new Array(100001).fill(0); // 从小到大排序来统计freq次数
    let count = 0;

    //traverse all the ice cream costs, and sort them
    for(const cost of costs){
        freq[cost]++;
    }

    // 1 <= costs[i] <= 10^5 => i is the cost of ice cream
    //traverse all the costs of ice creams, and count how many ice creams can be bought
    // Greedy algorithm: start the most cheapest one, then we can buy ice creams is the maximum
    for(let i = 1; i <= 100000; i++){
        if(freq[i] === 0){
            continue; //跳过这一次，但继续for循环
        }
        // check based on our coins, how many ice creams we can buy it
        // 取决于现有的硬币能买几个，以及商店里有几个
        const canBuy = Math.min(freq[i], Math.floor(coins / i));

        if(canBuy === 0){
            break; // can not buy anyone, stop traversing
        }

        count += canBuy;
        coins -= canBuy * i; // i 是价钱，canBuy 是买的数量
    }

    return count;
};
