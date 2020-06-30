package airhacks.service.ping.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;


@ApplicationScoped
public class StartupService {
    

    public void initOnStart(@Observes @Initialized(ApplicationScoped.class) Object doesntMatter) {
        System.out.println("CDI: StartupService.initOnStart()");
    }
}