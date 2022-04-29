/*
* Main class to start the project
*
*
* */
package main;

import component.UniversityInterfaceImpl;
import composite.colleges.CollegeImpl;
import composite.departments.DepartmentImpl;
import data_objects_DAO.*;
import leaf.FacultyLeafClass;
import leaf.StudentLeafClass;
import user_interface.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/***
 *-----------------------------------------SMALL DOCUMENTATION OF THE FLOW-------------------------------------------------
 * Setting some basic data set
 *
 * reading json file for students and faculty data and adding same student and faculty data to alll department.
 *
 * 1. Reading student and faculty data from json file by calling "getStudentListFromStaticData() and getStudentListFromStaticData()"
 * We can add more faculty and students in json and code will add itself
 *
 * JSON files to read faculty and student data are: facultyDataList.json, studentDataList.json
 *
 *------------------------------------------------------------------------------------------------------------------------
 * 2. Creating 2 colleges : CSCM, LYLES
 *      2.1 CSCM : Created 2 department in CSCM college
 *         2.1.1 : Creating CSCI department in CSCM college and assigning faculty and student data
 *         2.1.2 : Creating ECE  department in CSCM college and assigning faculty and student data
 *
 *      2.2 LYLES : Created 2 department in LYLES college
 *         2.2.1  : Creating Maths department in LYLES college and assigning faculty and student data
 *         2.2.2  : Creating Physics department in LYLES college and assigning faculty and student data
 *
 * ------------------------------------------------------------------------------------------------------------------------
 * 3. Created University object where CSCM and LYLES are stored.
 *
 * Some Abbreviations:
 * Fac: Faculty, Stu: Student, dept: department,
 * Impl: Implementation , clg: college
 *
 *
 * Below is the tree structure of design
 *
 *                        University(Fresno State)                  ----> can send alert and notification to everyone
 *                        /                    \
 *                      /                       \
 *                   CSCM(College)              LYLES(COLLEGE)     ----->  can send notification to all or departments
 *                  /           \                /        \
 *                 /             \              /          \
 *              CSCI(dept)        ECE(dept)    Maths(dept)  Physics(dept)  -----> can send notification to both fac and stu or either fac or stu
 *              /      \           /     \        /    \       /   \
 *             /        \         /       \      /      \     /     \
 *          Stu        Fac      Stu     Fac    Stu    Fac   Stu     Fac
 *
 *
 * ------------------------------------------------------------------------------------------------------------------------
 *
 *
 * 3. For announcement alerts i have tried to create following scenarios and many more can be easily integrated in future:
 *    3.1 Do you want to send Emergency alert on behalf of Fresno State to everyone?
 *    3.2 Do you want to send normal announcement on behalf of Fresno State to everyone?
 *    3.3 Do you want to send an announcement on behalf of department to students and faculty?
 *    3.4 Do you want to send an announcement on behalf of department to faculty only
 *    3.5 Do you want to send an announcement on behalf of department to students only
 *
 *    -> after selecting from choices, we are redirecting to Proxy design class (NotificationImpl).
 *        Here, I have used PROXY design pattern to delay the message. Until a delay of 10sec we do not send announcement.
 *        When delay is over notification is sent to observers by calling University send notification implementation.
 *
 *        Also, COMMAND pattern has been used inside NotificationProxyImpl. Here, I am asking user to take action if user
 *        wants to cancel the notification. If user selects yes in 10sec than notification wont be sent and user is redirected to main men
 *
 *  NOTE: Emergency notification service has been implemented where FRESNO STATE can send emergency alerts to everyone with no
 *  intruption or delay.
 *--------------------------------------------------------------------------------------------------------------------------
 *
 * 4. Main Menu from which user can take action looks as follows:
 *    4.1 Create new department
 *    4.2 Enter new Student data
 *    4.3 Enter new Faculty Data
 *    4.4 Remove a Department
 *    4.5 Remove Faculty Data
 *    4.6 Remove Student Data
 *    4.7 View all Faculty data
 *    4.8 View all students data
 *    4.9 View All available departments
 *    4.10 Send Notification
 *
 *------------------------------------------------------------------------------------------------------------------------
 *  DESIGN Patterns and SOLIDS used:
 *
 *  5. In future if required we can make console input for colleges and university details too to make it more dynamic.
 *  6. SINGLETON pattern is used in University(Fresno State)
 *  7. COMPOSITE design pattern is used to make tree hierarchy structure as shown above in tree diagram.
 *  8. PROXY design pattern is used to implement delay in sending announcements
 *  9. COMMAND design pattern is used to take user action to cancle the announcement untill announcement is sent.
 *  10. FACTORY design pattern has been followed to build core of project
 *
 *  11. Every class have single responsibilty like notification is used to configure notification, main to take input, etc
 *  12. Liskov substitution is used along with following LAW of Demiter.
 *  13. This project follows open for extension with very minimum changes.
 *  14. Interface segeration has been used to implement single responsibility easily.
 *
 *  ----------------------------------------------------------------------------------------------------------------------
 *  15. Exception handling has been done to omit any unexpected failure
 *
 *  16. Main class is the entry point of project
 *
 *  17. Created data_objects_dao classes for Student, Faculty, College, Department, University so that some basic properties
 *      of each object can be stored easily.
 *
 * ------------------------------------------------------------------------------------------------------------------------
 * User Interface:
 * 18. UI class in package user_interface has been created to consolidate user inputs and proper console display in one class. If in future we
 * needs to implement from UI then we can use UI class as reference.
 *
 */

