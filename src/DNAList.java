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
    static int start;
    static int end;
    static int intPos = 0;
    static int intFirstIndex = 0;
    static int intSecondIndex = 0;
    static char[] charSequence;
    static ArrayList<String> printingAll = new ArrayList<String>();

    public static void main(String[] args) {

                if (args.length < 2) {
                    System.err.println("Usage: java DNAList <array-size> <command-file>");
                    System.exit(1);
                }

                int arraySize = Integer.parseInt(args[0]);
                String commandFile = args[1];

                // Your code goes here
            }
        }


        //reading the file line by line
        try {
            File file = new File("in.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
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
                else if (firstWord.equalsIgnoreCase("print") && arr.length == 2) {
                    intPos = Integer.parseInt(arr[1]);
                    printElement();
                }
                else if (firstWord.equalsIgnoreCase("print") && arr.length == 1) {
                    print();
                }
                //clip
                else if (firstWord.equalsIgnoreCase("clip")) {
                    intPos = Integer.parseInt(arr[1]);
                    start = Integer.parseInt(arr[2]);
                    end = Integer.parseInt(arr[3]);
                    clip();
                }
                //copy
                else if (firstWord.equalsIgnoreCase("copy")) {
                    firstIndex = arr[1];
                    secondIndex = arr[2];
                    intFirstIndex = Integer.parseInt(firstIndex);
                    intSecondIndex = Integer.parseInt(secondIndex);
                    copy();
                }
                //transcribe
                else if (firstWord.equalsIgnoreCase("transcribe")) {
                    pos = arr[1];
                    intPos = Integer.parseInt(pos);
                    transcribe();
                }
                // read next line
            }

            scanner.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    //insert method
    public static void insert() {
        //if DNA doesn't contain A, C, G, T or if RNA doesn't contain A, C, G, U and if position = a numbered index then add it to that index of arraylist
        //if it doesn't contain those letter for DNA and RNA respectively then print “Error occurred while inserting”.
        for (int i = 0; i < sequence.length(); i++) {
            if (type.equalsIgnoreCase("DNA")) {
                if (!(sequence.charAt(i) == 'A' || sequence.charAt(i) == 'C' || sequence.charAt(i) == 'G' || sequence.charAt(i) == 'T')) {
                    System.out.println("Error occured while inserting");
                    return;
                }
            }
            if (type.equalsIgnoreCase("RNA")) {
                if (!(sequence.charAt(i) == 'A' || sequence.charAt(i) == 'C' || sequence.charAt(i) == 'G' || sequence.charAt(i) == 'U')) {
                    System.out.println("Error occured while inserting");
                    return;
                }
            }
        }
        //makes sequenceArray the correct size which also makes it so that the index size exists
        if (sequenceArray.size() < intPos) {
            for (int i = sequenceArray.size(); i <= intPos; i++) {
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

    //remove method OVERLOAD THS METHOD
    public static void remove() {
        if (intPos < (sequenceArray.size()) && sequenceArray.get(intPos) != null) {
            sequenceArray.set(intPos,null);
            printingAll.set(intPos,null);
            print();
        }
        else {
            System.out.println("No sequence to remove at specified position");
        }
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
    //print single sequence
    public static void printElement() {
        System.out.println();
        if (sequenceArray.get(intPos) != null) {
            System.out.println(printingAll.get(intPos) + "\t" + sequenceArray.get(intPos));
        }
        else System.out.println("No sequence to print at specified position");
        System.out.println();

    }

    //clip method
    public static void clip() {
        LinkedList<Character> clipBioSequence = new LinkedList<Character>();
        //if the start and end are not within the sequence then print “Error occurred while clipping”
        if (sequenceArray.get(intPos) == null) {
            System.out.println("No sequence at specified position");
            return;
        }
        if (start < 0) {
            System.out.println("Invalid start index");
            return;
        }
        if (start > sequenceArray.get(intPos).size()) {
            System.out.println("Start index is out of bounds");
            return;
        }
        if (end > sequenceArray.get(intPos).size()) {
            System.out.println("End index is out of bounds");
            return;
        }
        //check if there is a sequence at the index
        if (start>=0 && sequenceArray.get(intPos) != null) {
            //check if the start and end are within the sequence
            if (start<end) {
                if (end<=sequenceArray.get(intPos).size()){
                    //clipBioSequence = sequenceArray.get(intPos);
                    int clipLength = end-start;
                    for (int i = 0; i <= clipLength; i++) {
                        char tempLinkedListChar = sequenceArray.get(intPos).get(start+i);
                        clipBioSequence.add(i,tempLinkedListChar);
                    }
                    sequenceArray.set(intPos,clipBioSequence);
                }
            }
        }
    }

    //copy method
    public static void copy() {
        //sets the first index to the second index
        if (sequenceArray.get(intFirstIndex) != null && sequenceArray.get(intSecondIndex) != null) {
            sequenceArray.set(intSecondIndex,sequenceArray.get((intFirstIndex)));
        }
        else {
            System.out.println("No sequence to copy at specified position");
        }
    }

    //transcribe method
    public static void transcribe() {
        if (sequenceArray.get(intPos) == null) {
            System.out.println("No sequence to transcribe at specified position");
            return;
        }
        if (printingAll.get(intPos).contains("RNA")) {
            System.out.println("Cannot transcribe RNA");
            return;
        }
        if (printingAll.get(intPos).contains("DNA")) {
            while (sequenceArray.get(intPos).contains('T')) {
                int index = sequenceArray.get(intPos).indexOf('T');
                sequenceArray.get(intPos).set(index,'U');
            }

            //Switch statement for complementing the DNA to RNA
            for (int i = 0; i < sequenceArray.get(intPos).size(); i++) {
                switch (sequenceArray.get(intPos).get(i)) {
                    case 'A':
                        sequenceArray.get(intPos).set(i,'U');
                        break;
                    case 'C':
                        sequenceArray.get(intPos).set(i,'G');
                        break;
                    case 'G':
                        sequenceArray.get(intPos).set(i,'C');
                        break;
                    case 'U':
                        sequenceArray.get(intPos).set(i,'A');
                        break;
                }
            }

            //change the printingAll to RNA
            printingAll.set(intPos,printingAll.get(intPos).replace("DNA","RNA"));


        }
    }
}