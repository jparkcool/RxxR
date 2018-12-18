import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.util.List;

//https://medium.com/corebuild-software/android-repository-pattern-using-rx-room-bac6c65d7385
public class UserRepository {
    private UserApi userApi = UserApi.getInstance();

    private static class Holder {
        private final static UserRepository INSTANCE = new UserRepository();
    }

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return Holder.INSTANCE;
    }

    public Observable<User> getUser() {
        Observable<User> observable = Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> e) throws Exception {
                User users = userApi.getUser();
                e.onNext(users);
            }
        });
        return observable;
    }

    public Observable<List<User>> getUsers() {
        Observable<List<User>> observable = Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> e) throws Exception {
                List<User> users = userApi.getUsers();
                e.onNext(users);
            }
        });
        return observable;
    }
}
