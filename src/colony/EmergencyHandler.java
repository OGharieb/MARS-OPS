package colony;
 
import agents.Agent;
import emergencies.Emergency;
import emergencies.Fire;
import emergencies.MedicalEmergency;
import emergencies.OxygenLeak;
import emergencies.RoverBreakdown;
import enums.EmergencyState;
import enums.EmergencyType;
import enums.MissionState;
import enums.ResourceType;
import facilities.Facility;
import java.util.List;
import missions.Mission;
 
public class EmergencyHandler {
    private List<Facility> facilities;

    public boolean handleEmergencies(MarsColony colony){
        this.facilities = colony.getFacilities();
        boolean handledAny = false;
 
        for (Emergency emergency: colony.emergencies){
            if (emergency.state == EmergencyState.RESOLVED|| emergency.attempted){
                continue;
            }
            emergency.attempted=true;
            List<Agent> allAgents = colony.getAllAgents();
            emergency.happen(colony);
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
                Mission mission = roverBreakdown.rover.assignedMission;
                if (mission != null){
                    mission.setState(MissionState.FAILED);
                }
                handleRoverBreakdown(colony,roverBreakdown,allAgents);
            }
            else if (emergency.type == EmergencyType.MEDICAL_EMERGENCY){
                MedicalEmergency medicalEmergency = (MedicalEmergency) emergency;
                handleMedicalEmergency(colony,medicalEmergency,allAgents);
            }
            handledAny = true;
        }
        return handledAny;
    }
 
    private boolean handleOxygenLeak(MarsColony colony,OxygenLeak emergency,List<Agent> allAgents){
        boolean humanAgentAvailable=false;
        boolean robotAgentAvailable=false;
        boolean sparePartsAvailable=false;
        for (Agent agent: allAgents){
            if (agent.canRespond(emergency) && agent.isAvailable()&& agent.isHuman()){
                dispatchAgent(emergency.location,agent);
                humanAgentAvailable=true;
                break;
            }
        }
        for (Agent agent: allAgents){
            if (agent.canRespond(emergency) && agent.isAvailable()&& agent.isRobot()){
                dispatchAgent(emergency.location,agent);
                robotAgentAvailable=true;
                break;
            }
        }
        if (colony.getTotal(ResourceType.SPARE_PARTS)>0){
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
           colony.consume(ResourceType.SPARE_PARTS, 1);
            emergency.resolveEmergency();
            emergency.affectedHabitat.setOperational(true);
            return true;
        }
        else{
            System.out.println("Unable to resolve the emergency.");
            emergency.failureReason="Missing required responders or spare parts";
            emergency.affectedHabitat.looseOxygen(emergency.oxygenLeakAmount);
            return false;
        }
 
    }
 
    private boolean handleFire(MarsColony colony, Fire emergency,List<Agent> allAgents){
        boolean agentAvailable = false;
        for (Agent agent: allAgents){
            if (agent.canRespond(emergency) && agent.isAvailable()){
                dispatchAgent(emergency.location,agent);
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
                dispatchAgent(emergency.location,agent);
                agentAvailable=true;
                break;
            }
        }
        if (colony.getTotal(ResourceType.SPARE_PARTS)>0){
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
    
            emergency.rover.repair();
            colony.consume(ResourceType.SPARE_PARTS,1);
 
            return true;
        }
        else{
            return false;
        }
 
    }
 
    private boolean handleMedicalEmergency(MarsColony colony, MedicalEmergency emergency,List<Agent> allAgents){
        
        Facility patientFacility = getFacilityByLocation(emergency.agent.getLocation());
        if (patientFacility == null || !patientFacility.isOperational()) {
            System.out.println("patient facility is not operational.");
            return false;
        }
        
        boolean agentAvailable = false;
        boolean suppliesAvaliable = false;
        for (Agent agent: allAgents){
            if (agent.canRespond(emergency) && agent.isAvailable()){
                dispatchAgent(emergency.location,agent);
                agentAvailable=true;
                break;
            }
        }
        if (colony.getTotal(ResourceType.MEDICAL_SUPPLIES)>0){
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
            emergency.agent.setIncapacitated(false);
            emergency.agent.setHealthStatus(emergency.agent.getHealthStatus() + 50);
            colony.consume(ResourceType.MEDICAL_SUPPLIES,1);
 
            return true;
        }
        else{
            return false;
        }
    }

    private void dispatchAgent(String targetLocation, Agent agent){
        Facility destinationFacility = getFacilityByLocation(targetLocation);
        if (destinationFacility == null){
            System.out.println(agent.getId() + " dispatched to " + targetLocation);
            return;
        }
        Facility agentFacility = getFacilityByLocation(agent.getLocation());
        if (agentFacility != null){
            agentFacility.removeAgentByID(agent.getId());
        }
        destinationFacility.addAgent(agent);
    }
 

    private Facility getFacilityByLocation(String location){
        if (location == null){
            return null;
        }
        for (Facility facilty : this.facilities){
            if (facilty.facilityName.equals(location)){
                return facilty;
            }
        }
        return null;
    }
}