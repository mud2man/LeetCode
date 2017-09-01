/* Hash Table: O(1)
 * 1. Have a hashmap dictionary to store the abbreviation-word pair
 * 2. If there are more than two different words mapping to the same abbreviation, abbreviation-word pair = (abbreviation, null)
 * 3. Only if there is no the same abbreviation, or the word is equal to the word in abbreviation-word pair, then return true
 */

import java.util.*; // Stack

public class ValidWordAbbr {
    private HashMap<String, String> dictionary;
    
    private String getAbbreviation(String word){
        int length = word.length();
        if(length < 3){
            return word;
        }
        else{
            StringBuilder abbr = new StringBuilder("");
            abbr.append(word.charAt(0));
            abbr.append(Integer.toString(length - 2));
            abbr.append(word.charAt(length - 1));
            return abbr.toString();
        }
    }
    
    public ValidWordAbbr(String[] dictionary) {
        this.dictionary = new HashMap<String, String>();
        for(String word: dictionary){
            String abbr = getAbbreviation(word);
            if(this.dictionary.containsKey(abbr)){
                String value = this.dictionary.get(abbr);
                if(value != null && !value.equals(word)){
                    this.dictionary.put(abbr, null);
                }
            }
            else{
                this.dictionary.put(abbr, word);
            }
        }
    }
    
    public boolean isUnique(String word) {
        String abbr = getAbbreviation(word);
        boolean retVal;
        
        if(dictionary.containsKey(abbr)){
            String value = dictionary.get(abbr);
            if(value != null && value.equals(word)){
                retVal = true; 
            }
            else{
                retVal = false; 
            }
        }
        else{
            retVal = true; 
        }
        return retVal;
    }    
    
    public static void main(String[] args){
        String[] dictionary = new String[] {"deer", "door", "cake", "card", "card"};

        System.out.println("dictionary: " + Arrays.asList(dictionary));

        ValidWordAbbr validWordAbbr = new ValidWordAbbr(dictionary);
        System.out.println("isUnique(dear):" +  validWordAbbr.isUnique("dear"));
        System.out.println("isUnique(cart):" +  validWordAbbr.isUnique("cart"));
        System.out.println("isUnique(cane):" +  validWordAbbr.isUnique("cane"));
        System.out.println("isUnique(make):" +  validWordAbbr.isUnique("make"));
        System.out.println("isUnique(cake):" +  validWordAbbr.isUnique("cake"));
        System.out.println("isUnique(card):" +  validWordAbbr.isUnique("card"));
    }
}
