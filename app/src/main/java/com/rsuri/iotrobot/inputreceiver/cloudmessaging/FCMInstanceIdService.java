package com.rsuri.iotrobot.inputreceiver.cloudmessaging;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = FCMInstanceIdService.class.getName();
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.w(TAG, "token refreshed: "+ refreshedToken);
    }
}
