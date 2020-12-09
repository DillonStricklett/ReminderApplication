package com.ebookfrenzy.reminderapplication;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class AddOrEditReminder extends AppCompatActivity {

    private EditText reminderName;
    private EditText reminderDescription;
    private EditText reminderTime;
    private CheckBox wantNotification;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_or_edit_reminder);
        reminderName = findViewById(R.id.reminder_name_text);
        reminderDescription = findViewById(R.id.reminder_description);
        reminderTime = findViewById(R.id.reminder_time);
        wantNotification = findViewById(R.id.enable_notification_checkbox);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    public void saveReminderClicked(View view){
        /**
         * The value of notificationState will either be a 0 or 1.
         * 0 represents the user not wanting notification
         * 1 represents the user wanting notification
         */
        String time = reminderTime.getText().toString();
        String name = reminderName.getText().toString();
        String description = reminderDescription.getText().toString();
        String notification = "0";
        if(wantNotification.isChecked()){
            notification = "1";
        }
        if(name == null){
            name = "Alarm";
        }
        if(description == null){
            description = "";
        }
        if (time == null || time == ""){
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            String message = "Time is required";
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }
        if(time != null || time != ""){
            Alarm createdAlarm = new Alarm(time, name, description, notification);
            mViewModel.insertAlarm(createdAlarm);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
