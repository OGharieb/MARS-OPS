package agents;

import emergencies.Emergency;
import enums.AgentRole;
import enums.EmergencyType;
import enums.MissionType;
import enums.SkillType;
import java.util.List;
import missions.Mission;

public class Engineer extends CrewMember {

    public Engineer(String id,String location, String name,List<SkillType> skills){
        super(id,location,name,AgentRole.ENGINEER,skills);
    }

    @Override
    public boolean canPerform(Mission mission) {
        return isAvailable()&&
         skills.contains(mission.getRequiredSkill())&&
         (mission.getMissionType()==MissionType.REPAIR||mission.getMissionType()==MissionType.EXPLORATION);
    }

    @Override
    public boolean canRespond(Emergency emergency) {
        return isAvailable()&&
        (emergency.getType()==EmergencyType.OXYGEN_LEAK||
        emergency.getType()==EmergencyType.ROVER_BREAKDOWN||
        emergency.getType()==EmergencyType.FIRE);
    }


    
}