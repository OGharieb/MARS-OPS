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

public class RepairMission extends Mission{
    int requiredSpareParts=1;

    public RepairMission(String location,SkillType requiredSkill){
        super(location, requiredSkill,2,MissionType.REPAIR);
    }

    @Override
    public boolean hasSufficientRecourses(MarsColony colony) {
        return colony.getTotal(ResourceType.SPARE_PARTS)>=requiredSpareParts;
    }
    
    @Override 
    public void consumeResources(MarsColony colony){
        colony.consume(ResourceType.SPARE_PARTS, requiredSpareParts);
    }

    @Override
    public boolean hasRequiredEquipment(MarsColony colony) {
        boolean hasEquipment=false;
        for(Equipment eq : colony.getAllEquipment()){
            if(eq.getType().equals(EquipmentType.REPAIR)&&!eq.isDamaged()&&!eq.isAssigned()){
                hasEquipment=true;
                break;
            }
                
        }
        return hasEquipment;
    }

    @Override
    public void assignEquipment(MarsColony colony) {
        for(Equipment eq : colony.getAllEquipment()){
            if(eq.getType().equals(EquipmentType.REPAIR) && !eq.isDamaged() && !eq.isAssigned()){
                eq.setAssigned(true);
                break;
            }
        }
    }

    @Override 
    public boolean  assembleTeam(List<Agent> availableAgents) {
        int requiredEngineerCount=1;
        int engineerCount=0;
        int requiredRepairBots=1;
        int repairBotCount=0;

        List<Agent> assigned = new ArrayList<>();
        for(Agent agent : availableAgents){
            if(engineerCount<requiredEngineerCount&&agent.canPerform(this)&&agent.getRole().equals(AgentRole.ENGINEER) ){
                assigned.add(agent);
                engineerCount++;

            }
            if(repairBotCount<requiredRepairBots&&agent.canPerform(this)&&agent.getRole().equals(AgentRole.REPAIR_ROBOT)){
                assigned.add(agent);
                repairBotCount++;

            }

            if(engineerCount==requiredEngineerCount&&repairBotCount==requiredRepairBots){
                for(Agent assignedAgent : assigned){
                    this.assign(assignedAgent);
                }
                return true;
            }

        }
        return false;
    }



    
    
}
