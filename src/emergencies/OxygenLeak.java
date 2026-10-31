package emergencies;
import enums.EmergencyType;
import facilities.Habitat;
public class OxygenLeak extends Emergency {
    public final String location;
    public final Habitat affectedHabitat;

    public OxygenLeak(Habitat affectedHabitat) {
        super(EmergencyType.OXYGEN_LEAK);
        this.location = affectedHabitat.facilityName;
        this.affectedHabitat = affectedHabitat;
    }


    @Override
    public void happen() {
        System.out.println("Oxygen is leaking at " + location + "!!!!");
        affectedHabitat.looseOxygen(10); // Example amount, replace with actual value
    }


    
}
