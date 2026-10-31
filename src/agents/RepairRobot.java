package agents;

import emergencies.Emergency;
import enums.AgentRole;
import enums.EmergencyType;
import enums.MissionType;
import missions.Mission;

public class RepairRobot extends RoboticUnit{

    public RepairRobot(String id,String location) {
        super(id, location, AgentRole.REPAIR_ROBOT);
    }

    @Override
    public boolean canPerform(Mission mission) {
        return isAvailable()&&
         mission.getMissionType()==MissionType.REPAIR;
    }

    @Override
    public boolean canRespond(Emergency emergency) {
        return isAvailable()&&
        (emergency.getType()==EmergencyType.OXYGEN_LEAK||
        emergency.getType()==EmergencyType.ROVER_BREAKDOWN);
    }

    
    
}
