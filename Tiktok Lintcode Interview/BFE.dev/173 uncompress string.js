/* BFE.dev 173. uncompress string  && 394. Decode String

Given a compressed string, return its original form.

For example.
uncompress('3(ab)') // 'ababab'
uncompress('3(ab2(c))') // 'abccabccabcc'

1. a number k followed by a pair of parenthesis, meaning to repeat the substring inside the parenthesis by k times, k is positive integer.
2. inputs are guaranteed to be valid input like above example, there is no numerical digit in original form.

*/

/**
 * @param {string} str
 * @returns {string}
 */
function uncompress(str) {
  // your code here
  let charDeque = [];
  let numDeque = [];

  charDeque.push(""); //申明charDeque 双向队列的类型

  // 一共有四种情况: number, dict, [, ]  
  //把数字和字母分别加入到stack中，然后从后往前遍历确定区域(遇到左隔板 "["）
  for(let i = 0; i < str.length; i++){
    let curr = str[i];

    //1.当遇到数字时
    if(/\d/.test(curr)){
      let start = i; //记录数字开始index位置
      while(/\d/.test(str[i + 1])){
        i++;
      } //找到数字的结束index位置，然后加入到numDeque中
      numDeque.push(Number(str.substring(start, i + 1)));
    } else if(curr === '('){
      charDeque.push('');
    } else if(curr === ')'){
      let times = numDeque.pop(); //得到需要重复的数量
      let str = charDeque.pop(); //进行重复的字符串
      let newStr = "";

      for(let i = 0; i < times; i++){
        newStr += str;
      }

      charDeque.push(charDeque.pop() + newStr); //把新的字符串重新加入到Deque中
    } else { //当遍历到的是字母的时候
      //把当前遍历到的 和 之前已经存在的组成新的 加入到deque中
      charDeque.push(charDeque.pop() + curr);
    }
  }

  return charDeque.pop();
}


