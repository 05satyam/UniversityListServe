package notification_proxy_implementation;

import component.UniversityInterface;
import component.UniversityInterfaceImpl;
import composite.colleges.CollegeImpl;
import composite.departments.DepartmentImpl;
import user_interface.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NotificationImpl implements NotificationProxyInterface{

    @Override
    public void sendNotificationToObservers(UniversityInterface uvI, String msg, String senderName, List<String> notificationLevel) {
        uvI.notifyObserver(msg, senderName, notificationLevel);
    }

    @Override
    public void sendEmergencyNotificationToObservers(UniversityInterface uvI, String msg, String senderName, List<String> notificationLevel) {
        uvI.emergencyNotification(msg, senderName);
    }
}
