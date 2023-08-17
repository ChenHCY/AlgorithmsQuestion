/*  102. validate string of parentheses

Given a string containing only following characters:

parentheses : ( or )
brackets: [ or ]
braces: { or }
write a function to determine if they are valid.

By 'valid', it means all should be rightly paired, and with the valid order.


validate('{}[]()') 
// true

validate('{[()]}') 
// true

validate('{[}]') 
// false, they are not in the right order

validate('{}}') 
// false, last `}` is not paired with `{`
Follow-up

What is time & space complexity of your approach ? Can you do it better?
*/

**
 * @param {string} str
 * @return {boolean} 
 */
function validate(str) {
  // your code here
  let stack = [];

  for(let i = 0; i < str.length; i++){
    if(str[i] === '['){
      stack.push(']');
    } else if(str[i] === '('){
      stack.push(')');
    } else if(str[i] === '{'){
      stack.push('}');
    } else if(stack.length == 0 || str[i] != stack.pop()){
      return false;
    }
  }
  return stack.length == 0 ? true : false;
}
