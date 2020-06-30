package airhacks.service.ping.boundary;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Startup
@Singleton
@ServerEndpoint("/notifications")
public class TechTalksNotifications {
    
    private Optional<Session> session;

    @PostConstruct
    public void init() {
        this.session = Optional.empty();
    }

	@OnOpen
    public void onConnect(Session session) {
        this.session = Optional.of(session);
        System.out.println("TechTalksNotifications.onConnect() " + session);

    }
    
    @OnMessage
    public void onMessage(String message) {
        this.session.map(Session::getAsyncRemote).ifPresent(r -> r.sendText("echo -> " + message));
    }
    
    @Schedule(minute = "*",second = "*/2",hour = "*")
    public void sendPing() {
        System.out.println("TechTalksNotifications.sendPing() " + System.currentTimeMillis());
        this.session.map(Session::getAsyncRemote).
        ifPresent(r -> r.sendText("ping -> " + LocalDateTime.now()));
    }
}