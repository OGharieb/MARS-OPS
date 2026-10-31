package colony;

import java.util.List;
import emergencies.Emergency;
import enums.EmergencyState;
import agents.Agent;
import enums.ResourceType;
import enums.EmergencyType;
import emergencies.OxygenLeak;
import emergencies.RoverBreakdown;
import emergencies.Fire;
import emergencies.MedicalEmergency;
import enums.MissionState;

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
                else if (emergency.type == EmergencyType.FIRE){
                    Fire fire = (Fire) emergency;
                    handleFire(colony,fire,allAgents);
                }
                else if (emergency.type == EmergencyType.ROVER_BREAKDOWN){
                    RoverBreakdown roverBreakdown = (RoverBreakdown) emergency;
                    roverBreakdown.rover.assignedMission.setState(MissionState.FAILED);
                    handleRoverBreakdown(colony,roverBreakdown,allAgents);
                }
                else if (emergency.type == EmergencyType.MEDICAL_EMERGENCY){
                    MedicalEmergency medicalEmergency = (MedicalEmergency) emergency;
                    handleMedicalEmergency(colony,medicalEmergency,allAgents);
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

    private boolean handleFire(MarsColony colony, Fire emergency,List<Agent> allAgents){
        boolean agentAvailable = false;
        for (Agent agent: allAgents){
            if (agent.canRespond(emergency) && agent.isAvailable()){
                agent.setLocation(emergency.location);
                agentAvailable=true;
                break;
            }
        }
        if (agentAvailable){
            emergency.resolveEmergency();
            emergency.affectedLaboratory.setOperational(true);
            return true;
        }
        else{
            System.out.println("agent unavailable");
            return false;
        }

    }
    private boolean handleRoverBreakdown(MarsColony colony, RoverBreakdown emergency,List<Agent> allAgents){
        boolean agentAvailable = false;
        boolean sparePartsAvailable = false;
        for (Agent agent: allAgents){
            if (agent.canRespond(emergency) && agent.isAvailable()){
                agent.setLocation(emergency.location);
                agentAvailable=true;
                break;
            }
        }
        if (colony.getTotal(enums.ResourceType.SPARE_PARTS)>0){
            sparePartsAvailable=true;
        }
        
        if (!agentAvailable){
            System.out.println("No available agent to respond to the emergency.");
        }
        if (!sparePartsAvailable){
            System.out.println("No spare parts available to resolve the emergency.");
        }
        if (agentAvailable&&sparePartsAvailable){
            emergency.resolveEmergency();
            emergency.rover.setAvailable(true);
            colony.consume(ResourceType.SPARE_PARTS,1);

            return true;
        }
        else{
            return false;
        }

    }

    private boolean handleMedicalEmergency(MarsColony colony, MedicalEmergency emergency,List<Agent> allAgents){
        boolean agentAvailable = false;
        boolean suppliesAvaliable = false;
        for (Agent agent: allAgents){
            if (agent.canRespond(emergency) && agent.isAvailable()){
                agent.setLocation(emergency.location);
                agentAvailable=true;
                break;
            }
        }
        if (colony.getTotal(enums.ResourceType.MEDICAL_SUPPLIES)>0){
            suppliesAvaliable=true;
        }
        
        if (!agentAvailable){
            System.out.println("No available agent to respond to the emergency.");
        }
        if (!suppliesAvaliable){
            System.out.println("No medical supplies.");
        }
        if (agentAvailable&&suppliesAvaliable){
            emergency.resolveEmergency();
            emergency.agent.setAvailable(true);
            colony.consume(ResourceType.MEDICAL_SUPPLIES,1);

            return true;
        }
        else{
            return false;
        }
    }

}


