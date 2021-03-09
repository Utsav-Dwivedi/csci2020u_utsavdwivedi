package sample;

import java.text.DecimalFormat;

/** This class is used to generate properties for the files to be tested.
 *  The class has members to hold the file name, spam probability and the actual class name.
 *  The class also has getter and setter methods for said members.
 */


public class TestFile {
    private String fileName;
    private double spamProb;
    private String actualClass;

    public TestFile(String name, double spamProbability, String actualClass) {
        this.fileName = name;
        this.spamProb = spamProbability;
        this.actualClass = actualClass;
    }

    public String getFilename() { return this.fileName; }

    public double getSpamProbability() { return this.spamProb; }

    public String getSpamProbRounded() {
        DecimalFormat df = new DecimalFormat("0.00000");
        return df.format(this.spamProb);
    }

    public String getActualClass(){return this.actualClass;}

    public void setFilename(String value) { this.fileName = value; }

    public void setSpamProbability(double val) { this.spamProb = val; }

    public void setActualClass(String value) { this.actualClass = value; }
}