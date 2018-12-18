import java.util.ArrayList;
import java.util.List;

public class UserApi {
    private static class Holder {
        private static final UserApi INSTANCE = new UserApi();
    }

    private UserApi() {
    }

    public static UserApi getInstance() {
        return Holder.INSTANCE;
    }

    public User getUser() {
        return new User();
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        return list;
    }
}
