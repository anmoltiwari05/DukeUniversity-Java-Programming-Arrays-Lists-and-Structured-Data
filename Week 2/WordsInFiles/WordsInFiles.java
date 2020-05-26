
/**
 * Write a description of WordsInFiles here.
 * 
 * @author Anmol Tiwari 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    
    public void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word: fr.words()){
            if (map.keySet().contains(word)){
                ArrayList<String> list = map.get(word);
                if(list.contains(f.getName())){continue;}
                else{
                    list.add(f.getName());
                    map.put(word, list);
                }
            }
            else{
                ArrayList<String> list = new ArrayList<String>();
                list.add(f.getName());
                map.put(word, list);
            }
        }
    }
    
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max=0;
        for(String s: map.keySet()){
            ArrayList<String> list = map.get(s);
            int numberOfFiles = list.size();
            if (numberOfFiles > max){
                max = numberOfFiles;
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        int value = 0;
        ArrayList<String> words = new ArrayList<String>();
        for(String s: map.keySet()){
            ArrayList<String> list = map.get(s);
            int numberOfFiles = list.size();
            if (numberOfFiles == number){
                words.add(s);
            }
        }
        return words;
    }
    
    public void printFilesIn(String word){
        ArrayList<String> files = new ArrayList<String>();
        files = map.get(word);
        for(int i=0;i<files.size();i++){
            System.out.println(word + " appears in: "+files.get(i));
        }
    }
    
    public void tester(){
        int number = 7;
        //String word = "laid";
        buildWordFileMap();
        //printFilesIn(word);
        ArrayList<String> list = wordsInNumFiles(number);
        for(int i=0;i<list.size();i++){
            System.out.println("Words that appear in exactly "+number+" Number of Files: "+list.get(i));
        }
        System.out.println("Total number of words that occur in "+ number+" files: "+list.size());
        System.out.println("Max number: "+ maxNumber());
    }
}
