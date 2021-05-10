package sg.edu.rp.c346.id20024313.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText mobileNumber;
    EditText NumOfPeople;
    DatePicker dp;
    TimePicker tp;
    CheckBox smokingOption;
    Button confirmation;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.Name);
        EditText mobileNumber = findViewById(R.id.MobileNumber);
        EditText NumOfPeople = findViewById(R.id.NumOfPeople);
        DatePicker dp = findViewById(R.id.datePicker);
        TimePicker tp = findViewById(R.id.timePicker);
        CheckBox smokingOption = findViewById(R.id.smokingOption);
        Button confirmation = findViewById(R.id.confirmation);
        Button reset = findViewById(R.id.reset);
        TextView FinalOutput = findViewById(R.id.finalOutput);
        tp.setCurrentHour(7);
        tp.setCurrentMinute(30);
        Calendar c = Calendar.getInstance();
        dp.setMinDate(c.getTimeInMillis());

        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().length() != 0
                        && mobileNumber.getText().toString().trim().length() != 0
                        && NumOfPeople.getText().toString().trim().length() != 0) {
                    if (Integer.parseInt(NumOfPeople.getText().toString().trim()) > 0 &&
                            Integer.parseInt(NumOfPeople.getText().toString().trim()) < 6) {
                        String nameNumPeople = "";

                        if (smokingOption.isChecked()) {
                            nameNumPeople = String.format("Name: %s \nMobile Number: %s \nNumber of People: %s\n" +
                                            "Smoking Area \nDate: %s/%s/%s \nTime: %s:%s",name.getText()
                                    ,mobileNumber.getText(),NumOfPeople.getText(),dp.getDayOfMonth(),dp.getMonth()
                                    ,dp.getYear(),tp.getCurrentHour(), tp.getCurrentMinute());
                        }
                        else {
                            nameNumPeople = String.format("Name: %s \nMobile Number: %s \nNumber of People: %s \n" +
                                            "Non-Smoking Area \nDate: %s/%s/%s \nTime: %s:%02d",name.getText()
                                    ,mobileNumber.getText(),NumOfPeople.getText(),dp.getDayOfMonth(),dp.getMonth()
                                    ,dp.getYear(),tp.getCurrentHour(), tp.getCurrentMinute());
                        }

                        FinalOutput.setText(nameNumPeople);
                    } else {
                        FinalOutput.setText("Enter Valid Number of People (Max 5)");
                    }
                } else {
                    FinalOutput.setText("A field cannot be left empty!");
                }

            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (tp.getCurrentHour() > 20) {
                    tp.setCurrentHour(20);
                }
                else if (tp.getCurrentHour() < 8) {
                    tp.setCurrentHour(8);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                mobileNumber.setText("");
                NumOfPeople.setText("");
                smokingOption.setText("");
                dp.updateDate(2021, 6,1);
                tp.setCurrentHour(7);
                tp.setCurrentMinute(30);
                FinalOutput.setText("");
            }
        });

    }

}