package emergencies;
import agents.ExplorationRover;
import colony.MarsColony;
import enums.EmergencyType;
public class RoverBreakdown extends Emergency {
 
    public ExplorationRover  rover;
 
    private boolean hasStarted = false;
 
    public RoverBreakdown(ExplorationRover rover) {
        super(EmergencyType.ROVER_BREAKDOWN);
        this.rover = rover;
        this.location = MarsColony.martianSurface;
    }
 
    @Override
    public void happen() {
        if (hasStarted) {
            return;
        }
        hasStarted = true;
        System.out.println("Rover has broken down on the Martian surface!");
        rover.setFunctional(false);
    }
    
}