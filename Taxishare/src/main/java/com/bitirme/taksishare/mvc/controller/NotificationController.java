package com.bitirme.taksishare.mvc.controller;

/**
 * Created by exper on 03.06.2017.
 */
import com.bitirme.taksishare.mvc.models.ActionResult;
import com.bitirme.taksishare.mvc.models.Notification;
import com.bitirme.taksishare.services.NotificationService;
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
import java.util.List;

@Controller
@RequestMapping(value = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/get-user-notifications", method = RequestMethod.GET, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public List<Notification> getNotificationByReceiverId(@ActiveUserId Integer receiverId) {

        return notificationService.getNotificationByReceiverId(receiverId);

    }

}
