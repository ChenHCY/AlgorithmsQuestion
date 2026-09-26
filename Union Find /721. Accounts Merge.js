/* 
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]

Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

Example 2:
Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]

Constraints:
1 <= accounts.length <= 1000
2 <= accounts[i].length <= 10
1 <= accounts[i][j].length <= 30
accounts[i][0] consists of English letters.
accounts[i][j] (for j > 0) is a valid email.
 */

/**
 * @param {string[][]} accounts
 * @return {string[][]}
 */
// find(email): 找到这个email 的 源头
// union(emailA, emailB): 检查这两个email 是否属于同一个group, 然后merge
var accountsMerge = function(accounts) {
    const parent = {};

    const find = (email) => {
        if(parent[email] !== email){
            parent[email] = find(parent[email]);
        }
        return parent[email]; //递归找到当前email 的源头
    }

    const union = (emailA, emailB) => {
        const rootA = find(emailA);
        const rootB = find(emailB);

        if(rootA === rootB){
            return false; //已经在同一个group了
        }
        parent[rootA] = rootB;
        return true;
    }

    const emailWithAccount = {};
    // traverse all the accounts email
    for(const [name, ...emails] of accounts){
        for(const email of emails){
            // 因为我们需要对于每一个email 创建一个group
            // 但这题的email长度是动态的，所以要在这里创建空间
            if(!parent[email]){
                parent[email] = email; //default value 指向自己
            }
            emailWithAccount[email] = name;
            union(email, emails[0]); //可以看成当前所有email 和 第一个email的connect
        }
    }
    // console.log(emailWithAccount);
    // console.log(parent);

    const res = {}; // 合并，最后的结果
    for(const email of Object.keys(parent)){
        const parentEmail = find(email);

        // 根据每个email 自己的源头email，来合并
        if(parentEmail in res){
            res[parentEmail].push(email); //把当前email 加入到对应的group 位置
        } else {
            res[parentEmail] = [email]; //在结果中，对于当前email, 创建空间
        }
    }

    // console.log(res)

    return Object.entries(res).map(([rootEmail, emailList]) => [emailWithAccount[rootEmail], ...emailList.sort()]);
};
