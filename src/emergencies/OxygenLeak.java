package emergencies;
import enums.EmergencyType;
import facilities.Habitat;
public class OxygenLeak extends Emergency {
    private final String location;
    public final Habitat affectedHabitat;

    public OxygenLeak(String location, Habitat affectedHabitat) {
        super(EmergencyType.OXYGEN_LEAK);
        this.location = location;
        this.affectedHabitat = affectedHabitat;
    }

    @Override
    public void resolveEmergency() {
        System.out.println("Oxygen leak at " + location + " has been resolved.");
    }

    @Override
    public void happen() {
        System.out.println("Oxygen leak has occurred at " + location + "!");
        affectedHabitat.looseOxygen(10); // Example amount, replace with actual value
    }


    
}
