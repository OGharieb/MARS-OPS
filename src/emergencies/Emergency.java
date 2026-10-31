package emergencies;
import enums.EmergencyType;
import enums.EmergencyState;

public abstract class Emergency {
    public EmergencyType type;
    public EmergencyState state;
    public String location;
    Emergency(EmergencyType type){
        this.type = type;
        this.state = EmergencyState.UNRESOLVED;
    }
    void setLocation(String location){
        this.location = location;
    }
    void setLocationToMartianSurface() {
        this.location = "Martian Surface";
    }
    abstract void resolveEmergency();
    abstract void happen();
}
