package facilities;

import enums.FacilityType;
import equipments.Equipment;
import java.util.ArrayList;
import java.util.List;
import resources.Resources;

public abstract  class Facility {
    protected boolean isOperational;
    protected FacilityType type;
    protected Resources resources;
    protected List<Equipment> equipments;

    public Facility(FacilityType type, int capacity) {
        this.isOperational = true;
        this.type = type;
        this.resources = new Resources();
        this.equipments = new ArrayList<>();
    }

    

    public boolean isIsOperational() {
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

    

    
    
}
