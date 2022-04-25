package user_interface;

import composite.colleges.CollegeImpl;
import composite.departments.DepartmentImpl;
import data_objects_DAO.University;
import leaf.FacultyLeafClass;
import leaf.StudentLeafClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {



    public static void printCurrentlyAvailableDataInUniversity(List<StudentLeafClass> studentList, List<FacultyLeafClass> facultyData) {
        System.out.println("Currently we have : " + studentList.size() + " number of students and " + facultyData.size() + " number of faculties in total");
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to view current student or faculty data? If yes choose from:  1. Student data  2. Faculty data   3. No");
        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                printAvaliableStudentsData(studentList);
                break;
            case 2:
                printAvaliableFacultyData(facultyData);
                break;
            default:
                break;

        }
    }

    public static void printAvaliableStudentsData(List<StudentLeafClass> stList) {
        for (StudentLeafClass st : stList)
            st.toString();
    }

    public static void printAvaliableFacultyData(List<FacultyLeafClass> stList) {
        for (FacultyLeafClass ft : stList)
            ft.toString();
    }


    public static void printMainMenuForUser(List<CollegeImpl> collegeList, List<DepartmentImpl> departmentListCSCM, List<DepartmentImpl> departmentListLYLES, University uv) {
        Scanner sc = new Scanner(System.in);
        String doYouWantToContinueChoice = "N";
        System.out.println("Menu options are: ");
        System.out.println("1. Create new department --- 2. Enter new Student data --- 3. Enter new Faculty Data ");
        System.out.println("--- 4. Remove a Department --- 5. Remove Faculty Data --- 6. Remove Student Data --- ");
        System.out.println("7. Send Notification");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                System.out.println("-----You choose to create a new Department-----");

                break;
            case 2:
                String deptName = sc.next();
                System.out.println("Choose College Name : 1. CSCM ---- 2. LYLES");
                int clgChoice = sc.nextInt();
                DepartmentImpl department=null;
                if(clgChoice==1) {
                    department = new DepartmentImpl(deptName, "CSCM", new ArrayList<>(), new ArrayList<>());
                    collegeList.get(0).addDepartment(department);
                }else{
                    department = new DepartmentImpl(deptName, "LYLES", new ArrayList<>(), new ArrayList<>());
                    collegeList.get(1).addDepartment(department);
                }
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                break;
        }

    }
}
