package airhacks.service.ping.boundary;

import javax.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class Ping {
    public int latency;
    
    @Schema(readOnly = true)
    @Size(min = 3,max = 10)
    public String name;

    public Ping() {
    }

    public Ping(int latency) {
        this.latency = latency;
        this.name = "juke";
    }
    
}