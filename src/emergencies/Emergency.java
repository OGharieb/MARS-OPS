package emergencies;
import colony.MarsColony;
import enums.EmergencyState;
import enums.EmergencyType;

public abstract class Emergency {
    public EmergencyType type;
    public EmergencyState state;
    public String location;
    public boolean attempted; 
    public String failureReason="";
    Emergency(EmergencyType type){
        this.type = type;
        this.state = EmergencyState.UNRESOLVED;
        this.attempted=false;
    }

    
    public void setLocation(String location){
        this.location = location;
    }
    public void setLocationToMartianSurface() {
        this.location = MarsColony.martianSurface;
    }
     public void resolveEmergency(){
        this.state = EmergencyState.RESOLVED;
     }
    abstract public void happen(MarsColony colony);


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
