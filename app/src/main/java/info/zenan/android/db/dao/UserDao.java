package info.zenan.android.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import info.zenan.android.db.entity.User;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface UserDao {

    @Query("select * from user")
    LiveData<List<User>> loadAllUsers();

    @Query("select * from user where id = :id")
    User loadUserById(int id);

    @Query("select * from user where name = :firstName")
    LiveData<List<User>> findUserByName(String firstName);

    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM User")
    void deleteAll();
}
