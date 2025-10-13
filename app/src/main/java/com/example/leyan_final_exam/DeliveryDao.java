package com.example.leyan_final_exam;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DeliveryDao {
    @Insert
    void insert(Delivery delivery);

    @Update
    void updateDelivery(Delivery delivery);

    @Delete
    void deleteDelivery(Delivery delivery);

    @Query("SELECT * FROM deliveries")
    LiveData<List<Delivery>> getAllDeliveries();

    @Query("SELECT * FROM deliveries WHERE status = :status")
    LiveData<List<Delivery>> getDeliveriesByStatus(String status);

    @Query("SELECT COUNT(*) FROM deliveries")
    int getTotalCount();

    @Query("SELECT COUNT(*) FROM deliveries WHERE status = 'Pending'")
    int getPendingCount();

    @Query("SELECT COUNT(*) FROM deliveries WHERE status = 'In Progress'")
    int getInProgressCount();

    @Query("SELECT COUNT(*) FROM deliveries WHERE status = 'Completed'")
    int getCompletedCount();
}
