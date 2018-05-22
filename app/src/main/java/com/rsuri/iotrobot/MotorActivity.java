package com.rsuri.iotrobot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rsuri.iotrobot.command.CommandInterpreter;
import com.rsuri.iotrobot.inputreceiver.cloudmessaging.FCMService;
import com.rsuri.iotrobot.robot.RobotDisplacer;

public class MotorActivity extends AppCompatActivity {

    private static final String TAG = "MotorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RobotDisplacer robotDisplacer = new RobotDisplacer();
        CommandInterpreter interpreter = new CommandInterpreter(robotDisplacer);
        FCMService.setCommandInterpreter(interpreter);
    }
    public boolean isGooglePlayServicesAvailable(){
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if(status != ConnectionResult.SUCCESS) {
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(this, status, 2404).show();
            }
            return false;
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
