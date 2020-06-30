package airhacks.service.ping.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class StartupService {
    

    @PostConstruct
    public void initOnStart() {
        System.out.println("StartupService.initOnStart()");
    }
}