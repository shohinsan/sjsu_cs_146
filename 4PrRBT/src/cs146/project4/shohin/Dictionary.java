package cs146.project4.shohin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Dictionary extends RedBlackTree {

    //Inserts the dictionary into a RBT
    public static RedBlackTree generateDictionary() {
        RedBlackTree dictionaryRBT = new RedBlackTree();
        try {
            //Read the dictionary text file
            BufferedReader reader = new BufferedReader(new FileReader("/Users/insidious/San Jose State University/2cmpe146/Project/4PrRBT/src/cs146/project4/shohin/dictionary.txt"));
            String line;
            //initiate starting time
            double startingTime = System.currentTimeMillis();
            while ((line = reader.readLine()) != null) //if next isnt null, continue to insert
            {
                dictionaryRBT.insert(line);
            }
            //ending time of insertion
            double endingTime = System.currentTimeMillis();
            System.out.println("Runtime of insertion into dictionary: " + (endingTime - startingTime)+" ms");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionaryRBT;
    }

    //Creates an arraylist containing words from the poem
    public static ArrayList<String> wordsOfPoem() {
        ArrayList<String> listOfWordsFromPoem = new ArrayList<>();
        try {
            //reads the poem text file, pretty similar to before
            BufferedReader reader = new BufferedReader(new FileReader("/Users/insidious/San Jose State University/2cmpe146/Project/4PrRBT/src/cs146/project4/shohin/poem.txt"));
            String line;
            double startingTime = System.currentTimeMillis();
            while ((line = reader.readLine()) != null) {
                //puts the words from the string line into an array of strings
                String[] changedLine = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                //Insert words into arraylist from the array of strings
                listOfWordsFromPoem.addAll(Arrays.asList(changedLine));
            }
            double endingTime = System.currentTimeMillis();
            System.out.println("Runtime of string to word: " + (endingTime - startingTime)+" ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfWordsFromPoem;
    }

    public static void spellChecker() throws IOException {
        //initiates counter (how many words are nonexistent
        int counter = 0;
        double startingTime = System.currentTimeMillis();
        RedBlackTree dictionaryRBT = generateDictionary();
        ArrayList<String> listOfWordsFromPoem = wordsOfPoem();
        for (String s : listOfWordsFromPoem) {
            //if the dictionary does not contain the word, increase counter
            if (dictionaryRBT.lookup(Node.root, s) == null) {
                counter++;
            }
        }
        double endingTime = System.currentTimeMillis();
        System.out.println("Runtime of spellChecker: " + (endingTime - startingTime) +" ms");
        System.out.println("Number of words in poem: " + listOfWordsFromPoem.size());
        System.out.println("Number of mismatched/nonexistent words: " + counter);

    }

    public static void main(String[] args) {
        //tests spellChecker, runs it
        try {
            spellChecker();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}