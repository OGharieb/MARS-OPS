package missions;

import colony.MarsColony;
import java.util.List;

public class RepairMission extends Mission{

    public RepairMission(int duration) {
        super("MARTIAN_SURFACE",List.of("REPAIR","MAINTENANCE"),duration);
    }

    public boolean hasSufficientResources(MarsColony colony){
        
    }



    
    
}
