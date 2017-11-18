package com.bitirme.taksishare.mvc.controller;

/**
 * Created by exper on 21.04.2017.
 */
import com.bitirme.taksishare.mvc.models.ActionResult;
import com.bitirme.taksishare.mvc.models.LoginSignupResponse;
import com.bitirme.taksishare.mvc.models.User;
import com.bitirme.taksishare.services.UploadService;
import com.bitirme.taksishare.services.UsersService;
import com.bitirme.taksishare.spring.ActiveUserId;
import javafx.geometry.Pos;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by fikricanca on 18.12.2016.
 */

@Controller
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UploadService uploadService;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ResponseBody
    public User me(@ActiveUserId Integer userId) {
        return usersService.getUserById(userId);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public LoginSignupResponse signUp(@RequestParam String name, @RequestParam String surname, @RequestParam String email,  @RequestParam String phone, @RequestParam Integer gender ,@RequestParam String birthdate ,@RequestParam(required = false, defaultValue = "") String image, @RequestParam(required = false, defaultValue = "") String fbId, @RequestParam(required = false, defaultValue = "") String fbAccessToken, @RequestParam String password) {
        LoginSignupResponse loginSignupResponse = usersService.createUser(name, surname, email,  phone,gender,birthdate, image, fbId, fbAccessToken, password);
        return loginSignupResponse;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public LoginSignupResponse login(HttpServletResponse response, @RequestParam String email, @RequestParam String password) {
        LoginSignupResponse loginSignupResponse = usersService.loginWithEmailAndPassword(email, password);
        if(loginSignupResponse.getUser() == null){
            response.setStatus(525);
        }
        return loginSignupResponse;
    }

    @RequestMapping(value = "/image-upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public JSONObject imageUpload(@RequestPart MultipartFile image) {
        String imageUrl = uploadService.persistFile(image);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("image", imageUrl);
        return jsonObject;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ActionResult update(@ActiveUserId Integer userId, @RequestParam String name, @RequestParam String surname, @RequestParam String email,
                               @RequestParam String phone, @RequestParam Integer gender ,@RequestParam String birthdate ,@RequestParam(required = false, defaultValue = "") String image, @RequestParam(required = false, defaultValue = "") String fbId,
                               @RequestParam(required = false, defaultValue = "") String fbAccessToken) {

        User user = new User(userId, name, surname, email,  phone, gender, birthdate, image, fbId, fbAccessToken);
        return usersService.updateUser(user);
    }

    @RequestMapping(value = "/check-mail-is-exist", method = RequestMethod.GET)
    @ResponseBody
    public ActionResult checkIfEmailRegistered(@RequestParam String email) {

        if (usersService.checkIfEmailRegistered(email) == null) {

            return new ActionResult(true, "");

        }else {

            return new ActionResult(false, "Bu email adresiyle kayıtlı bir kullanıcı bulunmaktadır.");

        }

    }

}