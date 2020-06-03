package com.example.edwardadmin.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.edwardadmin.aidl.IRemoteAidlInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AIDLMainClient";
    private static final String BIND_SERVICE_ACTION = "android.intent.action.RESPOND_AIDL_MESSAGE";

    private IRemoteAidlInterface iRemoteAidlInterface;

    private Button mConnectButton;
    private Button mAcquireButton;

    private String mUsername;
    private String mUserage;
    Context.MIDI_SERVICE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate entry!");
        initView();
    }

    private void initView() {
        mConnectButton = (Button) this.findViewById(R.id.connect);
        mAcquireButton = (Button) this.findViewById(R.id.acquire_info);
        Log.d(TAG, "initView entry!");
        mConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService();
            }
        });

        mAcquireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick be trigger!");
                Toast.makeText(getApplicationContext(), "检测到5意图。 trigger", Toast.LENGTH_LONG).show();
                if (null != iRemoteAidlInterface) {
                    try {
                        mUsername = iRemoteAidlInterface.getPersonUserName();
                        mUserage = iRemoteAidlInterface.getPersonUserAge();
                        Toast.makeText(getApplicationContext(), "检测到5意图。", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "mUsername = " + mUsername + ",mUserage = " + mUserage, Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "serviceConnection be triger! onServiceConnected entry");
            Toast.makeText(getApplicationContext(), "ServiceConnection be created successfully!", Toast.LENGTH_LONG).show();
            iRemoteAidlInterface = IRemoteAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iRemoteAidlInterface = null;
        }
    };

    private void bindService() {
        Log.d(TAG, " before bindService");
        Intent serviceIntent = new Intent();
        serviceIntent.setAction(BIND_SERVICE_ACTION);
        // 方法一
        // serviceIntent.setComponent(new ComponentName("com.example.edwardadmin.aidlserver", "com.example.edwardadmin.service.RmoteService"));
        // bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        // 方法二
        final Intent explicitIntent = new Intent(achieveExplicitFromImplicitIntent(this, serviceIntent));
        bindService(explicitIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "bindService");
    }

    private void unBindSevice() {
        unbindService(serviceConnection);
        Log.d(TAG, "unbindService");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindSevice();
    }

    public Intent achieveExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        Log.d(TAG,"packageName = " + packageName);
        Log.d(TAG,"className = " + className);
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
