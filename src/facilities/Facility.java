package facilities;

import enums.FacilityType;
import equipments.Equipment;
import java.util.ArrayList;
import java.util.List;
import resources.Resources;
import resources.resources2;

public abstract  class Facility {
    protected boolean isOperational;
    protected FacilityType type;
    protected Resources resources;
    protected resources2 resources2;
    protected List<Equipment> equipments;

    public Facility(FacilityType type, int capacity) {
        this.isOperational = true;
        this.type = type;
        this.resources = new Resources();
        this.equipments = new ArrayList<>();
    }

    

    public boolean isOperational() {
        return isOperational;
    }

    public FacilityType getType() {
        return type;
    }



    public List<Equipment> getEquipments() {
        return equipments;
    }

    public Resources getResources() {
        return resources;
    }

    public resources2 getResources2() {
        return resources2;
    }

    
    

    
    
}
