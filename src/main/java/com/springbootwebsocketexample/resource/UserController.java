package com.springbootwebsocketexample.resource;

import com.springbootwebsocketexample.facade.UserFacade;
import com.springbootwebsocketexample.model.User;
import com.springbootwebsocketexample.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @MessageMapping("/user")
    @SendTo("/topic/user")
    public UserResponse getUser(User user) {

        return userFacade.getUser(user);
    }

}
