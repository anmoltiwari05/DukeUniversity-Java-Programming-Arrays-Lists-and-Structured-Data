
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author Anmol Tiwari
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> myCharacters;
    private ArrayList<Integer> myCounts;
    public CharactersInPlay(){
        myCharacters = new ArrayList();
        myCounts = new ArrayList();
    }
    
    public void update(String person){
        int index = myCharacters.indexOf(person);
        if (index == -1){
            myCharacters.add(person);
            myCounts.add(1);
        }
        else{
            int value = myCounts.get(index);
            myCounts.set(index, value+1);
        }
    }
      
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for(String lines: fr.lines()){
            int indexOfPeriod = lines.indexOf(".");
            if (indexOfPeriod == -1){continue;}
            else{
                String character = lines.substring(0,indexOfPeriod); 
                update(character);
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        for(int i=0;i<myCharacters.size();i++){
            if (myCounts.get(i)>5){
                System.out.println(myCharacters.get(i)+" "+myCounts.get(i));
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        findAllCharacters();
        for(int i=0;i<myCharacters.size();i++){
            if (num2 >= myCounts.get(i) && myCounts.get(i) >= num1){
                System.out.println(myCharacters.get(i)+" "+myCounts.get(i));
            }
        }
    }
}
