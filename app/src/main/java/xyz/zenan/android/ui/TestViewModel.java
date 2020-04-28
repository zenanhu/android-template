package xyz.zenan.android.ui;

import android.app.Application;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import xyz.zenan.android.AndroidApplication;
import xyz.zenan.android.db.AppDatabase;
import xyz.zenan.android.db.entity.User;
import xyz.zenan.android.db.repository.UserRepository;

public class TestViewModel extends AndroidViewModel {

    @Inject
    AppDatabase appDatabase;

    private UserRepository repo;

    private LiveData<String> userNames;

    public TestViewModel(Application application) {
        super(application);
        AndroidApplication.getAppComponent(application).inject(this);
    }

    public LiveData<String> getUserNames() {
        return userNames;
    }

    public void createDb() {
        repo = new UserRepository(getApplication());

        // Populate it with initial data
        DatabaseInitializer.populateAsync(appDatabase);

        // Receive changes
        subscribeToDbChanges();
    }

    private void subscribeToDbChanges() {
//        LiveData<List<User>> users = mDb.userDao().findUserByName("Mike");
        LiveData<List<User>> users = repo.getAllUsers();

        // Instead of exposing the list of Loans, we can apply a transformation and expose Strings.
        userNames = Transformations.map(users, new Function<List<User>, String>() {
            @Override
            public String apply(List<User> users) {
                StringBuilder sb = new StringBuilder();
                for (User u : users) {
                    sb.append(u.name);
                    sb.append("\n");
                }
                return sb.toString();
            }
        });
    }

}
