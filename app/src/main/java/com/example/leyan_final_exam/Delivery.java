package com.example.leyan_final_exam;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Ignore;
import java.io.Serializable;

@Entity(tableName = "deliveries")
public class Delivery implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String customerName;
    public String address;
    public String status;
    public String note;

    public Delivery(String customerName, String address, String status, String note) {
        this.customerName = customerName;
        this.address = address;
        this.status = status;
        this.note = note;
    }

    @Ignore
    public Delivery(String customerName, String address, String status) {
        this(customerName, address, status, "");
    }
}
