package missions;

import agents.Agent;
import colony.MarsColony;
import enums.AgentRole;
import enums.EquipmentType;
import enums.MissionType;
import enums.ResourceType;
import enums.SkillType;
import equipments.Equipment;
import java.util.ArrayList;
import java.util.List;

public class ResearchMission extends Mission{

    int requiredOxygen=5;
    int requiredBatteryPower=10;

    public ResearchMission(String location,SkillType requiredSkill){
        super(location, requiredSkill,7,MissionType.RESEARCH);

    }

    @Override
    public boolean hasSufficientRecourses(MarsColony colony) {
        return colony.getTotal(ResourceType.OXYGEN)>=requiredOxygen&&
        colony.getTotal(ResourceType.BATTERY)>=requiredBatteryPower;
    }

    @Override 
    public void consumeResources(MarsColony colony){
        colony.consume(ResourceType.OXYGEN, requiredOxygen);
        colony.consume(ResourceType.BATTERY, requiredBatteryPower);
    }
    
    @Override
    public boolean hasRequiredEquipment(MarsColony colony) {
        boolean hasEquipment=false;
        for(Equipment eq : colony.getAllEquipment()){
            if(eq.getType().equals(EquipmentType.RESEARCH)&&!eq.isDamaged()&&!eq.isAssigned()){
                hasEquipment=true;
                break;
            }
                
        }
        return hasEquipment&&colony.hasOperationalLab();
    }

    @Override
    public void assignEquipment(MarsColony colony) {
        for(Equipment eq : colony.getAllEquipment()){
            if(eq.getType().equals(EquipmentType.RESEARCH) && !eq.isDamaged() && !eq.isAssigned()){
                eq.setAssigned(true);
                break;
            }
        }
    }

    @Override
    public boolean  assembleTeam(List<Agent> availableAgents) {
        int requiredScientists=2;
        int scientistCount=0;
        int requiredDrones = 1;
        int droneCount = 0;

        List<Agent> assigned = new ArrayList<>();
        for(Agent agent : availableAgents){
            if(scientistCount<requiredScientists&&agent.canPerform(this)&&agent.getRole().equals(AgentRole.SCIENTIST) ){
                assigned.add(agent);
                scientistCount++;

            }
            if(droneCount < requiredDrones && agent.canPerform(this) && agent.getRole().equals(AgentRole.DRONE) ){
                assigned.add(agent);
                droneCount++;
            }

            if(scientistCount == requiredScientists && droneCount == requiredDrones){
                for(Agent assignedAgent : assigned){
                    this.assign(assignedAgent);
                }
                return true;
            }

        }
        return false;
    }

    



    


    
}
