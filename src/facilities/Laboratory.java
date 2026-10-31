package facilities;

import enums.FacilityType;
import enums.AgentRole;
import agents.Agent;

public class Laboratory extends Facility{
    Laboratory(String facilityName){
        super(FacilityType.LABORATORY, facilityName);
    }

    public int hasScientists(){
        int count=0;
        for(Agent agent:agents){
            if(agent.getRole().equals(AgentRole.SCIENTIST)){
                count++;
            }
        }
        return count;
    }
    public void repairAllEquipments(){
        for(int i=0;i<equipments.size();i++){
            equipments.get(i).repairEquipment();
        }
    }
    public void damageAllEquipments(){
        for(int i=0;i<equipments.size();i++){
            equipments.get(i).damageEquipment();
        }
    }
}

