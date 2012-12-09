package ousl.group4.service;

import ousl.group4.model.User;

import java.util.List;

public interface UserService {

    /**
     * @param userId
     * @return
     */
    public User getUser(Long userId);


    /**
     * @param username
     * @return
     */
    public User getUserByUsername(String username);


    /**
     * @return
     */
    public List<User> getUsers();


    /**
     * @param user
     */
    public User saveUser(User user);

}
