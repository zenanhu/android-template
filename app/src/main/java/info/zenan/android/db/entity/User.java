package info.zenan.android.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name="name")
    public String name;
}
