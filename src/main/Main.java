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

public class Main {

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        StudentLeafClass st = new StudentLeafClass();
        FacultyLeafClass ft = new FacultyLeafClass();

        /***
         *
         * setting some basic data set
         *
         * reaading json file for students and faculty data and adding same student and faculty data to alll department.
         *
         * 1. Reading student and faculty data from json file by calling "getStudentListFromStaticData() and getStudentListFromStaticData()"
         * 2. Creating 2 colleges : CSCM, LYLES
         *      2.1 CSCM : Created 2 department in CSCM college
         *         2.1.1 : Creating CSCI department in CSCM college and assigning faculty and student data
         *         2.1.2 : Creating ECE  department in CSCM college and assigning faculty and student data
         *
         *      2.2 LYLES : Created 2 department in LYLES college
         *         2.2.1  : Creating Maths department in LYLES college and assigning faculty and student data
         *         2.2.2  : Creating Physics department in LYLES college and assigning faculty and student data
         *
         * 3. Created University object where CSCM and LYLES are stores.
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
         */
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
           // System.out.println("stack trace "+ e.getStackTrace()[0]);
            System.out.println("UNEXPECTED FAILURE OCCUR!!!! PLEASE TRY AGAIN FROM START");
        }
    }


}
