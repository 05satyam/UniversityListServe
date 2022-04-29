package user_interface;

import component.UniversityInterfaceImpl;
import composite.colleges.CollegeImpl;
import composite.departments.DepartmentImpl;
import leaf.FacultyLeafClass;
import leaf.StudentLeafClass;
import notification_proxy_implementation.NotificationProxyImpl;
import notification_proxy_implementation.NotificationProxyInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * THIS IS A USER INTERFACE CLASS WHERE ALL THE MENUS AND INPUTS ARE TAKEN FROM USER TO MAKE EASY FLOW OF THE APPLICATION
 *
 * METHODS ARE STATIC HERE BECAUSE THESE ARE MENU OPTIONS AND INPUTS FROM USERS WHICH WONT AFFECT THE DESIGN ON THE BACKEND.
 */

public class UI {

    /**
     *
     * @param studentList
     * @param facultyData
     *
     * this is a helper method to print all the availabe data when application loads initially.
     *
     */
    public static void printCurrentlyAvailableDataInUniversity(List<StudentLeafClass> studentList, List<FacultyLeafClass> facultyData) {
        System.out.println("Currently we have : " + studentList.size() + " number of students and " + facultyData.size() + " number of faculties in total");
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to view current student or faculty data? ");
        System.out.println("If yes choose from:  1. Student data  2. Faculty data   3. No Proceed to Main Menu");
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

    /**
     *
     * @param stList
     * this method is used to print all the available students data on UI
     */
    public static void printAvaliableStudentsData(List<StudentLeafClass> stList) {
        for (StudentLeafClass st : stList)
            System.out.println(st.toString());
    }

    /**
     *
     * @param stList
     * this method is used to print all the available faculties data on UI
     *
     */
    public static void printAvaliableFacultyData(List<FacultyLeafClass> stList) {
        for (FacultyLeafClass ft : stList)
            System.out.println(ft.toString());
    }


    /**
     *
     * @param collegeList
     * @param departmentListCSCM
     * @param departmentListLYLES
     * @param uv
     * @throws InterruptedException
     *
     * THIS IS THE MAIN UI MENU TO DISPLAY ON CONSOLE.
     */
    public static void printMainMenuForUser(List<CollegeImpl> collegeList, List<DepartmentImpl> departmentListCSCM, List<DepartmentImpl> departmentListLYLES, UniversityInterfaceImpl uv) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("...............................Department Metrics............................................");
        System.out.println("Current of CSCM departments count :" + departmentListCSCM.size());
        System.out.println("Current of LYLES departments count: " + departmentListLYLES.size());
        System.out.println(".............................................................................................");


        System.out.println("................................MAIN MENU OPTIONS............................................");
        System.out.println("1. Create new department --- 2. Enter new Student data --- 3. Enter new Faculty Data ");
        System.out.println("--- 4. Remove a Department --- 5. Remove Faculty Data --- 6. Remove Student Data --- ");
        System.out.println("7. View all Faculty data  --- 8.View all students data --- 9. View All available departments" );
        System.out.println("--- 10. Send Notification");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                /***
                 *
                 * adding departments into colleges : CSCM OR LYLES
                 * taking input from user : department name and college choice and adding new department
                 */
                System.out.println("....................................................................................");
                System.out.println("                     YOU SELECT TO CREATE NEW DEPARTMENT");
                System.out.println("....................................................................................");



                System.out.println("Please enter new department name: ");
                String deptName = sc.next();
                System.out.println("Choose College number in which you want to create department : 1. CSCM     2. LYLES");
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
                System.out.println("....................................................................................");
                System.out.println("                       YOU SELECT TO CREATE NEW STUDENT");
                System.out.println("....................................................................................");


                /***
                 * displaying all the departments available to the user
                 */
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES, uv);

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
                //System.out.println("chhosen dept in students "+ choosenDept.getDepartmentName());
                StudentLeafClass st = new StudentLeafClass(name, id, choosenDept.getDepartmentName(), yearAdm);
                choosenDept.addStudent(st);
                break;
            case 3:
                /***
                 * Here, we are creating new faculty and adding that faculty to a department
                 * We have given run time choice to user to choose from all the avaialbe departments and add faculty to
                 * that particular department.
                 */
                System.out.println("....................................................................................");
                System.out.println("                           YOU SELECT TO CREATE NEW FACULTY");
                System.out.println("....................................................................................");


