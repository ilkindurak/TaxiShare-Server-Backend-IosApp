package com.bitirme.taksishare.services;

/**
 * Created by exper on 21.04.2017.
 */
import com.bitirme.taksishare.db.UsersDao;
import com.bitirme.taksishare.mvc.models.ActionResult;
import com.bitirme.taksishare.mvc.models.LoginSignupResponse;
import com.bitirme.taksishare.mvc.models.User;
import com.bitirme.taksishare.spring.ActiveUserId;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


@Component
public class UsersService {
    @Autowired
    UsersDao usersDao;

    public LoginSignupResponse loginWithEmailAndPassword(String email, String passwordClear) {
        User userByEmail = usersDao.getUserByEmail(email);
        LoginSignupResponse loginSignupResponse;
        if (userByEmail != null) {
            boolean passwordMatch = BCrypt.checkpw(passwordClear, userByEmail.getPassword());
            String token = UUID.randomUUID().toString();
            if (passwordMatch) {
                updateUserToken(userByEmail.getId(), token);
                loginSignupResponse = new LoginSignupResponse(new ActionResult(true, "Login başarılı."), userByEmail, token);
                return loginSignupResponse;
            } else {
                loginSignupResponse = new LoginSignupResponse(new ActionResult(false, "sifrenizi yanlıs girdiniz. Lütfen kontrol edip tekrar deneyiniz."));
                return loginSignupResponse;
            }
        } else {
            loginSignupResponse = new LoginSignupResponse(new ActionResult(false, "E-mail adresinizi ya da şifrenizi yanlış girdiniz. Lütfen kontrol edip tekrar deneyiniz."));
            return loginSignupResponse;
        }
    }

    public ActionResult updateUser(User user) {
        ActionResult actionResult;
        try {
            if (StringUtils.isEmpty(user.getName())) {
                user.setName(null);
            }
            if (StringUtils.isEmpty(user.getSurname())) {
                user.setSurname(null);
            }
            if (StringUtils.isEmpty(user.getEmail())) {
                user.setEmail(null);
            }
            if (StringUtils.isEmpty(user.getPhone())) {
                user.setPhone(null);
            }
            if (StringUtils.isEmpty(user.getGender())) {
                user.setGender(0);
            }
            if (StringUtils.isEmpty(user.getBirthdate())) {
                user.setBirthdate(null);
            }
            if (StringUtils.isEmpty(user.getFbId())) {
                user.setFbId(null);
            }
            if (StringUtils.isEmpty(user.getFbAccessToken())) {
                user.setFbAccessToken(null);
            }
            if (user.getImage().equals("")) {
                user.setImage(null);
            }

            if (usersDao.updateUser(user) > 0) {
                actionResult = new ActionResult(true, "Güncellendi.");
            } else {
                actionResult = new ActionResult(false, "Güncelleme Başarısız.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            actionResult = new ActionResult(false, "Hata oluştu. Sonra tekrar deneyiniz.");
        }
        return actionResult;
    }

    public User getUserById(int userId) {

        return usersDao.getUserById(userId);
    }

    public void deleteUser(Integer id) {
        usersDao.deleteUser(id);
    }

    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }

    public User checkIfEmailRegistered(String email) {

        return usersDao.getUserByEmail(email);

    }

    public LoginSignupResponse createUser(String name, String surname, String email, String phone,Integer gender,String birthdate, String image, String fbId, String fbAccessToken, String passwordClear) {

        String hashedPassword = BCrypt.hashpw(passwordClear, BCrypt.gensalt());

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);
        user.setBirthdate(birthdate);
        user.setImage(image);
        user.setFbId(fbId);
        user.setFbAccessToken(fbAccessToken);
        user.setPassword(hashedPassword);

        User userCheckForMail = usersDao.getUserByEmail(user.getEmail());
        if (userCheckForMail != null) {
            return new LoginSignupResponse(new ActionResult(false, "Kayıtlı mail adresi."));
        }

        String token = UUID.randomUUID().toString();

        Integer newUserId = usersDao.createUser(user, token);

        if (newUserId > 0) {
            user.setId(newUserId);
            return new LoginSignupResponse(new ActionResult(true, "Yeni kayıt oluşturuldu."), user, token);
        } else {
            return new LoginSignupResponse(new ActionResult(false, "Kayıt oluşturma başarısız."));
        }
    }

    public User validateTokenAndGetUser(String token) {

        return usersDao.getUserByToken(token);

    }

    public void updateUserToken(int id, String token) {

        usersDao.updateTokenByUserId(id, token);

    }
}