package xyz.zenan.android.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.zenan.android.db.entity.User;

import static androidx.room.OnConflictStrategy.IGNORE;

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
