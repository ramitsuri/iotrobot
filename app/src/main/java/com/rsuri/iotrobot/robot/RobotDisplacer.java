package com.rsuri.iotrobot.robot;

import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;
import com.rsuri.iotrobot.command.Command;

import java.util.concurrent.TimeUnit;

public class RobotDisplacer {

    private static final String TAG = RobotDisplacer.class.getName();
    private static final String LEFT_PIN1 = "BCM2";
    private static final String LEFT_PIN2 = "BCM3";
    private static final String RIGHT_PIN1 = "BCM14";
    private static final String RIGHT_PIN2 = "BCM15";

    private static final long PERIOD = 500;

    private Gpio mGPIOLeft1;
    private Gpio mGPIOLeft2;
    private Gpio mGPIORight1;
    private Gpio mGPIORight2;

    private int mCommand;
    private boolean mRun;

    RobotDisplacer() {
        PeripheralManager manager = PeripheralManager.getInstance();
        try {
            mGPIOLeft1 = manager.openGpio(LEFT_PIN1);
            mGPIOLeft2 = manager.openGpio(LEFT_PIN2);
            mGPIORight1 = manager.openGpio(RIGHT_PIN1);
            mGPIORight2 = manager.openGpio(RIGHT_PIN2);

            mGPIOLeft1.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            mGPIOLeft2.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            mGPIORight1.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            mGPIORight2.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private Thread mCommandThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while (mRun) {
                    switch (mCommand) {
                        case Command.CMD_STOP:
                            stop();
                            break;

                        case Command.CMD_MOVE_FORWARD:
                            stop();
                            goForward();
                            TimeUnit.MILLISECONDS.sleep(PERIOD);
                            mCommand = Command.CMD_STOP;
                            break;

                        case Command.CMD_MOVE_BACKWARD:
                            stop();
                            goBackward();
                            TimeUnit.MILLISECONDS.sleep(PERIOD);
                            mCommand = Command.CMD_STOP;
                            break;

                        case Command.CMD_TURN_LEFT:
                            stop();
                            turnLeft();
                            TimeUnit.MILLISECONDS.sleep(PERIOD/5);
                            mCommand = Command.CMD_STOP;
                            break;

                        case Command.CMD_TURN_RIGHT:
                            stop();
                            turnRight();
                            TimeUnit.MILLISECONDS.sleep(PERIOD/5);
                            mCommand = Command.CMD_STOP;
                            break;
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    });

    private void goForward() {
        try {
            mGPIOLeft2.setValue(true);
            mGPIORight2.setValue(true);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private void goBackward() {
        try {
            mGPIOLeft1.setValue(true);
            mGPIORight1.setValue(true);
            } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private void turnLeft() {
        try {
            mGPIOLeft1.setValue(true);
            mGPIORight2.setValue(true);
            } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private void turnRight() {
        try {
            mGPIOLeft2.setValue(true);
            mGPIORight1.setValue(true);
            } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private void stop() {
        try {
            mGPIOLeft1.setValue(false);
            mGPIOLeft2.setValue(false);
            mGPIORight1.setValue(false);
            mGPIORight2.setValue(false);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public synchronized void setCommand(int com) {
        mCommand = com;
        Log.d(TAG, String.valueOf(mRun));
        if (mCommand == Command.CMD_START) {
            mRun = true;
            mCommandThread.start();
        }else if (mCommand == Command.CMD_STOP){
            mRun = false;
        }
    }
}
