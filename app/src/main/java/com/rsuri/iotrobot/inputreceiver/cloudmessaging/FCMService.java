package com.rsuri.iotrobot.inputreceiver.cloudmessaging;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.rsuri.iotrobot.command.Command;
import com.rsuri.iotrobot.command.CommandInterpreter;

public class FCMService extends FirebaseMessagingService {

    private static final String TAG = FCMService.class.getName();

    private static final String CMD_IDENTIFIER = "CMD";
    private static final String CMD_START = "START";
    private static final String CMD_STOP = "STOP";
    private static final String CMD_MOVE_FORWARD = "FWD";
    private static final String CMD_MOVE_BACKWARD = "BCK";
    private static final String CMD_TURN_LEFT = "LEFT";
    private static final String CMD_TURN_RIGHT = "RIGHT";

    private static CommandInterpreter mCommandInterpreter;

    public static void setCommandInterpreter(CommandInterpreter interpreter) {
        mCommandInterpreter = interpreter;
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0 &&
                remoteMessage.getData().get(CMD_IDENTIFIER) != null) {
            executeCommand(remoteMessage.getData().get(CMD_IDENTIFIER));
        }
    }

    private void executeCommand(String command) {
        switch (command) {
            case CMD_START:
                mCommandInterpreter.onCommandReceived(Command.CMD_START);
                break;
            case CMD_STOP:
                mCommandInterpreter.onCommandReceived(Command.CMD_STOP);
                break;
            case CMD_MOVE_BACKWARD:
                mCommandInterpreter.onCommandReceived(Command.CMD_MOVE_BACKWARD);
                break;
            case CMD_MOVE_FORWARD:
                mCommandInterpreter.onCommandReceived(Command.CMD_MOVE_FORWARD);
                break;
            case CMD_TURN_LEFT:
                mCommandInterpreter.onCommandReceived(Command.CMD_TURN_LEFT);
                break;
            case CMD_TURN_RIGHT:
                mCommandInterpreter.onCommandReceived(Command.CMD_TURN_RIGHT);
                break;
        }
    }
}
