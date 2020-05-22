
/**
 * Write a description of WordLengths here.
 * 
 * @author Anmol Tiwari 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class WordLengths {
    public int[] countWordLengths(FileResource fr, int[] counts){
        for (String word : fr.words()){
            int length = word.length();
            if (length < counts.length){
                if (Character.isLetter(word.charAt(length-1))){
                    counts[length] += 1;
                }
                else{
                    counts[length-1] += 1;
                }
            }
            else{
                counts[counts.length - 1] += 1;
            }
        }
        return counts;
    }
    
    public int indexOfMax (int[] values){
        int count=0, index=0;
        for(int i=1;i<values.length;i++){
            if(values[i]>count){
                count = values[i];
                index = i;
            }
        }
        return index;
    }
    
    public void testCountWordLength(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        counts = countWordLengths(fr, counts);
        for (int i=1;i<counts.length;i++){
            if (counts[i] > 0){
                System.out.println("Words of Length "+ i + ": " + counts[i]);
            }
        }
        System.out.println("Most common word length: " + indexOfMax(counts));
    }
}
