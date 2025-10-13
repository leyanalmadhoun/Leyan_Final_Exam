package com.example.leyan_final_exam;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class DeliveryRepository {
    private DeliveryDao deliveryDao;

    public DeliveryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        deliveryDao = db.deliveryDao();
    }

    public LiveData<List<Delivery>> getDeliveriesByStatus(String status) {
        return deliveryDao.getDeliveriesByStatus(status);
    }

    public void updateDelivery(Delivery delivery) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deliveryDao.updateDelivery(delivery);
        });
    }
}
