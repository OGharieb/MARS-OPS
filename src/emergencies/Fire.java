package emergencies;
import enums.EmergencyType;
import facilities.Laboratory;


public class Fire extends Emergency {
    public final Laboratory affectedLaboratory;

    Fire(Laboratory affectedLaboratory) {
        super(EmergencyType.FIRE);
        this.affectedLaboratory = affectedLaboratory;
        this.location = affectedLaboratory.facilityName;
    }

    @Override 
    public void happen() {
        System.out.println("Fire has broken out at " + location + "!!!!");
        affectedLaboratory.damageAllEquipments();
        affectedLaboratory.looseOxygen(5);
    }
    
}