                /***
                 * displaying all the departments available to the user
                 */
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES, uv);
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

                choosenDept = getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);

                FacultyLeafClass ft = new FacultyLeafClass(name, choosenDept.getDepartmentName(), id, yearAdm);
                choosenDept.addFaculty(ft);
                System.out.println("New Faculty data has been added successfully");
                break;
            case 4:

                /**
                 * here we are implemeting to remove a department.
                 * Taking user input which department to delete
                 *
                 */
                System.out.println("....................................................................................");

                System.out.println("You choose to remove a department. WARNING!! removing a department means" +
                        "you will be removing all faculty and students from that department");
                System.out.println("....................................................................................");


                /***
                 * displaying all the departments available to the user
                 */

                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES, uv);
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
                if ("CSCM".equalsIgnoreCase(choosenDept.getCollegeName()))
                    departmentListCSCM.remove(choosenDept);
                else if ("LYLES".equalsIgnoreCase(choosenDept.getCollegeName()))
                    departmentListLYLES.remove(choosenDept);

                System.out.println("WARNING!! We have successfully removed " + choosenDept.getDepartmentName() +
                        " from college " + choosenDept.getCollegeName());
                break;
            case 5:
                /**
                 * here we are implementing to remove a faculty from any department.
                 * Taking department input and then which faculty to remove
                 */
                System.out.println(".............................................................................................");
                System.out.println("                         You have chose to remove a faculty");
                System.out.println(".............................................................................................");
                /***
                 * displaying all the departments available to the user
                 */
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES, uv);
                System.out.println();
                System.out.println("Choose the department from above list from which you want to remove faculty");
                deptCh = sc.nextInt();

                System.out.println("FACULTIES available in chosen department are: ");
                choosenDept = getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);
                int seqId = 1;
                for (FacultyLeafClass depFt : choosenDept.getFacultyList()) {
                    System.out.println("Seq id: " + seqId + "  Faculty id: " + depFt.getFacultyId() +
                            " Faculty name: " + depFt.getFacultyName() + " Faculty department: " + depFt.getFacultyName());
                }
                System.out.println("Enter the Seq id of the faculty you want to delete");
                int facSeqId = sc.nextInt();
                choosenDept.removeFaculty(facSeqId);
                break;
            case 6:
                /**
                 * here we are removing student data from a department
                 */
                System.out.println(".............................................................................................");
                System.out.println("                             You have chose to remove a student");
                System.out.println(".............................................................................................");
                /***
                 * displaying all the departments available to the user
                 */
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES, uv);
                System.out.println();
                System.out.println("Choose the department from above list from which you want to remove student");
                deptCh = sc.nextInt();
                System.out.println("Faculties available in chosen department are: ");
                choosenDept = getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);
                seqId = 1;
                for (StudentLeafClass depFt : choosenDept.getStudentList()) {
                    System.out.println("Seq id: " + seqId + "  Student id: " + depFt.getStudentId() +
                            " Student name: " + depFt.getStudentName() + " Student department: " + depFt.getDepartmentName());


                }
                System.out.println("Enter the Seq id of the student you want to delete");
                facSeqId = sc.nextInt();
                choosenDept.removeStudent(facSeqId);
                break;
            case 7:
                /**
                 * this case is reponsible to display all faculties from all the departments
                 */
                System.out.println(".............................................................................................");
                System.out.println("             Displaying all Faculties from all the departments present at Fresno State");
                System.out.println(".............................................................................................");
                for (DepartmentImpl dpt : departmentListCSCM) {
                    System.out.println(".......Faculty present in the department " + dpt.getDepartmentName() + ".........");
                    printAvaliableFacultyData(dpt.getFacultyList());
                }
                for (DepartmentImpl dpt : departmentListLYLES) {
                    System.out.println(".......Students present in the department " + dpt.getDepartmentName() + "........");
                    printAvaliableFacultyData(dpt.getFacultyList());
                }
                break;
            case 8:
                /**
                 * this case handles to display all the students of all departments
                 */
                System.out.println(".............................................................................................");
                System.out.println("               Displaying all students from all the departments present at Fresno State");
                System.out.println(".............................................................................................");
                for (DepartmentImpl dpt : departmentListCSCM) {
                    System.out.println("Students present in the department " + dpt.getDepartmentName() );
                    printAvaliableStudentsData(dpt.getStudentList());
                }
                for (DepartmentImpl dpt : departmentListLYLES) {
                    System.out.println("Students present in the department " + dpt.getDepartmentName());
                    printAvaliableStudentsData(dpt.getStudentList());
                }
                break;
            case 9:
                /**
                 * this case handles to display all departmets
                 */
                System.out.println(".............................................................................................");
                System.out.println("                 Displaying all the departments present at Fresno State");
                System.out.println(".............................................................................................");
                for (DepartmentImpl dpt : departmentListCSCM) {
                    System.out.println("College name: " + dpt.getCollegeName() + " and Department name:  " + dpt.getDepartmentName());
                }
                for (DepartmentImpl dpt : departmentListLYLES) {
                    System.out.println("College name: " + dpt.getCollegeName() + " and Department name:  " + dpt.getDepartmentName());
                }
                break;
            case 10:
                /**
                 * this case is reponsible to send notifications as per requirement.
                 */
                int notificationCh = showNotificationMenuAndGetUserChoice();
                sendNotifcationToObservers(notificationCh, collegeList, departmentListCSCM, departmentListLYLES, uv);
            default:
                break;
        }

    }

    /**
     *
     * @return
     *
     * method to display UI menu for notification
     */
    public static int showNotificationMenuAndGetUserChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println(".............................................................................................");
        System.out.println("                                       SEND ANNOUNCEMENT MENU");
        System.out.println(".............................................................................................");

        System.out.println("We have following ways to send notifications. Kindly enter your choice");
        System.out.println("1. Do you want to send Emergency alert on behalf of Fresno State to everyone?");
        System.out.println("2. Do you want to send normal announcement on behalf of Fresno State to everyone?");
        System.out.println("3. Do you want to send an announcement on behalf of department to students and faculty?");
        System.out.println("4. Do you want to send an announcement on behalf of department to faculty only");
        System.out.println("5. Do you want to send an announcement on behalf of department to students only");
        int notificationCh = sc.nextInt();
        return notificationCh;
    }

    /**
     *
     * @param departmentListCSCM
     * @param departmentListLYLES
     * @param deptCh
     * @return
     *
     * this is a helper method and it is used to get department object which user wants
     */
    public static DepartmentImpl getDepartmentWhichUserSelects(List<DepartmentImpl> departmentListCSCM, List<DepartmentImpl> departmentListLYLES, int deptCh) {
        System.out.println("dept ch "+ deptCh + " departmentListCSCM.size() "+ departmentListCSCM.size());
        if (deptCh > departmentListCSCM.size()) {
            deptCh = (departmentListCSCM.size() + departmentListLYLES.size()) - (deptCh+13) ;
            return departmentListLYLES.get(deptCh);
        } else {
            return departmentListCSCM.get(deptCh-1);
        }
    }

    /**
     *
     * @param departmentListCSCM
     * @param departmentListLYLES
     * @param uv
     *
     * A helper method. This method will display all the departments detals
     */
    public static void printAllAvailableDepartments(List<DepartmentImpl> departmentListCSCM, List<DepartmentImpl> departmentListLYLES, UniversityInterfaceImpl uv) {
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
    }

    /**
     *
     * @param notificationCh
     * @param collegeList
     * @param departmentListCSCM
     * @param departmentListLYLES
     * @param uv
     * @throws InterruptedException
     *
     * This is a helper method to send notification to observer and will be calling proxy classes
     */
    public static void sendNotifcationToObservers(int notificationCh, List<CollegeImpl> collegeList,
                                                  List<DepartmentImpl> departmentListCSCM, List<DepartmentImpl> departmentListLYLES, UniversityInterfaceImpl uv) throws InterruptedException {
        NotificationProxyInterface nvI = new NotificationProxyImpl();
        Scanner sc = new Scanner(System.in);
        String announcement = null;
        List<String>  notificationLevel = new ArrayList<>();
        switch (notificationCh) {
            case 1:
                System.out.println(".............................................................................................");
                System.out.println("Send an Emergency alert on behalf of Fresno State to everyone");
                System.out.println(".............................................................................................");
                System.out.println("Please enter the alert announcement");
                announcement = sc.next();
                nvI.sendEmergencyNotificationToObservers(uv, announcement, uv.getUniversityName(), null);
                break;
            case 2:
                System.out.println(".............................................................................................");
                System.out.println("Send normal announcement on behalf of Fresno State to everyone");
                System.out.println(".............................................................................................");
                System.out.println("Enter your choice:  that you want to send notification to:  1. CSCM only   2. LYLES only   3. BOTH");
                System.out.print("");
                int chClg = sc.nextInt();

                System.out.println("Please enter the alert announcement");
                announcement = sc.next();
                System.out.println();

                if(chClg==1)
                    notificationLevel.add("CSCM");
                else if(chClg==2)
                    notificationLevel.add("LYLES");
                else
                    notificationLevel.add("00");

                nvI.sendNotificationToObservers(uv, announcement, uv.getUniversityName(), notificationLevel);
                break;
            case 3:
                System.out.println(".............................................................................................");
                System.out.println("Send normal announcement on behalf of department to students and faculty");
                System.out.println(".............................................................................................");
                System.out.println("Please enter the  announcement");
                announcement = sc.next();
                System.out.println("Please choose the department from which you want to send notification");
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES, uv);
                int deptCh = sc.nextInt();
                DepartmentImpl choosenDept = getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);
                nvI.sendNotificationToObservers(choosenDept, announcement, choosenDept.getDepartmentName(), null);
                break;
            case 4:
                System.out.println(".............................................................................................");
                System.out.println("Send normal announcement  on behalf of department to faculty only");
                System.out.println(".............................................................................................");
                System.out.println("Please enter the  announcement");
                announcement = sc.next();
                System.out.println("Please choose the department from which you want to send notification");
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES, uv);
                deptCh = sc.nextInt();
                choosenDept = getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);
                nvI.sendNotificationToObservers(choosenDept, announcement, choosenDept.getDepartmentName(), new ArrayList<>(Arrays.asList("Faculty")));
                break;
            case 5:
                System.out.println(".............................................................................................");
                System.out.println("Send normal announcement  on behalf of department to student only");
                System.out.println(".............................................................................................");
                System.out.println("Please enter the  announcement");
                announcement = sc.next();
                System.out.println("Please choose the department from which you want to send notification");
                printAllAvailableDepartments(departmentListCSCM, departmentListLYLES, uv);
                deptCh = sc.nextInt();
                choosenDept = getDepartmentWhichUserSelects(departmentListCSCM, departmentListLYLES, deptCh);
                nvI.sendNotificationToObservers(choosenDept, announcement, choosenDept.getDepartmentName(), new ArrayList<>(Arrays.asList("Student")));
                break;
            default:
                System.out.println("WRONG CHOICE");
                break;


        }

    }
}
