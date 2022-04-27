package user_interface;

import composite.colleges.CollegeImpl;
import composite.departments.DepartmentImpl;
import data_objects_DAO.Student;
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
                /***
                 *
                 * adding departments into colleges : CSCM OR LYLES
                 * taking input from user : department name and college choice and adding new department
                 */
                System.out.println("-----You choose to create a new Department-----");
                String deptName = sc.next();
                System.out.println("Choose College Name : 1. CSCM ---- 2. LYLES");
                int clgChoice = sc.nextInt();
                DepartmentImpl department = null;
                if (clgChoice == 1) {
                    department = new DepartmentImpl(deptName, "CSCM", new ArrayList<>(), new ArrayList<>());
                    collegeList.get(0).addDepartment(department);
                } else {
                    department = new DepartmentImpl(deptName, "LYLES", new ArrayList<>(), new ArrayList<>());
                    collegeList.get(1).addDepartment(department);
                }
                break;
            case 2:
                /***
                 * Here, we are creating new student and adding that student to a department
                 * We have given run time choice to user to choose from all the avaialbe departments and add student to
                 * that particular department.
                 */
                System.out.println("-----You choose to create a new Student-----");


                /***
                 * displaying all the departments available to the user
                 */
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES);

                System.out.println();
                System.out.println("Choose the department from above list in which students will be created");
                int deptCh = sc.nextInt();
                System.out.println("Now enter student details ");
                System.out.println("Enter student name");
                String name = sc.next();
                System.out.println("Enter student id ");
                String id = sc.next();
                System.out.println("Enter student year of admission ");
                String yearAdm = sc.next();
                int deptNum = 0;
                DepartmentImpl choosenDept = getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);

                StudentLeafClass st = new StudentLeafClass(name, id, choosenDept.getDepartmentName(), yearAdm);
                choosenDept.addStudent(st);
                break;
            case 3:
                /***
                 * Here, we are creating new faculty and adding that faculty to a department
                 * We have given run time choice to user to choose from all the avaialbe departments and add faculty to
                 * that particular department.
                 */
                System.out.println("-----You choose to create a new Faculty-----");



                /***
                 * displaying all the departments available to the user
                 */
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES);
                System.out.println();
                System.out.println("Choose the department fromm above list in which students will be created");
                deptCh = sc.nextInt();
                System.out.println("Now enter Faculty details ");
                System.out.println("Enter faculty name");
                name = sc.next();
                System.out.println("Enter faculty id ");
                id = sc.next();
                System.out.println("Enter student year of joining ");
                yearAdm = sc.next();
                deptNum = 0;
                choosenDept = getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);

                FacultyLeafClass ft = new FacultyLeafClass(name, choosenDept.getDepartmentName(), id, yearAdm);
                choosenDept.addFaculty(ft);
                break;
            case 4:
                System.out.println("You choose to remove a department. WARNING!! removing a department means" +
                        "you will be removing all faculty and students from that department");
                /***
                 * displaying all the departments available to the user
                 */

                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES);
                System.out.println();
                System.out.println("Enter the sequence number of department from above list which you want to remove");
                deptCh = sc.nextInt();
                System.out.println("WARNING!! We are going to remove a complete department.");
                choosenDept = getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);
                List<StudentLeafClass> deptSt = choosenDept.getStudentList();
                choosenDept.getStudentList().removeAll(deptSt);
                List<FacultyLeafClass> deptFt = choosenDept.getFacultyList();
                choosenDept.getStudentList().removeAll(deptFt);
                collegeList.remove(choosenDept);
                if("CSCM".equalsIgnoreCase(choosenDept.getCollegeName()))
                    departmentListCSCM.remove(choosenDept);
                else if("LYLES".equalsIgnoreCase(choosenDept.getCollegeName()))
                    departmentListLYLES.remove(choosenDept);

                System.out.println("WARNING!! We have successfully removed "+ choosenDept.getDepartmentName() +
                        " from college "+ choosenDept.getCollegeName());
                break;
            case 5:
                System.out.println("You have chose to remove a faculty");

                /***
                 * displaying all the departments available to the user
                 */
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES);
                System.out.println();
                System.out.println("Choose the department from above list from which you want to remove faculty");
                deptCh = sc.nextInt();
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("Faculties available in choosen department are: " );
                choosenDept =  getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);
                int seqId=1;
                for(FacultyLeafClass depFt: choosenDept.getFacultyList()){
                    System.out.println("Seq id: "+ seqId + "  Faculty id: "+ depFt.getFacultyId() +
                            " Faculty name: "+ depFt.getFacultyName() + " Faculty department: "+ depFt.getFacultyName());


                }
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("Enter the Seq id of the faculty you want to delete");
                int facSeqId = sc.nextInt();
                choosenDept.removeFaculty(facSeqId);
                break;
            case 6:
                System.out.println("You have chose to remove a student");

                /***
                 * displaying all the departments available to the user
                 */
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES);
                System.out.println();
                System.out.println("Choose the department from above list from which you want to remove student");
                deptCh = sc.nextInt();
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("Faculties available in choosen department are: " );
                choosenDept =  getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);
                 seqId=1;
                for(StudentLeafClass depFt: choosenDept.getStudentList()){
                    System.out.println("Seq id: "+ seqId + "  Student id: "+ depFt.getStudentId() +
                            " Student name: "+ depFt.getStudentName() + " Student department: "+ depFt.getDepartmentName());


                }
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("Enter the Seq id of the student you want to delete");
                facSeqId = sc.nextInt();
                choosenDept.removeStudent(facSeqId);
                break;
            case 7:
                System.out.println("You have choosen to send notification");
                System.out.println("We have following ways to send notifications. Kindly enter your choice");
                System.out.println("1. Do you want to send Emergency alert on behalf of Fresno State to everyone?");
                System.out.println("2. Do you want to send normal announcement on behalf of Fresno State to everyone?");
                System.out.println("3. Do you want to send an announcement on behalf of department to students and faculty?");

            default:
                break;
        }

    }

    private static DepartmentImpl getDepartmentWhichUserSelects(List<DepartmentImpl> departmentListCSCM, List<DepartmentImpl> departmentListLYLES, int deptCh) {
        DepartmentImpl choosenDept = null;
        if (deptCh > departmentListCSCM.size()) {
            deptCh = (departmentListCSCM.size() + departmentListLYLES.size()) - deptCh;
            choosenDept = departmentListLYLES.get(deptCh);
        } else {
            choosenDept = departmentListCSCM.get(deptCh);
        }
        return choosenDept;
    }

    private static void printAllAvailableDepartments(List<DepartmentImpl> departmentListCSCM, List<DepartmentImpl> departmentListLYLES) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Departments available are: ");
        int i;
        i = 1;
        for (DepartmentImpl dept : departmentListCSCM) {
            System.out.print(i + " " + dept.getDepartmentName() + "  ");
            i++;
        }
        for (DepartmentImpl dept : departmentListLYLES) {
            System.out.print(i + " " + dept.getDepartmentName() + "  ");
            i++;
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }
}
