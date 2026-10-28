package equipments;

import enums.EquipmentType;

public class Equipment {

    private boolean isDamaged;
    private final String id;
    private final EquipmentType type;

    public Equipment(String id,EquipmentType type) {
        this.id=id;
        this.type=type;
        isDamaged=false;
    }

    public EquipmentType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public boolean isIsDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean isDamaged) {
        this.isDamaged = isDamaged;
    }
    
    

    

    
}


