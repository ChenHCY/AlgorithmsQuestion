/*87. longest substring with unique characters

Given a string, please find the longest substring that has no repeated characters.

If there are multiple result, any one substring is fine.

longestUniqueSubstr('aaaaa')
// 'a'
longestUniqueSubstr('abcabc')
// 'abc', or 'bca', or 'cab'
longestUniqueSubstr('a12#2')
// 'a12#'

Follow-up
What is the time & space cost of your solution ? Could you do it better?

*/

/**
 * @param {string} str
 * @return {string}
 */
function longestUniqueSubstr(str) {
  // your code here
  let res = "";

  for(let i = 0; i < str.length; i++){
    if(res.includes(str.charAt(i))){
      break;
    } else{
      res += str.charAt(i);
    }
  }

  return res;
}
