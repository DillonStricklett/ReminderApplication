package com.ebookfrenzy.reminderapplication;

import android.app.Application;

import androidx.core.app.AppLaunchChecker;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private AlarmRepository repository;
    private LiveData<List<Alarm>> allAlarms;
    private MutableLiveData<List<Alarm>> alarmDetails;

    public MainViewModel (Application application){
        super(application);
        repository = new AlarmRepository(application);
        allAlarms = repository.getAllAlarms();
        alarmDetails = repository.getAlarmDetails();
    }

    MutableLiveData<List<Alarm>> getAlarmDetails(){return alarmDetails;}

    LiveData<List<Alarm>> getAllAlarms(){return allAlarms;}

    public void insertAlarm(Alarm alarm){repository.insertAlarm(alarm);}

    public void findAlarm(String title){repository.findAlarm(title);}

    public void deleteAlarm(String title){repository.deleteAlarm(title);}
}
