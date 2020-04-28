package xyz.zenan.android.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;


@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name="name")
    public String name;
}
