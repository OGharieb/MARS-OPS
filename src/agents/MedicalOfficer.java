package agents;

import emergencies.Emergency;
import enums.AgentRole;
import enums.EmergencyType;
import enums.MissionType;
import enums.SkillType;
import java.util.List;
import missions.Mission;

public class MedicalOfficer extends  CrewMember {

    public MedicalOfficer(String id,String location, String name,List<SkillType> skills){
        super(id,location,name,AgentRole.MEDICAL_OFFICER,skills);
    }
    
    @Override
    public boolean canPerform(Mission mission) {
        return isAvailable()&&
         skills.contains(mission.getRequiredSkill())&&
         mission.getMissionType()==MissionType.RESCUE;
    }

    @Override
    public boolean canRespond(Emergency emergency) {
        return isAvailable()&&
        (emergency.getType()==EmergencyType.MEDICAL_EMERGENCY||
        emergency.getType()==EmergencyType.FIRE);
    }

    
}
