package com.ghanshyam.seeya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {


    EditText userID;
    //    String userID1 = userid.toString();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userID = findViewById(R.id.editText);
        button = findViewById(R.id.getStarted);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = MainActivity.this.userID.getText().toString().trim();
                if (userId.isEmpty()) {
                    return;
                }
                startCall(userId);
                Intent i = new Intent(getApplicationContext(), CallActivity.class);
                i.putExtra("userId", userId);
                startActivity(i);
            }
        });
    }

    void startCall(String userID) {
        Application application = getApplication(); // Android's application context
        long appID = 00000000000;   //YOUR AppID
        String appSign = "--YOUR APP SIGN--";
        String userName = userID.toString();   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName, callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}