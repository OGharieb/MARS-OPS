package emergencies;
import enums.EmergencyType;
public class RoverBreakdown extends Emergency {
    public RoverBreakdown() {
        super(EmergencyType.ROVER_BREAKDOWN);
        this.setLocationToMartianSurface();
    }

    @Override
    public void happen() {
        System.out.println("Rover has broken down on the Martian surface!");
    }
    
}
