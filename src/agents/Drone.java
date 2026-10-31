package agents;

import emergencies.Emergency;
import enums.AgentRole;
import enums.EmergencyType;
import enums.MissionType;
import missions.Mission;

public class Drone extends RoboticUnit {

    public Drone(String id,String location) {
        super(id, location, AgentRole.DRONE);
    }


    @Override
    public boolean canPerform(Mission mission) {
        return isAvailable()&&
         mission.getMissionType()==MissionType.RESEARCH;
    }

    @Override
    public boolean canRespond(Emergency emergency) {
        return isAvailable()&&
        emergency.getType()==EmergencyType.FIRE;
    }


    
    
}
