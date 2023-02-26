import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DNAList {
    //Initializing variables
    static ArrayList<LinkedList<Character>> sequenceArray = new ArrayList<LinkedList<Character>>();
    static String sequence;
    static String pos;
    static String type;
    static String firstIndex;
    static String secondIndex;
    static int intPos = 0;
    static char[] charSequence;
    static ArrayList<String> printingAll = new ArrayList<String>();

    public static void main(String[] args) {
        File input = new File (args[0]);
        BufferedReader reader;
        //reading the file line by line
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();

            while (line != null) {
                String arr[] = line.split("\\ ", 4);
                String firstWord = arr[0];
                //insert
                if (firstWord.equalsIgnoreCase("insert")) {
                    pos = arr[1];
                    intPos = Integer.parseInt(pos);
                    type = arr[2];
                    sequence = arr[3];
                    charSequence = sequence.toCharArray();
                    insert();
                }
                //remove
                else if (firstWord.equalsIgnoreCase("remove")) {
                    pos = arr[1];
                    intPos = Integer.parseInt(pos);
                    remove();
                }
                //print
                else if (firstWord.equalsIgnoreCase("print")) {
                    print();
                }
                //clip
                else if (firstWord.equalsIgnoreCase("clip")) {
                    clip();
                }
                //copy
                else if (firstWord.equalsIgnoreCase("copy")) {
                    firstIndex = arr[1];
                    secondIndex = arr[2];
                    copy();
                }
                //transcribe
                else if (firstWord.equalsIgnoreCase("transcribe")) {
                    pos = arr[1];
                    intPos = Integer.parseInt(pos);
                    transcribe();
                }
                //System.out.println(line);
                // read next line
                line = reader.readLine();
            }

            reader.close();
            //s.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //DNA = A,C,G,T
        //RNA = A,C,G,U
    }

    //insert method
    public static void insert() {
        //if contains A, C, G, T, U and if position = a numbered index then add it to that index of arraylist
        if (sequence.contains("A") && sequence.contains("C") && sequence.contains("G") || sequence.contains("T") || sequence.contains("U")) {
            //makes sequenceArray the correct size which also makes it so that the index size exists
            if (sequenceArray.size() < intPos) {
                for (int i = sequenceArray.size(); i < intPos; i++) {
                    sequenceArray.add(null);
                    printingAll.add(null);
                }
            }
            //for putting the bioSequence into a LinkedList of Characters
            LinkedList<Character> bioSequence = new LinkedList<Character>();
            for (int i = 0; i < charSequence.length; i++) {
                bioSequence.add(charSequence[i]);
            }
            //printing the index of arraySequence
            sequenceArray.add(intPos,bioSequence);
            printingAll.add(intPos, "" + intPos + "\t" + type);
            if (intPos < (sequenceArray.size())-1) {
                sequenceArray.remove(intPos+1);
                printingAll.remove(intPos+1);
            }
            print();
        }
        //if it doesn't contain those letter then print “Error occurred while inserting”.
        else {
            System.out.println("Error occured while inserting");
        }
    }

    //remove method OVERLOAD THS METHOD
    public static void remove() {
        //sequenceArray.remove(new LinkedList<Character>());
    }

    //print method OVERLOAD THIS METHOD FOR PRINT POS
    public static void print() {
        System.out.println();
        for (int i = 0; i < sequenceArray.size(); i++) {
            if (sequenceArray.get(i) != null) {
                System.out.println(printingAll.get(i) + "\t" + sequenceArray.get(i));
            }
        }
        System.out.println();
    }

    //clip method
    public static void clip() {

    }

    //copy method
    public static void copy() {

    }

    //transcribe method
    public static void transcribe() {

    }
}