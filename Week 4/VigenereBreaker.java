/**
 @author: Anmol Tiwari 
**/

import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    private String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        int i=0;
        for(char c: message.toCharArray()){
            if (i%totalSlices == whichSlice){
                sb.append(c);
            }
            i++;
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i=0;i<klength;i++){
            String sliced = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(sliced);            
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for(String lines: fr.lines()){
            words.add(lines.toLowerCase());
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count=0;
        for(String word: message.toLowerCase().split("\\W+")){
            if(dictionary.contains(word)){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int counts = 0;
        int keyLength = 0;
        String plainText = "";
        int[] keys = new int[100];
        for(int i=1;i<100;i++){
            int[] currKeys = tryKeyLength(encrypted, i,mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(currKeys);
            String currPlainText = vc.decrypt(encrypted);
            int currCounts = countWords(currPlainText, dictionary);
            if(currCounts>counts){
                keys = currKeys;
                counts = currCounts;
                keyLength = i;
                plainText = currPlainText;
            }
        }
        //for(int key:keys){
            //System.out.print(key+",");
        //}
        //System.out.println("Key Length: "+ keyLength);
        //System.out.println("Words matched from dictionary: "+ counts+"\\"+plainText.split("\\W+").length);
        return plainText;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        for(String word:dictionary){
            for(char c: word.toLowerCase().toCharArray()){
                if(counts.keySet().contains(c)){
                    counts.put(c, counts.get(c)+1);
                }
                else{counts.put(c, 1);}
            }
        }
        int maxCount = 0;
        char mostCommonChar = 'a';
        for(char c:counts.keySet()){
            int currMaxCount = counts.get(c);
            if(currMaxCount>maxCount){
                maxCount = currMaxCount;
                mostCommonChar = c;
            } 
        }
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxCounts = 0;
        String plainText = ""; 
        String lang = "";
        for(String language: languages.keySet()){
            HashSet<String> currLang = languages.get(language);
            String currPlainText = breakForLanguage(encrypted, currLang);
            int currCounts = countWords(currPlainText, currLang);
            //System.out.println("Plain Text: "+ currPlainText);
            //System.out.println("Language: "+ language);
            if (currCounts>maxCounts){
                plainText = currPlainText;
                lang = language;
                maxCounts = currCounts;
            }
        }
        System.out.println("Plain Text: "+ plainText);
        System.out.println("Language: "+ lang);
        //return plainText;
    }
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString(); 
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr1 = new FileResource(f);
            HashSet<String> dictionary = readDictionary(fr1);
            languages.put(f.getName(), dictionary);
            System.out.println("Done with "+f.getName());
        }
        breakForAllLangs(encrypted, languages);
        
        
        //int klength = 4;
        //int[] keys = tryKeyLength(encrypted, klength, 'e');
        //VigenereCipher vc = new VigenereCipher(keys);
        //String plainText = vc.decrypt(encrypted);
        //System.out.println(plainText);
    }
    
    public void testKey(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int keySize = 38;
        int[] keys = new int[keySize];
        keys = tryKeyLength(encrypted, keySize, 'e'); 
        for(int key:keys){
            System.out.println(key);
        }
    }
    
    public void testMostCommonCharIn(){ 
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr); 
        char mostCommon = mostCommonCharIn(dictionary);
        System.out.println(mostCommon);
    }
}
