
/**
 * Write a description of WordFrequencies here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList();
        myFreqs = new ArrayList();
    }
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String words : fr.words()){
            int index = myWords.indexOf(words.toLowerCase());
            if (index == -1){
                myWords.add(words.toLowerCase());
                myFreqs.add(1);
            }
            else{
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int value = 0;
        for (int i=0;i<myFreqs.size();i++){
            if (myFreqs.get(i)>value){
                value = myFreqs.get(i);
            }
        }
        return myFreqs.indexOf(value);
    }
    
    public void tester(){
        findUnique();
        
        for (int i=0;i<myWords.size();i++){
            System.out.println(myFreqs.get(i)+" "+myWords.get(i));
        }
        System.out.println("Number of unique words: "+myWords.size());
        System.out.println("The word that occurs most often and its count are: "+myWords.get(findIndexOfMax())+" "+myFreqs.get(findIndexOfMax()));
    }
}
