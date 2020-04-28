package xyz.zenan.android.db.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

import xyz.zenan.android.db.AppDatabase;
import xyz.zenan.android.db.dao.UserDao;
import xyz.zenan.android.db.entity.User;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInMemoryDatabase(application);

        userDao = db.userDao();
    }

    public LiveData<List<User>> getAllUsers() {
        return userDao.loadAllUsers();
    }
}
