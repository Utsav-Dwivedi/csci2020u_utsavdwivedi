package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Controller {


    @FXML private TextField directory;

    @FXML private TableColumn<String, String> fileColumn;
    @FXML private TableColumn<String, String> actualClassColumn;
    @FXML private TableColumn<String, Double> probabilityColumn;

    private TreeMap<String, Double> wordCounts;
    private TreeMap<String, Double> HamFrequency = new TreeMap<String, Double>();
    private TreeMap<String, Double> SpamFrequency = new TreeMap<String, Double>();
    private TreeMap<String, Integer> spamCount = new TreeMap<String, Integer>();
    private TreeMap<String, Double> SpamGivenWord = new TreeMap<String, Double>();
    private TreeMap<String, Integer> hamCount = new TreeMap<String, Integer>();
    private TreeMap<String, Double> ProbSpam = new TreeMap<String, Double>();

    double accuracy;
    double precision;

    double numTruePostives = 0;
    double numFalsePositives = 0;
    double numTrueNegatives = 0;
    double numTestFiles = 0;

    /** This is method is used for the Test button to use the directory chooser.
     * @param event
     *
     * @throws IOException
     */
    public void TestBtnAction(ActionEvent event) throws IOException {

        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File("."));
        File md = dc.showDialog(null);

        if (md != null){
            String path = md.getAbsolutePath();
            directory.setText(path);
            parseFile(md);

        }else{
            System.out.println("Error in finding directory");
        }
    }



    /**	This method is used to parse through the file.
     Checks a given file and calls the trainFrequency method on them.
     @param file This is the file to be parsed through
     **/

    public void parseFile(File file) throws IOException {
        System.out.println("Starting parsing the file:" + file.getAbsolutePath());

        if (file.isDirectory()) {
            if (file.getName().equals("ham") || file.getName().equals("ham2")) {
                trainFrequency(file,hamCount,HamFrequency);

            } else if (file.getName().equals("spam")) {
                trainFrequency(file,spamCount,SpamFrequency);
            } else {
                //parse each file inside the directory
                File[] content = file.listFiles();
                for (File current : content) {
                    parseFile(current);
                }
            }
        }
    }

    /** This is the method used to train the frequency of words.
     Looks through the file and puts the words into a map.
     Iterate through that map to insert the wordlist into another map wordCount.
     Map the frequency of words.
     @param file The file that will be searched through
     @param wordCount A map to hold the count of words
     @param wordFreq A map to hold the frequency/probability of the words
     **/

    public void trainFrequency(File file,TreeMap<String, Integer> wordCount,TreeMap<String, Double> wordFreq) throws IOException {
        File[] directory = file.listFiles();
        for (int i = 0; i < directory.length; i++) {
            HashMap<String, Integer> temp = new HashMap<String, Integer>();
            // Gather list of words in specific file and put in temporary Map
            Scanner scanner = new Scanner(directory[i]);
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (isValidWord(word)) {
                    if (!temp.containsKey(word)) {
                        temp.put(word, 1);
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : temp.entrySet()) {
                countWord(wordCount,entry.getKey());
            }
            temp.clear();

            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                double probWord = (double) entry.getValue() / (double) directory.length;
                wordFreq.put(entry.getKey(), probWord);
            }
        }
    }

    /** This method is used to count the reoccurring words in the file.
     It maps the words to a TreeMap.
     @param map This is used to store the reoccurring words
     @param word This is the word that will be compared and checked for
     **/

    private void countWord(TreeMap<String, Integer> map, String word) {
        if (map.containsKey(word)) {
            int previous = map.get(word);
            map.put(word, previous + 1);
        } else {
            map.put(word, 1);
        }

    }

    /** This method checks if a word is composed of only letters.
     @param word The word that will be checked in the method call
     **/

    private boolean isValidWord(String word) {
        String allLetters = "^[a-zA-Z]+$";
        // returns true if the word is composed by only letters otherwise returns false;
        return word.matches(allLetters);
    }

    /** This method applies a formula to normalizes the values
     @param ProbSpam is the probabilty of a file being spam, given it contains a word
     **/
    private double Normalizer (double ProbSpam){
        return ((Math.log( (1 - ProbSpam - Math.log(ProbSpam)))));
    }

    public double testProbability(File file) throws FileNotFoundException {
        double PSpamgivenFile;
        double n = 0.0;
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String word = scanner.next();
            if (isValidWord(word) && SpamGivenWord.containsKey(word)) {
                n += Normalizer(SpamGivenWord.get(word));
            }
        }
        PSpamgivenFile = 1/(1 + Math.pow(Math.E,n));
        return PSpamgivenFile;
    }

    /** public double getAccuracy(){
     accuracy = (numTruePos + numTrueNeg)/(hamFileNum + spamFileNum);
     return accuracy;
     }

     public double getPrecision(){
     precision = numTruePos/(numTruePos + numFalsePos);
     return precision;
     }

     **/
    public void Positives(File file, double pSpam) throws FileNotFoundException {
        double n = 0.0;
        double precision;
        // accumulate accuracy/precision statistics
        if (file.getParent() == ("spam") && pSpam > 0.5) {
            numTruePostives += 1.0;
        }
        if (file.getParent() == ("ham") || file.getParent() == ("ham2") && pSpam > 0.5) {
            numFalsePositives += 1.0;
        }

    }

    public double precision(){
        return  numTruePostives / (numFalsePositives + numTruePostives);
    }
    public double getAccuracy(){
        return (numTruePostives+numTrueNegatives)/numTestFiles;
    }

    //////// UNFINISHED METHODS:


    /**
     *          public double spamProb(){
     *              scan through file
     *
     *              determine if word is valid word
     *
     *              calculate probability if the word is detected as spam
     *
     *              increase true/false pos and neg numbers and the testfile count
     *          }
     *
     *
     *          Use observableList object to getAllFiles in order to display them in the tableView
     *
     */
}