public class Main {

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        StudentLeafClass st = new StudentLeafClass();
        FacultyLeafClass ft = new FacultyLeafClass();

        List<StudentLeafClass> studentList = st.getStudentListFromStaticData();
        List<FacultyLeafClass> facultyData = ft.getFacultyListFromStaticData();

        /**
         *  creating CSCM college departments
         */

        DepartmentImpl csmClgDept1 = new DepartmentImpl("CSCI", "CSCM", facultyData, studentList);
        DepartmentImpl csmCLgDept2 = new DepartmentImpl("ECE", "CSCM", facultyData, studentList);

        /**
         *  creating LYLES college departments
         */
        DepartmentImpl lylesClgDept1 = new DepartmentImpl("MATHS", "LYLES", facultyData, studentList);
        DepartmentImpl lylesClgDept2 = new DepartmentImpl("PHYSICS", "LYLES", facultyData, studentList);


        /**
         *  creating department list in CSCM clg
         */
        List<DepartmentImpl> departmentListCSCM = new ArrayList<>();
        departmentListCSCM.add(csmClgDept1);
        departmentListCSCM.add(csmClgDept1);


        /**
         *  creating department list in LYLES clg
         */
        List<DepartmentImpl> departmentListLYLES = new ArrayList<>();
        departmentListLYLES.add(lylesClgDept1);
        departmentListLYLES.add(lylesClgDept2);

        CollegeImpl c1 = new CollegeImpl("CSCM",  departmentListCSCM, University.getInstance());
        CollegeImpl c2 = new CollegeImpl("LYLES", departmentListLYLES, University.getInstance());


        List<CollegeImpl> collegeList = new ArrayList<>();
        collegeList.add(c1);
        collegeList.add(c2);

        /***
         * implemented singelton pattern in UniversityInterfaceImpl and adding CSCM AND LYLES clg to UNIVERSITY
         */
        UniversityInterfaceImpl uv = UniversityInterfaceImpl.getInstance();
        uv.setCollegeList(collegeList);
        uv.setUniversityName("FRESNO STATE");

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("----------------------WELCOME TO LIST-SERVE OF FRESNO STATE----------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------");
        try {
            /**
             * UI is my User Interface class where all user inputs and menu has been displayed
             *
             */
            UI.printCurrentlyAvailableDataInUniversity(studentList, facultyData);
            String doYouWantToContinueChoice = "N";
            do {
                UI.printMainMenuForUser(collegeList, departmentListCSCM, departmentListLYLES, uv);

                System.out.println("You are in the main menu.Do you want to start over from main menu(Y/N)? ");

                doYouWantToContinueChoice = sc.next();
            } while ("Y".equalsIgnoreCase(doYouWantToContinueChoice));

            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("-----------------------Exiting from APPLICATION------------------------------------------");
            System.out.println("---------------------Thank you for using LIST-SERVE--------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
        }catch(Exception e){
            System.out.println("stack trace "+ e.getMessage());
            for(int i=0;i<e.getStackTrace().length;i++){
                System.out.println("stack trace "+ e.getStackTrace()[i]);
            }
            System.out.println("UNEXPECTED FAILURE OCCUR!!!! PLEASE TRY AGAIN FROM START");
        }
    }


}
