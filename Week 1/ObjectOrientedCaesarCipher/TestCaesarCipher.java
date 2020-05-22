
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class TestCaesarCipher {
    public int maxIndex(int[] values){
        int count=0, index=0;
        for(int i=0;i<values.length;i++){
            if (values[i]>count){
                count = values[i];
                index = i;
            }
        }
        return index;
    }
    
    public int[] countLetters(String phrase){
        int[] count = new int[26];
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i=0;i<phrase.length();i++){
            char currChar= phrase.charAt(i);
            int index = letters.indexOf(Character.toUpperCase(currChar));
            if (index != -1){count[index] += 1;}
        }
        return count;
    }
        
    public String breakCaesarCipher(String input){
        int[] counts = countLetters(input);
        int maxDex = maxIndex(counts);
        int shift = maxDex - 4;
        if(maxDex < 4){
            shift = 26- (4 -maxDex);
        }
        CaesarCipher cc = new CaesarCipher(shift);
        return cc.decrypt(input);
    }
    
    public void simpleTests(){
        CaesarCipher cc = new CaesarCipher(15);
        FileResource fr = new FileResource();
        String input = fr.asString();
        String encrypted = cc.encrypt(input);
        String decrypted = breakCaesarCipher(encrypted);
        String plainText = cc.decrypt(encrypted);
        System.out.println("Plain Text: "+ plainText);
        System.out.println("Encrypted String: " + encrypted);
        System.out.println("Decrpypted String: " + decrypted);
    }
}
