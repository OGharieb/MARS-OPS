package agents;
import missions.Mission;
import emergencies.Emergency;

public interface Agent {
    String getId();
    String getLocation();
    void setLocation(String location);
    boolean isAvailable();
    void setAvailable(boolean available);
    boolean canRespond(Emergency emergency);
    boolean canPerform(Mission mission);
}
