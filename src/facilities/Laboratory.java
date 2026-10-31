package facilities;

import agents.Agent;
import enums.AgentRole;
import enums.FacilityType;

public class Laboratory extends Facility{
    public Laboratory(String facilityName){
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

