package com.rsuri.iotrobot.command;

import com.rsuri.iotrobot.robot.RobotDisplacer;

public class CommandInterpreter {

    private static final String TAG = CommandInterpreter.class.getName();

    private RobotDisplacer mRobotDisplacer;

    CommandInterpreter(RobotDisplacer robotDisplacer) {
        mRobotDisplacer = robotDisplacer;
    }

    public void onCommandReceived(int command){
        mRobotDisplacer.setCommand(command);
    }
}
