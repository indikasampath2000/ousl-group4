package ousl.group4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ousl.group4.dao.UserDao;
import ousl.group4.model.User;
import ousl.group4.service.UserService;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(Long userId) {
        return userDao.getUser(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User saveUser(User user) {
        PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
        if(user.getVersion() == null){
            user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        }
        return userDao.saveUser(user);
    }

    /**
     * @param mobilePhoneNumber
     * @return
     */
    @Override
    public User getUserByMobilePhoneNumber(String mobilePhoneNumber) {
        return userDao.getUserByMobilePhoneNumber(mobilePhoneNumber);
    }

}
