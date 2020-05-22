
/**
 * Write a description of CaesarCipher here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i=0; i<encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            if (Character.isLetter(currChar)){
                if (Character.isUpperCase(currChar)){
                    int index = alphabet.indexOf(currChar);
                    encrypted.setCharAt(i, shiftedAlphabet.charAt(index));
                }
                else{
                    int index = (alphabet.toLowerCase()).indexOf(currChar);
                    encrypted.setCharAt(i, (shiftedAlphabet.toLowerCase()).charAt(index));
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
}
