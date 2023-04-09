package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    private Queue<Mail> inbox;
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    private Queue<Mail> trash;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inbox = new LinkedList<>();
        trash = new LinkedList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        if(this.inbox.size() >= this.inboxCapacity)
            this.trash.offer(this.inbox.poll());
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        this.inbox.offer(new Mail(date, sender, message));
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        int inbox_size = this.inbox.size();

        if(inbox_size == 0)
            return;

//        int rotation_count = 0;
//        // Included <= in the for loop :
//        for(int i = 0 ; i < inbox_size ; i++) {
//            if(this.inbox.peek().message.equals(message)) {
//                this.trash.offer(this.inbox.poll());
//                rotation_count = i;
//                break;
//            }
//            this.inbox.offer(this.inbox.poll());
//        }
//
//        for(int i = rotation_count ; i < inbox_size ; i++){
//            this.inbox.offer(this.inbox.poll());
//        }

        Queue <Mail> temporarySpace = new LinkedList<>();
        while(!this.inbox.isEmpty() && !this.inbox.peek().message.equals(message)){
            temporarySpace.offer(this.inbox.poll());
        }

        if(this.inbox.size() > 0){
            this.trash.offer(this.inbox.poll());
        }

        while(!this.inbox.isEmpty()){
            temporarySpace.offer(this.inbox.poll());
        }

        this.inbox = new LinkedList<>(temporarySpace);
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        if(this.inbox.size() == 0)
            return null;
        // Else, return the message of the latest mail present in the inbox
        int inbox_size = this.inbox.size();
        for(int i = 0 ; i < inbox_size - 1 ; i++) {
            this.inbox.offer(this.inbox.poll());
        }
        // Resetting the order of the message by setting the last message towards the end :
        String message = this.inbox.peek().message;
        this.inbox.offer(this.inbox.poll());
        return message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        if(this.inbox.size() == 0)
            return null;
        // Else, return the message of the oldest mail present in the inbox
        return this.inbox.peek().message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        int resultant_mails = 0;
        int inbox_size = this.inbox.size();

        if(inbox_size == 0)
                return 0;

        for(int i = 0 ; i < inbox_size ; i++) {
            assert this.inbox.peek() != null;
            //It is guaranteed that start date <= end date
            if(this.inbox.peek().date.equals(start) || (this.inbox.peek().date.after(start) && this.inbox.peek().date.before(end))
                    || this.inbox.peek().date.equals(end))
                resultant_mails++;

            this.inbox.offer(this.inbox.poll());
        }
        return resultant_mails;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return this.inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return this.trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        this.trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
