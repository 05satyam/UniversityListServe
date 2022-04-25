package main;

import composite.colleges.CollegeImpl;
import composite.departments.DepartmentImpl;
import data_objects_DAO.*;
import leaf.FacultyLeafClass;
import leaf.StudentLeafClass;
import user_interface.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        StudentLeafClass st = new StudentLeafClass();
        FacultyLeafClass ft = new FacultyLeafClass();
        List<StudentLeafClass> studentList = st.getStudentListFromStaticData();
        List<FacultyLeafClass> facultyData = ft.getFacultyListFromStaticData();
        DepartmentImpl csmDept1 = new DepartmentImpl("CSCI", "CSCM", facultyData, studentList);
        DepartmentImpl csmDept2 = new DepartmentImpl("ECE", "CSCM", facultyData, studentList);

        DepartmentImpl lylesDept1 = new DepartmentImpl("MATHS", "LYLES", facultyData, studentList);
        DepartmentImpl lylesDept2 = new DepartmentImpl("PHYSICS", "LYLES", facultyData, studentList);

        List<DepartmentImpl> departmentListCSCM = new ArrayList<>();
        departmentListCSCM.add(csmDept1);
        departmentListCSCM.add(csmDept2);


        List<DepartmentImpl> departmentListLYLES = new ArrayList<>();
        departmentListLYLES.add(lylesDept1);
        departmentListLYLES.add(lylesDept2);

        CollegeImpl c1 = new CollegeImpl("CSCM",  departmentListCSCM, University.getInstance());
        CollegeImpl c2 = new CollegeImpl("LYLES", departmentListLYLES, University.getInstance());

        List<CollegeImpl> collegeList = new ArrayList<>();
        collegeList.add(c1);
        collegeList.add(c2);

        University uv = new University();
        uv.setCollegeList(collegeList);

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------WELCOME TO LIST-SERVE OF FRESNO STATE---------------");
        System.out.println("------------------------------------------------------------------");

        UI.printCurrentlyAvailableDataInUniversity(studentList,facultyData);
        String doYouWantToContinueChoice = "N";
        do {
            UI.printMainMenuForUser(collegeList, departmentListCSCM, departmentListLYLES, uv);

            System.out.println("Do you want to start over from main menu? ");
            doYouWantToContinueChoice = sc.next();
        }while ("Y".equalsIgnoreCase(doYouWantToContinueChoice));
        String ch = "N";
        do {
            System.out.println("Choose from below options :- ");
            System.out.println("1. Enter new student or Faculty data    2. Delete a student or faculty data  3. Add new Department     4. Remove Department");
            int menuChoice = sc.nextInt();
            switch (menuChoice) {
                case 1:
                    printChoiceMenuAndGetNewStudentFacultyData(studentList, facultyData);
                    break;
                case 2:
                    deleteStudentFacultyDateFromDataBase(studentList, facultyData);
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
            System.out.println("Do you want to continue with main menu(Y/N) ?");
            ch = sc.next();

        } while (ch.equalsIgnoreCase("Y"));
    }

    private static void printChoiceMenuAndGetNewStudentFacultyData(List<StudentLeafClass> studentList, List<FacultyLeafClass> facultyData) {
        Scanner sc = new Scanner(System.in);
        String ch1 = "N";
        do {
            System.out.println("Choose from below options");
            System.out.println("1. Enter Student      2. Enter faculty");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    break;
                case 2:
                    break;
                default:

            }
            System.out.println("Do you want to add more data(Y/N)?");
            ch1 = sc.nextLine();
        } while ("Y".equalsIgnoreCase(ch1));
    }

    private static void deleteStudentFacultyDateFromDataBase(List<StudentLeafClass> studentList, List<FacultyLeafClass> facultyData) {
        Scanner sc = new Scanner(System.in);
        String chForLoop = "N";
        do {
            System.out.println("Choose from below option :- ");
            System.out.println("1. Do you want to delete Student data?      2. Do you want to delete Faculty data? ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    Student.printCurrentStudentData(studentList);
                    System.out.println("Enter sequenceId you want to delete: ");
                    int seqId = sc.nextInt();
                    studentList.remove(seqId - 1);
                    break;
                case 2:
                    Faculty.printCurrentFacultyData(facultyData);
                    System.out.println("Enter sequenceId you want to delete: ");
                    seqId = sc.nextInt();
                    facultyData.remove(seqId - 1);
                    break;

            }
            System.out.println("Do you want to delete from data(Y/N)?");
            chForLoop = sc.next();
        } while ("Y".equalsIgnoreCase(chForLoop));
    }

}
