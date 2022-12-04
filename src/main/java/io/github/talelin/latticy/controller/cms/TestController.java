package io.github.talelin.latticy.controller.cms;

import io.github.talelin.latticy.module.message.WsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/cms/test")
public class TestController {

    @Autowired
    private WsHandler wsHandler;

    @RequestMapping("")
    public String index() {
        Optional<WebSocketSession> session = wsHandler.getSessions().stream().findFirst();
        if (session.isPresent()) {
            try {
                wsHandler.sendMessage(session.get(), "对第一个会话发送了一条消息");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 省略其它代码
        return "123";
    }
}
