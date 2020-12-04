package com.ebookfrenzy.reminderapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class AlarmRepository {
    private MutableLiveData<List<Alarm>> alarmDetails =
            new MutableLiveData<>();
    public LiveData<List<Alarm>> allAlarms;
    private AlarmDao alarmDao;

    public LiveData<List<Alarm>> getAllAlarms(){return allAlarms;}

    public MutableLiveData<List<Alarm>> getAlarmDetails() {return alarmDetails;}

    public AlarmRepository(Application application){
        AlarmRoomDatabase db;
        db = AlarmRoomDatabase.getDatabase(application);
        alarmDao = db.alarmDao();
        allAlarms = alarmDao.getAllAlarms();
    }

    public void insertAlarm(Alarm newAlarm){
        InsertAsyncTask task = new InsertAsyncTask(alarmDao);
        task.execute(newAlarm);
    }

    public void deleteAlarm(String title){
        QueryAsyncTask task = new QueryAsyncTask(alarmDao);
        task.execute(title);
    }

    public void findAlarm(String title){
        QueryAsyncTask task = new QueryAsyncTask(alarmDao);
        task.delegate = this;
        task.execute(title);
    }

    private void asyncFinished(List<Alarm> details){
        alarmDetails.setValue(details);
    }

    private static class QueryAsyncTask extends
            AsyncTask<String, Void, List<Alarm>>{
        private AlarmDao asyncTaskDao;
        private AlarmRepository delegate = null;

        QueryAsyncTask(AlarmDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected List<Alarm> doInBackground(final String... params){
            return asyncTaskDao.findAlarms(params[0]);
        }

        @Override
        protected void onPostExecute(List<Alarm> result){
            delegate.asyncFinished(result);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Alarm, Void, Void>{
        private AlarmDao asyncTaskDao;

        InsertAsyncTask(AlarmDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Alarm... params){
            asyncTaskDao.insertAlarm(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<String, Void, Void>{
        private AlarmDao asyncTaskDao;

        DeleteAsyncTask(AlarmDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params){
            asyncTaskDao.deleteAlarm(params[0]);
            return null;
        }
    }
}
