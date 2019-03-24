package info.zenan.android.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

import info.zenan.android.db.AppDatabase;
import info.zenan.android.db.dao.UserDao;
import info.zenan.android.db.entity.User;

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
