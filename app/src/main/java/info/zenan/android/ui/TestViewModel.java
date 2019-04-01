package info.zenan.android.ui;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import info.zenan.android.AndroidApplication;
import info.zenan.android.db.AppDatabase;
import info.zenan.android.db.entity.User;
import info.zenan.android.db.repository.UserRepository;

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
