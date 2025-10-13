package com.example.leyan_final_exam;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class DeliveryViewModel extends AndroidViewModel {
    private DeliveryRepository repository;

    public DeliveryViewModel(Application application) {
        super(application);
        repository = new DeliveryRepository(application);
    }

    public LiveData<List<Delivery>> getDeliveriesByStatus(String status) {
        return repository.getDeliveriesByStatus(status);
    }

    public void updateDelivery(Delivery delivery) {
        repository.updateDelivery(delivery);
    }
}
