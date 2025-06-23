package com.example.callapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {

    EditText userIdEditText;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userIdEditText = findViewById(R.id.user_id_edit_text);
        startBtn = findViewById(R.id.start_btn);
        startBtn.setOnClickListener((v)->{
            String userID = userIdEditText.getText().toString().trim();
            if(userID.isEmpty()){
                Toast.makeText(this, "Please Enter an ID", Toast.LENGTH_SHORT).show();
                return;
            }
            startService(userID);
            Intent intent = new Intent(MainActivity.this, CallActivity.class);
            intent.putExtra("userID",userID);
            startActivity(intent);
        });
    }
    void startService(String userID){
            Application application = getApplication();
            long appID = YOUR_APP_ID; // Replace with your actual App ID;
            String appSign = YOUR_APP_ID_SIGN; // Replace with your actual App Sign;
            String userName = userID;

            ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

            ZegoUIKitPrebuiltCallService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallService.unInit();
    }
}