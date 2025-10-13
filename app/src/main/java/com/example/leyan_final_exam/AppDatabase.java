package com.example.leyan_final_exam;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Delivery.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DeliveryDao deliveryDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "delivery_database")
                            .addCallback(roomCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@androidx.annotation.NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                DeliveryDao dao = INSTANCE.deliveryDao();

                dao.insert(new Delivery("Leyan", "Gaza City - Rimal", "Pending", "Deliver before noon"));
                dao.insert(new Delivery("Tala", "Khan Younis - Al Amal", "In Progress", "Urgent package"));
                dao.insert(new Delivery("Ahmad", "Rafah - Al Shaboura", "Completed", "Delivered successfully"));
                dao.insert(new Delivery("Sara", "Deir al-Balah - Camp", "Pending", "Customer not home yet"));
                dao.insert(new Delivery("Omar", "Beit Lahia - Al Atatra", "Completed", "Signed by receiver"));
            });
        }
    };
}
