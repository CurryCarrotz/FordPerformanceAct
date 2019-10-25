package com.karimun.fordperformanceact.Functionalities;

import com.google.firebase.database.FirebaseDatabase;

public class FordPerformanceAct extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Set disk persistence
        // read more: https://firebase.google.com/docs/database/android/offline-capabilities
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
