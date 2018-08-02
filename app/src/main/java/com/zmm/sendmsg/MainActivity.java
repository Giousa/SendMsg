package com.zmm.sendmsg;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        Button send = (Button) findViewById(R.id.btn_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });
    }

    private void sendMsg() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(context); //Need to change the build to API 19
            Intent sendIntent = new Intent(Intent.ACTION_SEND, Uri.parse("smsto:"));
            sendIntent.putExtra("sms_body", "短信内容输入");
            sendIntent.setType("text/plain");
            if (defaultSmsPackageName != null){
                sendIntent.setPackage(defaultSmsPackageName);
            }
            context.startActivity(sendIntent);
        } else {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:"));
            sendIntent.putExtra("sms_body", "短信内容输入");
            sendIntent.setType("vnd.android-dir/mms-sms");
            context.startActivity(sendIntent);
        }

    }
}
