package agents;
import emergencies.Emergency;
import enums.AgentRole;
import missions.Mission;
import colony.MarsColony;
public interface Agent {
    String getId();
    String getLocation();
    void setLocation(String location);
    boolean isAvailable();
    void setAvailable(boolean available);
    boolean canRespond(Emergency emergency);
    boolean canPerform(Mission mission);
    AgentRole getRole();
    default void setLocationToMartianSurface() {
        setLocation(MarsColony.martianSurface);
    }

}
