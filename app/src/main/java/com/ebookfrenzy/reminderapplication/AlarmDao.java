package com.ebookfrenzy.reminderapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlarmDao {

    @Insert
    void insertAlarm(Alarm alarm);

    @Query("SELECT * FROM alarms WHERE alarmTitle = :title")
    List<Alarm> findAlarms(String title);

    @Query("DELETE FROM alarms WHERE alarmTitle = :title")
    void deleteAlarm(String title);

    @Query("SELECT * FROM alarms")
    LiveData<List<Alarm>> getAllAlarms();
}
