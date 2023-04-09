package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public ArrayList<Meeting> getCalendar() {
        return calendar;
    }

    public void setCalendar(ArrayList<Meeting> calendar) {
        this.calendar = calendar;
    }

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        // Included <Meeting> :
        setCalendar(new ArrayList<Meeting>());
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        getCalendar().add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        ArrayList<Meeting> meeting_schedule = new ArrayList<>(calendar);
        meeting_schedule.sort(new Comparator<>() {
            @Override
            public int compare(Meeting meet_1, Meeting meet_2) {
                if (meet_1.getEndTime().isBefore(meet_2.getEndTime()))
                    return -1;
                return 1;
            }
        });

        int maxMeet = 1;

        LocalTime meetEnd = meeting_schedule.get(0).getEndTime();

        for(int i =1; i < meeting_schedule.size(); i++){
            if(meeting_schedule.get(i).getStartTime().isAfter(meetEnd)){
                maxMeet++;
                meetEnd = meeting_schedule.get(i).getEndTime();
            }
        }
        return  maxMeet;
    }
}
