package missions;

import agents.Agent;
import colony.MarsColony;
import enums.AgentRole;
import enums.EquipmentType;
import enums.MissionType;
import enums.ResourceType;
import equipments.Equipment;
import java.util.ArrayList;
import java.util.List;

public class ExplorationMission extends Mission {
    int requiredBatteries=20;
    
    public ExplorationMission(String location,String requiredSkills){
        super(location, requiredSkills,4,MissionType.EXPLORATION);

    }


    @Override
    public boolean hasSufficientRecourses(MarsColony colony) {
    
        return colony.getTotal(ResourceType.BATTERY)>=requiredBatteries;
    }

    @Override 
    public void consumeResources(MarsColony colony){
        colony.consume(ResourceType.BATTERY, requiredBatteries);
    
    }

    @Override
    public boolean hasRequiredEquipment(MarsColony colony) {
        boolean hasEquipment=false;
        for(Equipment eq : colony.getAllEquipment()){
            if(eq.getType().equals(EquipmentType.EXPLORATION)&&!eq.isDamaged()&&!eq.isAssigned()){
                hasEquipment=true;
                break;
            }
                
        }
        return hasEquipment;
    }

    @Override
    public void assignEquipment(MarsColony colony) {
        for(Equipment eq : colony.getAllEquipment()){
            if(eq.getType().equals(EquipmentType.EXPLORATION) && !eq.isDamaged() && !eq.isAssigned()){
                eq.setAssigned(true);
                break;
            }
        }
    }

    @Override 
    public boolean  assembleTeam(List<Agent> availableAgents) {
        int requiredScientists=1;
        int scientistCount=0;
        int requiredRover=1;
        int roverCount=0;

        List<Agent> assigned = new ArrayList<>();
        for(Agent agent : availableAgents){
            if(scientistCount<requiredScientists&&agent.canPerform(this)&&agent.getRole().equals(AgentRole.SCIENTIST) ){
                assigned.add(agent);
                scientistCount++;

            }
            if(roverCount<requiredRover&&agent.canPerform(this)&&agent.getRole().equals(AgentRole.EXPLORATION_ROVER)){
                assigned.add(agent);
                roverCount++;

            }

            if(scientistCount==requiredScientists&&roverCount==requiredRover){
                for(Agent assignedAgent : assigned){
                    this.assign(assignedAgent);
                }
                return true;
            }

        }
        return false;
    }


}
