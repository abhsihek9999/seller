package com.abhishek.seller.core.database;

public final class DBHolder {
    private DBHolder() {

    }

    private static AppDatabase appDatabase;


    public static AppDatabase getDB() {
        appDatabase = AppDatabase.getAppDatabase();
        return appDatabase;
    }
}