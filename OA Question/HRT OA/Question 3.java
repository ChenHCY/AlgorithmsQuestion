/* HRT OA Question 3 
For a chemical reaction represented by a string, verify that the chemical reaction is a balanced 
reaction (i.e. that we didn't somehow lose or gain an atom during the reaction). 
If the reaction is balanced return true, otherwise return false.

For example, for the hydrogen combustion reaction:

'2 H2 + O2 = 2 H2O'
would output true because the number of atoms in the reactants matches up with the number of atoms in the product.

However, for the precipitation of silver-chloride:
'NaCl + AgNO3 = NaNO3 + Ag'
the output should be false because we're missing the chlorine atom in
the products.

The reactants and products will always be separated by a right-pointing arrow "->" and the individual molecules within the reactants/products are always separated by a "+" sign. Multiple
molecules are represented by a number and space prefacing the
molecule (e.g., "2 H20").

Other test cases:
'O2 = NaCl' = false
'C6H12O6 + 6 O2 = 6 CO2 + 6 H2O' = true
'10 NH3 + 10 H2O = 10 NH4 + OH' = false
*/

import java.util.HashMap;

public class ChemicalReactionBalance {
    public static void main(String[] args) {
        String test1 = "2 H2 + O2 = 2 H2O";
        String test2 = "NaCl + AgNO3 = NaNO3 + Ag";
        String test3 = "O2 = NaCl";
    
        System.out.println(isBalancedReaction(test1));
        System.out.println(isBalancedReaction(test2));
        System.out.println(isBalancedReaction(test3));
    }
  
    public static boolean isBalancedReaction(String reaction){
        //Use the HashMap to save every chemical with numbers
        HashMap<String, Integer> leftAtoms = new HashMap<>();
        HashMap<String, Integer> rightAtoms = new HashMap<>();
        
        int currentMultiplier = 1;
        int amount = 0;
        String currentAtom = "";
        String parseMultiplier = "";
        boolean isLeftSide = true;
        
        for(int i = 0; i < reaction.length(); i++){
            if(Character.isDigit(reaction.charAt(i))){
                parseMultiplier+=reaction.charAt(i);
                
                while(i < reaction.length()-1 && Character.isDigit(reaction.charAt(i+1))){
                    parseMultiplier += reaction.charAt(i+1);
                    i++;
                }
                currentMultiplier = Integer.parseInt(parseMultiplier);
                parseMultiplier="";
            }else if(Character.isUpperCase(reaction.charAt(i))){
                currentAtom+=reaction.charAt(i);
                
                while(i < reaction.length()-1 && Character.isLowerCase(reaction.charAt(i+1))){
                    currentAtom+=reaction.charAt(i+1);
                    i++;
                }
                
                while(i < reaction.length()-1 && Character.isDigit(reaction.charAt(i+1))){
                    parseMultiplier+=reaction.charAt(i+1);
                    i++;
                }
                
                if(parseMultiplier.length()==0){
                    parseMultiplier="1";
                }
                
                if(isLeftSide){
                    amount = currentMultiplier*Integer.parseInt(parseMultiplier);
                    if(!leftAtoms.containsKey(currentAtom)){
                        leftAtoms.put(currentAtom,amount);
                    }else{
                        amount+=leftAtoms.get(currentAtom);
                        leftAtoms.put(currentAtom,amount);
                    }
                }else{
                    amount = currentMultiplier*Integer.parseInt(parseMultiplier);
                    if(!rightAtoms.containsKey(currentAtom)){
                        rightAtoms.put(currentAtom,amount);
                    }else{
                        amount+=rightAtoms.get(currentAtom);
                        rightAtoms.put(currentAtom,amount);
                    }
                }
                
                parseMultiplier = "";
                currentAtom = "";
            }else if(reaction.charAt(i) =='='){
                isLeftSide=false;
                i++;
            }else if(reaction.charAt(i)=='+'){
                currentMultiplier = 1;
            }
        }
        
        for(String atom : leftAtoms.keySet()){
            if(!rightAtoms.containsKey(atom)) 
              return false;
            if(leftAtoms.get(atom)!=rightAtoms.get(atom)) 
              return false;
        }
        
        return true;
    }
}
