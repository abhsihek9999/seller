package com.hk.seller.core.database;

import com.hk.seller.core.application.HKApp;

import androidx.room.Room;
import androidx.room.RoomDatabase;

//@Database(entities = {},version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    private static final String DB_NAME = "HK_database";

//    public abstract GolfDao getGolfMemberDao();
//
//    public abstract OutletModelDao outletModelDao();
//
//    public abstract CourseModelDao courseModelDao();
//
//    public abstract PressReleaseModelDao pressReleaseModelDao();
//
//    public abstract FaqCategoryDao faqCategoryDao();
//
//    public abstract FaqCategoryQuestionDao faqCategoryQuestionDao();
//
//    public abstract ProfileDao getProfileDao();
//
//    public abstract AboutUsDao aboutUsDao();
//
//    public abstract BarcodeDao getBarcodeDao();
//
//    public abstract CardDetailDao getCardDetailDao();
//
///*    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            // Since we didn't alter the table, there's nothing else to do here.
//        }
//    };*/
//
    public static AppDatabase getAppDatabase() {
        if (instance == null) {
            instance = Room.databaseBuilder(HKApp.getAppContext(), AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
//                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return instance;
    }


}