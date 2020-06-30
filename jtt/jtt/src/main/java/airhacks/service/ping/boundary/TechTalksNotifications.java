package airhacks.service.ping.boundary;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricRegistry.Type;
import org.eclipse.microprofile.metrics.annotation.RegistryType;

@Startup
@Singleton
@ServerEndpoint("/notifications")
public class TechTalksNotifications {
    
    private Optional<Session> session;

    @Inject
    @RegistryType(type = Type.APPLICATION)
    MetricRegistry registry;

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
        registry.counter("broadcasting_to_listeners").inc();
        System.out.println("TechTalksNotifications.sendPing() " + System.currentTimeMillis());
        this.send("hello world");
    }
    
    void send(String text) {
        registry.counter("send_messages").inc();
        this.session.map(Session::getAsyncRemote).ifPresent(r -> r.sendText(text));
   
    }
}