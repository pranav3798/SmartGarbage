package com.example.smartgarbagesystem;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import static android.app.Service.START_STICKY;

public class MyService extends Service {
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
