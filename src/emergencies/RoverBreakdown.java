package emergencies;
import enums.EmergencyType;
import agents.ExplorationRover;
public class RoverBreakdown extends Emergency {

    public ExplorationRover  rover;

    RoverBreakdown(ExplorationRover rover) {
        super(EmergencyType.ROVER_BREAKDOWN);
        this.rover = rover;
        this.setLocationToMartianSurface();
    }

    @Override
    public void happen() {

        System.out.println("Rover has broken down on the Martian surface!");
        rover.setAvailable(false);
    }
    
}
