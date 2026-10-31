package colony;

import java.util.ArrayList;
import java.util.List;
import colony.MarsColony;
import facilities.Facility;
import emergencies.Emergency;
import enums.EmergencyState;
import emergencies.Emergency;
import agents.Agent;
import enums.ResourceType;
import emergencies.OxygenLeak;
public class EmergencyHandler {

    public boolean handleEmergencies(MarsColony colony){
        List<Agent> allAgents = colony.getAllAgents();
        for (Emergency emergency: colony.emergencies){
            if (emergency.state == EmergencyState.UNRESOLVED){
                emergency.happen();
                if (emergency.type == EmergencyType.OXYGEN_LEAK){
                    OxygenLeak oxygenLeak = (OxygenLeak) emergency;
                    handleOxygenLeak(colony,oxygenLeak,allAgents);
                }



                return true;
            }
        }
        return false;
    }

    private boolean handleOxygenLeak(MarsColony colony,OxygenLeak emergency,List<Agent> allAgents){
        boolean humanAgentAvailable=false;
        boolean robotAgentAvailable=false;
        boolean sparePartsAvailable=false;
        for (Agent agent: allAgents){
            if (agent.canRespond(emergency) && agent.isAvailable()&& agent.isHuman()){
                agent.setLocation(emergency.location);
                humanAgentAvailable=true;
                break;
            }
        }
        for (Agent agent: allAgents){
            if (agent.canRespond(emergency) && agent.isAvailable()&& agent.isRobot()){
                agent.setLocation(emergency.location);
                robotAgentAvailable=true;
                break;
            }
        }
        if (colony.getTotal(enums.ResourceType.SPARE_PARTS)>0){
            sparePartsAvailable=true;
        }
        if (!humanAgentAvailable){
            System.out.println("No available human agent to respond to the emergency.");
        }
        if (!robotAgentAvailable){
            System.out.println("No available robotic unit to respond to the emergency.");
        }
        if (!sparePartsAvailable){
            System.out.println("No spare parts available to resolve the emergency.");
        }
        if (humanAgentAvailable && robotAgentAvailable && sparePartsAvailable){
            colony.consume(ResourceType.SPARE_PARTS,1);
            emergency.affectedHabitat.looseOxygen(emergency.oxygenLeakAmount);

            emergency.resolveEmergency();
            emergency.affectedHabitat.setOperational(true);
            return true;
        }
        else{
            System.out.println("Unable to resolve the emergency.");
            emergency.affectedHabitat.looseOxygen(2*(emergency.oxygenLeakAmount));
            return false;
        }

    }


}


