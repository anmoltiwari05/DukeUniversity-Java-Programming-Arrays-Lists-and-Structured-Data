
/**
 * Write a description of CodonCount here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.lang.Math;
public class CodonCount {
    private HashMap<String, Integer> map;
    public CodonCount(){
        map = new HashMap<String, Integer>();
    }
    
    private String trim(String s, int start){
        String trimmed = s.substring(start, s.length()-((s.length()-start)%3));
        return trimmed;
    }
    
    public void count(String s, int start){
        String trimmed = trim(s, start);
        trimmed = trimmed.toUpperCase();
        for(int i=0;i<trimmed.length();i+=3){
            String codon = trimmed.substring(i,i+3);
            if (map.keySet().contains(codon)){
                    map.put(codon, map.get(codon)+1);
            }
            else{
                    map.put(codon,1);
            }
        }
    }
    
    private String getMostCommonCodon(){
        int value = 0;
        String mostCommon="";
        for(String s: map.keySet()){
            if(map.get(s)>value){
                value = map.get(s);
                mostCommon = s;
            }
        }
        return mostCommon;
    }
    
    public void printCodonCounts(int start, int end){
        for (String s:map.keySet()){
            if (map.get(s)>=start && map.get(s)<=end){
                System.out.println(map.get(s)+" "+s);
            }
        }
        String mostCommon = getMostCommonCodon();
        System.out.println("Total number of Unique Codons: "+map.size());
        System.out.println("Most Common Codon: "+map.get(mostCommon)+" "+mostCommon);
    }
    
    public void tester(){
        map.clear();
        FileResource fr = new FileResource();
        count(fr.asString(),0);
        printCodonCounts(7,100);
    }
}
