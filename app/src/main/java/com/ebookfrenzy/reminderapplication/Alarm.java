package com.ebookfrenzy.reminderapplication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alarms")
public class Alarm {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "alarmId")
    private int id;

    @ColumnInfo(name = "alarmTitle")
    private String title;

    @ColumnInfo(name = "alarmDesc")
    private String description;

    @ColumnInfo(name = "alarmTime")
    private String time;

    /**
     * The value of notificationState will either be a 0 or 1.
     * 0 represents the user not wanting notification
     * 1 represents the user wanting notification
     */
    @ColumnInfo(name = "wantNotification")
    private String notificationState;

    public Alarm(String time, String title, String description, String notificationState){
        this.id = id;
        this.title = title;
        this.description = description;
        this.notificationState = notificationState;
        this.time = time;
    }

    public int getId(){return this.id;}

    public String getTitle(){return this.title;}

    public String getDescription(){return this.description;}

    public String getNotificationState(){return this.notificationState;}

    public String getTime(){return this.time;}

    public void setId(int id){this.id = id;}

    public void setTitle(String title){this.title = title;}

    public void setDescription(String description){this.description = description;}

    public void setNotificationState(String notificationState){this.notificationState = notificationState;}

    public void setTime(String time){this.time = time;}
}
