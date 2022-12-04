package io.github.talelin.latticy.controller.ws;

import io.github.talelin.core.annotation.PermissionModule;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/message")
@PermissionModule(value = "用户")
@Validated
public class WebSocketController {

//    @GetMapping("")
//    private void findAll(){
//        System.out.println("123");
//    }
}
