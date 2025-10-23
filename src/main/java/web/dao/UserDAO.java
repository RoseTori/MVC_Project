package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserDAO {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);


    public UserDAO() {
        users.add(new User(idCounter.getAndIncrement(),"Josh", "Miller"));
        users.add(new User(idCounter.getAndIncrement(),"Jane", "Smith"));
        users.add(new User(idCounter.getAndIncrement(),"Bob", "Johnson"));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void saveUser(User user) {
        user.setId(idCounter.getAndIncrement());
        users.add(user);
    }

    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                break;
            }
        }
    }

    public void deleteUser(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
