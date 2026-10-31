package emergencies;
import enums.EmergencyType;
import facilities.Habitat;
public class OxygenLeak extends Emergency {
    public final Habitat affectedHabitat;
    private boolean hasLeaked = false;
    public final int oxygenLeakAmount = 10; // Amount of oxygen lost during the leak
    public OxygenLeak(Habitat affectedHabitat) {
        super(EmergencyType.OXYGEN_LEAK);
        this.location = affectedHabitat.facilityName;
        this.affectedHabitat = affectedHabitat;
    }


    @Override
    public void happen() {
        if (!hasLeaked) {
        hasLeaked = true;
        
        System.out.println("Oxygen is leaking at " + location + "!!!!");
    }
}


    
}
