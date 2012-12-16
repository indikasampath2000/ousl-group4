package ousl.group4.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ousl.group4.model.User;

import java.util.List;

public interface UserDao {

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


    /**
     *
     * @param mobilePhoneNumber
     * @return
     */
    public User getUserByMobilePhoneNumber(String mobilePhoneNumber);


    /**
     * @param username
     * @return
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
     *
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
