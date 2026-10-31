package facilities;

import enums.FacilityType;
public class Habitat extends Facility{
    private final LifeSupportSystem lifeSupport;
    Habitat(String facilityName){
    super(FacilityType.HABITAT, facilityName);
    lifeSupport = new LifeSupportSystem(this.agents, this.resources);
    }
    public boolean isHabitable(){
        return lifeSupport.isHabitable();
    }

    
}
