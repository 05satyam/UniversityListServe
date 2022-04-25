package component;

import java.util.List;

public interface UniversityInterface {
    void notifyObserver(String msg, String senderName, List<String> notificationLevel);

    void emergencyNotification(String msg, String senderName);
}
