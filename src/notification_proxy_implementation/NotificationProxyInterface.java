package notification_proxy_implementation;

import component.UniversityInterface;

import java.util.List;

public interface NotificationProxyInterface {
    public void sendNotificationToObservers(UniversityInterface uvI, String msg, String senderName, List<String> notificationLevel) throws InterruptedException;

    public void sendEmergencyNotificationToObservers(UniversityInterface uvI, String msg, String senderName, List<String> notificationLevel);

}
