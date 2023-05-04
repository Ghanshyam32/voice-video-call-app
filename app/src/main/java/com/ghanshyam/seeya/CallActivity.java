package com.ghanshyam.seeya;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {

    EditText userID;
    TextView textView;
    ZegoSendCallInvitationButton voiceCall, videoCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        userID = findViewById(R.id.editText);
        textView = findViewById(R.id.hey_user_text_view);
        voiceCall = findViewById(R.id.voiceCall);
        videoCall = findViewById(R.id.videoCall);


        String userId = getIntent().getStringExtra("userId");
        textView.setText("Hey " + userId);

        userID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String targetUserId = userID.getText().toString().trim();
                setVideoCall(targetUserId);
                setVoiceCall(targetUserId);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    void setVoiceCall(String targetUserId) {
        voiceCall.setIsVideoCall(false);
        voiceCall.setResourceID("zego_uikit_call");
        voiceCall.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserId)));
    }

    void setVideoCall(String targetUserID) {

        videoCall.setIsVideoCall(true);
        videoCall.setResourceID("zego_uikit_call");
        videoCall.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }
}