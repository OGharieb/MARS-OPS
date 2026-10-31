package facilities;
import resources.Resources;
import enums.FacilityType;
import enums.ResourceType;

public class StorageFacility extends Facility{
    StorageFacility(){
        super(FacilityType.STORAGE);
    }
    public boolean hasResource( ResourceType resourceType){
        return this.resources.getAmount(resourceType) > 0;
    }
}
