package emergencies;

import enums.EmergencyType;

public abstract class  Emergency {
    protected  EmergencyType type;
    protected  boolean resolved;

    public Emergency(EmergencyType type) {
        this.type = type;
        this.resolved = false;
    }

    public EmergencyType getType() {
        return type;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
