package sample;

public class StudentRecord {

    private String studentID;
    private double midtermGrade;
    private double assignGrade;
    private double examGrade;
    private double finalGrade;
    private String letterGrade;


    public StudentRecord(String ID, double assignment, double midterm, double exam){
        this.studentID = ID;
        this.midtermGrade = midterm;
        this.assignGrade = assignment;
        this.examGrade = exam;
    }

    public String getStudentID(){
        return this.studentID;
    }

    public double getMidtermGrade(){
        return this.midtermGrade;
    }

    public double getAssignGrade(){
        return this.assignGrade;
    }

    public double getExamGrade(){
        return this.examGrade;
    }

    public double getFinalGrade(){
        this.finalGrade = ((this.getAssignGrade()*0.2) + (this.getMidtermGrade()*0.3) + (this.getExamGrade()*0.5));
        return this.finalGrade;
    }

    public String getLetterGrade() {

     if (this.getFinalGrade() >= 80 && this.getFinalGrade() <= 100){
         this.letterGrade = "A";
     } else if (this.getFinalGrade() >= 70 && this.getFinalGrade() <= 79){
         this.letterGrade = "B";
     } else if (this.getFinalGrade() >= 60 && this.getFinalGrade() <= 69){
         this.letterGrade = "C";
     } else if (this.getFinalGrade() >= 50 && this.getFinalGrade() <= 59){
         this.letterGrade = "D";
     } else if (this.getFinalGrade() >= 0 && this.getFinalGrade() <= 49){
         this.letterGrade = "F";
     }

     return this.letterGrade;
    }
}
