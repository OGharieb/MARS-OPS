package emergencies;
import colony.MarsColony;
import enums.EmergencyState;
import enums.EmergencyType;

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
        this.location = MarsColony.martianSurface;
    }
     void resolveEmergency(){
        this.state = EmergencyState.RESOLVED;
     }
    abstract void happen();
    
    public EmergencyType getType() {
        return type;
    }

    public boolean isResolved() {
        return this.state == EmergencyState.RESOLVED;
        
    }

    public void setResolved(boolean resolved) {
        if (resolved){
            this.state = EmergencyState.RESOLVED;
        } else {
            this.state = EmergencyState.UNRESOLVED;
        }
    }
    
}
