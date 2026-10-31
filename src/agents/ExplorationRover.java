package agents;

import emergencies.Emergency;
import enums.AgentRole;
import enums.MissionType;
import missions.Mission;

public class ExplorationRover extends RoboticUnit{

    public ExplorationRover(String id,String location) {
        super(id, location, AgentRole.EXPLORATION_ROVER);
    }

    @Override
    public boolean canPerform(Mission mission) {
        return isAvailable()&&
         (mission.getMissionType()==MissionType.EXPLORATION||
         mission.getMissionType()==MissionType.RESCUE);
    }

    @Override
    public boolean canRespond(Emergency emergency) {
        return false;
    }
    
    
}
