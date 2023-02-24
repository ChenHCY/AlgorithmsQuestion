// Tom is a genius cat, except that he doesn't know how to catch a mouse. Tom has a TikTok gift card worth x dollars , and an Amazon gift card worth y dollars. 
//A TikTok gift card is only able to purchase gifts at the TikTok store. Similarly, a Amazon gift card only allowed to purchase gifts at an Amazon store. 
//Today is Jerry's (the mouse) birthday. Tom wants to buy exactly

/*Example: 
10
8
3
3
1 2 10
1 3 20

Output: 30
*/

//Passed test case: 10/26

#include <bits/stdc++.h>
using namespace std;
string ltrim(const string &);
string rtrim(const string &);
vector<string> split(const string &);

int maximumHappiness(int x, int y, vector<vector<int>> items) {
    int res = 0;
    
    //declare two vector used for save the element
    vector<pair<int, int>> vectX;
    vector<pair<int, int>> vectY;
    
    for(int i = 0; i < items.size(); i++){
        if(items[i][0] == 1){
            vectX.push_back({items[i][2],items[i][1]});
        }else{
            vectY.push_back({items[i][2],items[i][1]});
        }
    }
    
    //sort the two vector list
    sort(vectX.begin(), vectX.end());
    sort(vectY.begin(), vectY.end());
    
    //travser all the elemnt form vectX and add into res
    for(int i = vectX.size() - 1; i >= 0; i--){
        if(x >= vectX[i].second){
            res += vectX[i].first;
            break;
        }
    }
    
    //travser all the elemnt form vectY and add into res
    for(int j = vectY.size() - 1; j >= 0; j--){
        if(y >= vectY[j].second){
            res += vectY[j].first;
            break;
        }
    }
    
    return res;
}
