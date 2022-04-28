package notification_proxy_implementation;

import component.UniversityInterface;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * attempt to implement command design pattern using DelayTimerClass
 * which is implementing announcement cancelling logic using THREADS concept.
 */
class CommandUndoClass {
    Scanner sc = new Scanner(System.in);

    public CommandUndoClass() {
    }

    public String runCommandOfCancellation() {
        long start = System.currentTimeMillis();
        long end = start + 3 * 1000;
        String ch = sc.next();
        if ("Y".equalsIgnoreCase(ch)) {
            System.out.println("Cancelling the announcement in progress. Please Wait!!!!");
            return ch;
        }
        System.out.println("...................Please wait until we process your request.................................");
        //delay times for 3 sec again
        start = System.currentTimeMillis();
        end = start + 3 * 1000;
        while (System.currentTimeMillis() < end) {
            // delay
        }
        System.out.println();
        System.out.println("Can you confirm again do you want to cancel the announcement??(Y/N)");
        ch = sc.next();
        if ("Y".equalsIgnoreCase(ch)) {
            System.out.println(".................Cancelling the announcement in progress. Please Wait!!!!.................");
            start = System.currentTimeMillis();
            end = start + 2 * 1000;
            while (System.currentTimeMillis() < end) {
                // delaying
            }
            return ch;
        }

        if (!"Y".equalsIgnoreCase(ch))
            System.out.println("Time's up to cancel the announcement! Now the message will be delieverd");

        return ch;
    }



}


/***
 * Implementation of Proxy design pattern
 */
public class NotificationProxyImpl implements NotificationProxyInterface {
    NotificationProxyInterface notificationProxyInterface;
    Scanner sc = new Scanner(System.in);

    @Override
    public void sendNotificationToObservers(UniversityInterface uvI, String msg, String senderName, List<String> notificationLevel) throws InterruptedException {
        //implement delay

        System.out.println("There will be delay timer of 10 sec to process the message. In the mean time do you want to cancel the announcement enter your choice when prompt appears");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Enter Y to cancel the announcement or Enter N if you don't want to cancel");
        String ch = new CommandUndoClass().runCommandOfCancellation();
        //after delay
        if (!"Y".equalsIgnoreCase(ch)) {
            notificationProxyInterface = new NotificationImpl();
            notificationProxyInterface.sendNotificationToObservers(uvI, msg, senderName, notificationLevel);
        }
    }

    @Override
    public void sendEmergencyNotificationToObservers(UniversityInterface uvI, String msg, String senderName, List<String> notificationLevel) {
        //no delay here
        notificationProxyInterface = new NotificationImpl();
        notificationProxyInterface.sendEmergencyNotificationToObservers(uvI, msg, senderName, notificationLevel);
    }

}
