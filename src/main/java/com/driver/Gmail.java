package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    Deque<mails> inbox ;
    ArrayList<mails> trash ;


    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inbox = new LinkedList<>();
        trash = new ArrayList<>();
    }
    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        if(inbox.size()==inboxCapacity){
            trash.add(inbox.removeFirst());
        }
        inbox.add(new mails(date,sender,message));
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

    }

    public void deleteMail(String message){
        Iterator<mails> it = inbox.iterator();


        while (it.hasNext()) {
            mails obj = it.next();
            if(obj.getMessage().equals(message))
            {
                trash.add(obj);
                it.remove();
                break;
            }
        }

        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

    }

    public String findLatestMessage(){
        if(inbox.size()==0){
            return null;
        }
        return inbox.peekLast().getMessage();
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

    }

    public String findOldestMessage(){
        if(inbox.size()==0){
            return null;
        }
        return inbox.peek().getMessage();
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

    }

    public int findMailsBetweenDates(Date start, Date end){
        Iterator<mails> it = inbox.iterator();
        int count=0;
        while (it.hasNext()) {
            mails obj=it.next();
//           if(obj.date.after(start) && obj.date.before(end))
            if(obj.date.getTime()>=start.getTime() && obj.date.getTime()<=end.getTime())
            {
                count++;
            }

        }
        return count;
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

    }

    public int getInboxSize(){
        return inbox.size();
        // Return number of mails in inbox

    }

    public int getTrashSize(){
        return trash.size();
        // Return number of mails in Trash

    }

    public void emptyTrash(){
        trash.clear();
        // clear all mails in the trash

    }

    public int getInboxCapacity() {
        return inboxCapacity;
        // Return the maximum number of mails that can be stored in the inbox
    }
}