package facilities;

import enums.FacilityType;
import agents.*;
public class Habitat extends Facility{
    private final LifeSupportSystem lifeSupport;
    Habitat(String facilityName){
    super(FacilityType.HABITAT, facilityName);
    lifeSupport = new LifeSupportSystem(this.agents, this.resources);
    }
    public boolean isHabitable(){
        return lifeSupport.isHabitable();
    }
    public void looseOxygen(int amount){
        boolean success = this.resources.consumeAmount(enums.ResourceType.OXYGEN, amount);
        if (!success){
            this.resources.consumeAll(enums.ResourceType.OXYGEN);
        }
        if (this.resources.isEmpty(enums.ResourceType.OXYGEN)){
        System.out.println("Warning: " + this.facilityName + " has no oxygen left!!!!!!");
        }
    }
    
}
