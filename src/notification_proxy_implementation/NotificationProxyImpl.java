package notification_proxy_implementation;

import component.UniversityInterface;

import java.util.*;

/**
 * attempt to implement command design pattern using DelayTimerClass
 * which is implementing announcement cancelling logic using THREADS concept.
 *
 */
class DelayTimerClass {
    Scanner sc = new Scanner(System.in);
    Timer timer;

    public DelayTimerClass(int seconds) {
        timer = new Timer();
        timer.schedule(new DelayTask(), seconds*1000);
    }

    class DelayTask extends TimerTask {
        public void run() {
            String ch = sc.next();
            if("Y".equalsIgnoreCase(ch)){
                System.out.println("The announcement will be cancelled");
                timer.cancel();
                System.exit(0);
            }
            System.out.println("Time's up to cancel the announcement! Now the message will be delieverd");
             //Terminate the timer thread
        }
    }

}


/***
 * Implementation of Proxy design pattern
 */
public class NotificationProxyImpl implements NotificationProxyInterface{
    NotificationProxyInterface notificationProxyInterface;
    Scanner sc = new Scanner(System.in);
    @Override
    public void sendNotificationToObservers(UniversityInterface uvI, String msg, String senderName, List<String> notificationLevel) {
       //implement delay

        System.out.println("There will be delay timer of 10 sec to process the message. In the mean time do you want to cancel the announcement");
        System.out.println("Enter Y to cancel the announcement or Enter N if you don't want to cancel");
        new DelayTimerClass(10);
        //after delay
        notificationProxyInterface = new NotificationImpl();
        notificationProxyInterface.sendNotificationToObservers(uvI, msg, senderName, notificationLevel);
    }

    @Override
    public void sendEmergencyNotificationToObservers(UniversityInterface uvI, String msg, String senderName, List<String> notificationLevel) {
        //no delay here
        notificationProxyInterface = new NotificationImpl();
        notificationProxyInterface.sendNotificationToObservers(uvI, msg, senderName, notificationLevel);
    }

}
