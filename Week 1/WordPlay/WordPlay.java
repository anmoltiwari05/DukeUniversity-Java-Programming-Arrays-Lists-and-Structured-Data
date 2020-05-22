
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class WordPlay {
    public boolean isVowel(char ch){
        String vowel = "AEIOU";
        if (vowel.indexOf(Character.toUpperCase(ch)) != -1){
            return true;
        }
        else {
            return false;
        }
    }
    
    public String replaceVowel(String phrase, char ch){
        StringBuilder noVowel = new StringBuilder(phrase);
        for (int i=0 ; i<noVowel.length(); i++){
            char currChar = noVowel.charAt(i);
            if(isVowel(currChar)){
                noVowel.setCharAt(i, ch);
            }
        }
        return noVowel.toString();
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder noChar = new StringBuilder(phrase);
        for(int i=0;i<noChar.length();i++){
            char currChar = noChar.charAt(i);
            if(Character.toUpperCase(currChar) == Character.toUpperCase(ch)){
                if (i%2 == 0){
                    noChar.setCharAt(i,'*');
                }
                else{noChar.setCharAt(i,'+');}
            }
        }
        return noChar.toString();
    }
    
    public void testIsVowel(){
        boolean bool = isVowel('F');
        System.out.println(bool);
    }
    
    public void testReplaceVowel(){
        String test = replaceVowel("", '*');
        System.out.println(test);
    }
    
    public void testEmphasize(){
        String test = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(test);
    }
}
