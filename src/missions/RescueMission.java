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

public class RescueMission extends Mission {
    int requiredFood=2;
    int requiredMedical =2;

    public RescueMission(String location,SkillType requiredSkill){
        super(location, requiredSkill,1,MissionType.RESCUE);

    }

    @Override
    public boolean hasSufficientRecourses(MarsColony colony) {
    
        return colony.getTotal(ResourceType.FOOD)>=requiredFood&&
        colony.getTotal(ResourceType.MEDICAL_SUPPLIES)>=requiredMedical;
    }
    @Override 
    public void consumeResources(MarsColony colony){
        colony.consume(ResourceType.FOOD, requiredFood);
        colony.consume(ResourceType.MEDICAL_SUPPLIES, requiredMedical);
    }

    @Override
    public boolean hasRequiredEquipment(MarsColony colony) {
        boolean hasEquipment=false;
        for(Equipment eq : colony.getAllEquipment()){
            if(eq.getType().equals(EquipmentType.RESCUE)&&!eq.isDamaged()&&!eq.isAssigned()){
                hasEquipment=true;
                break;
            }
                
        }
        return hasEquipment;
    }

    @Override
    public void assignEquipment(MarsColony colony) {
        for(Equipment eq : colony.getAllEquipment()){
            if(eq.getType().equals(EquipmentType.RESCUE) && !eq.isDamaged() && !eq.isAssigned()){
                eq.setAssigned(true);
                break;
            }
        }
    }

    @Override 
    public boolean  assembleTeam(List<Agent> availableAgents) {
        int requiredMedics=1;
        int medicCount=0;
        int requiredRovers = 1;
        int roverCount = 0;

        List<Agent> assigned = new ArrayList<>();
        for(Agent agent : availableAgents){
            if(medicCount<requiredMedics&&agent.canPerform(this)&&agent.getRole().equals(AgentRole.MEDICAL_OFFICER) ){
                assigned.add(agent);
                medicCount++;

            }
            if(roverCount < requiredRovers && agent.canPerform(this) && agent.getRole().equals(AgentRole.EXPLORATION_ROVER)){
                assigned.add(agent);
                roverCount++;
            }

            if(medicCount==requiredMedics&&roverCount == requiredRovers){
                for(Agent assignedAgent : assigned){
                    this.assign(assignedAgent);
                }
                return true;
            }

        }
        return false;
    }


}
