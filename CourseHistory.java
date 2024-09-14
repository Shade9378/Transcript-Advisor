import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;



//NOTES:  You will need the import statements that appear at the top of this file, so you should
//leave them in place.  Follow the list of steps on the project writeup to complete the CourseHistory
//class that is started below.  This entire block of comments should be deleted when you are done. 


public class CourseHistory
{
    private ArrayList<CompletedCourse> courseList;
    
    public CourseHistory()
    {  courseList = new ArrayList<CompletedCourse>();  
       String department;
       String courseNumber;
       String semesterTaken;
       String credit;
       String grade;
       String competency;
       String distributionArea;
        
       try
       {
           FileReader reader = new FileReader("finishedcourses.txt");
           Scanner in = new Scanner(reader);
       
           while(in.hasNextLine())   
           {  department = in.nextLine();  
              courseNumber = in.nextLine();
              semesterTaken = in.nextLine();
              credit = in.nextLine();     
              grade = in.nextLine();
              competency = in.nextLine();
              distributionArea = in.nextLine();  
              CompletedCourse theCourse = new CompletedCourse(department, courseNumber,semesterTaken, credit, grade, competency, distributionArea);
              ///theCourse.displayCourse();  //You will be replacing this line 
              courseList.add(theCourse);
          }
          in.close();  //Close the file when we are done reading it
       } catch (IOException exception)
       {
          System.out.println("Error processing file: " + exception);
       }  
    
    }
    
    public void displayCourseHistory() 
    {
        System.out.println("<*** Course History ***>");
        for (int i = 0; i < courseList.size(); i += 1) 
        {
            System.out.println(courseList.get(i).getDepartment());
            System.out.println(courseList.get(i).getCourseNumber());
            System.out.println(courseList.get(i).getSemesterTaken());
            System.out.println(courseList.get(i).getCompetency());
            System.out.println(courseList.get(i).getCredit());
            System.out.println(courseList.get(i).getGrade());
            System.out.println(courseList.get(i).getDistArea());
        }
    }
    
    public void summaryReport() 
    {
        System.out.println("<*** Summary Report ***>");
        double totalCredEarned = 0;
        double GPA = 0;
        double credEarned = 0;
        for (int i = 0; i < courseList.size(); i += 1) {
            if (courseList.get(i).getGrade() > 0) {
                totalCredEarned += courseList.get(i).getCredit();
            }
            credEarned += courseList.get(i).getCredit();
            GPA += courseList.get(i).getGrade(); 
        }
        System.out.println("Total Credit Attemped:" + credEarned);
        System.out.println("Total Credit Earned:" + totalCredEarned);
        System.out.println("GPA:" + GPA/credEarned);
    }
    
    private void distAreaCourses(String distArea)
    {
        int numCompleted = 0;
        CompletedCourse crs;
        for (int i = 0; i < courseList.size(); i += 1){
             if (courseList.get(i).getDistArea().equals(distArea)){
                 if (courseList.get(i).getGrade() > 0) {
                     numCompleted += 1;
                 }
                 crs = courseList.get(i);
                 System.out.println(crs.getDepartment() + crs.getCourseNumber());
             }
         }
         System.out.println("Number of Courses Completed: " + numCompleted);
    }
    
    public void distAreaReport()
    {
         System.out.println("<*** Distribution Area Report ***>");
         
         System.out.println("- Art and Humanity Courses Attemped:");
         distAreaCourses("AH");
         
         System.out.println("- Social Science Courses Attemped:");
         distAreaCourses("SS");
         
         System.out.println("- Science and Mathematic Courses Attemped:");
         distAreaCourses("SM");
         
         System.out.println("- Liberal Art Courses Attemped:");
         distAreaCourses("LA");
         
    }
    
    public void compReport() 
    {
        int numQ = 0;
        int numW = 0;
        int numS = 0;
        String statusQ = "completed";
        String statusW = "completed";
        String statusS = "completed";
        CompletedCourse crs;
        
        System.out.println("<*** Competency Report ***>");
        for (int i = 0; i < courseList.size(); i += 1){
            crs = courseList.get(i);
            if (crs.getCompetency().equals("Q")){
                numQ += 1;
            }
            if (crs.getCompetency().equals("W")){
                numW += 1;
            }
            if (crs.getCompetency().equals("S")){
                numS += 1;
            }
         }
        if ((numQ > 0) && (numW > 0) && (numS > 0)) {
            System.out.println("All competencies completed");
        }
        else if ((numQ > 0) || (numW > 0) || (numS > 0)) {
            System.out.println("Competencies Partially Completed");
            if (numQ == 0) {
                statusQ = "incompleted";
            }
            if (numW == 0) {
                statusW = "incompleted";
            }
            if (numS == 0) {
                statusS = "incompleted";
            }
            System.out.println("Q:" + statusQ);
            System.out.println("W:" + statusW);
            System.out.println("S:" + statusS);
        }
        else 
        {
            System.out.println("No Competencies Completed");
        }
    }
    
    public void fullReport() 
    {
        System.out.println("<----- FULL REPORT ----->");
        summaryReport();
        distAreaReport();
        compReport();
    }
    
    public void sortListGPA()
    {
        System.out.println("<*** Course History Sorted By GPA (Higher to Lower) ***>");
        ArrayList<CompletedCourse> sortedCL = new ArrayList<CompletedCourse>();
        for (int i = 0; i < courseList.size(); i+= 1) {
            sortedCL.add(courseList.get(i));
        }
        int maxIndex;
        for (int i = 0; i < sortedCL.size(); i += 1)
        {
            maxIndex = i;
            for (int j = i + 1; j < sortedCL.size(); j += 1) {
                if (sortedCL.get(j).getGrade() > sortedCL.get(maxIndex).getGrade())
                {
                    maxIndex = j;
                }
            }
            CompletedCourse crs = sortedCL.get(i);
            sortedCL.set(i, sortedCL.get(maxIndex));
            sortedCL.set(maxIndex, crs);
            
            System.out.println(sortedCL.get(i).getDepartment());
            System.out.println(sortedCL.get(i).getCourseNumber());
            System.out.println(sortedCL.get(i).getSemesterTaken());
            System.out.println(sortedCL.get(i).getCompetency());
            System.out.println(sortedCL.get(i).getCredit());
            System.out.println(sortedCL.get(i).getGrade());
            System.out.println(sortedCL.get(i).getDistArea());
        }
    }
}












