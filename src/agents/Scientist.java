package agents;

import emergencies.Emergency;
import enums.AgentRole;
import enums.EmergencyType;
import enums.MissionType;
import enums.SkillType;
import java.util.List;
import missions.Mission;

public class  Scientist extends CrewMember{

    public Scientist(String id,String location, String name,List<SkillType> skills){
        super(id,location,name,AgentRole.SCIENTIST,skills);
    }

    @Override
    public boolean canPerform(Mission mission) {
        return isAvailable()&&
         skills.contains(mission.getRequiredSkill())&&
         (mission.getMissionType()==MissionType.RESEARCH||mission.getMissionType()==MissionType.EXPLORATION);
    }

    @Override
    public boolean canRespond(Emergency emergency) {
        return isAvailable()&&
        emergency.getType()==EmergencyType.FIRE;
    }


    


    

    
}
