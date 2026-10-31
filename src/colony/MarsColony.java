package colony;

import agents.Agent;
import enums.FacilityType;
import enums.MissionState;
import enums.ResourceType;
import equipments.Equipment;
import facilities.Facility;
import java.util.ArrayList;
import java.util.List;
import missions.Mission;
import emergencies.Emergency;

public class MarsColony {
    List<Facility> facilities = new ArrayList<>();
    private int currentDay;
    List<Mission> missions= new ArrayList<>();
    public static String martianSurface = "Martian Surface";
    private EmergencyHandler emergencyHandler = new EmergencyHandler();
    public List<Emergency> emergencies = new ArrayList<>();

    public MarsColony(){
        this.facilities= new ArrayList<>();
        this.currentDay=1;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }
    public void addFacility(Facility facility){
        facilities.add(facility);
    }

    public int getTotal(ResourceType type) {
        int total = 0;
        for (Facility facility : this.facilities) {
            if (facility.isOperational()) {
                total += facility.getResources().getAmount(type);
            }
        }
        return total;
    }
    
    public boolean consume(ResourceType type, int amount) {
        if (getTotal(type) < amount) {
            return false;
        }
        
        int remaining = amount;
        for (Facility facility : facilities) {
            if (facility.isOperational() && remaining > 0) { 
                int available = facility.getResources().getAmount(type);
                
                if (available >= remaining) {
                    facility.getResources().consumeAmount(type, remaining);
                    remaining = 0;
                } else if (available > 0) {
                    facility.getResources().consumeAmount(type, available);
                    remaining -= available;
                }
            }
        }
        return true;
    }

    public List<Equipment> getAllEquipment(){
        List<Equipment> totalEquipment= new ArrayList<>(); 
        
        for(Facility facility : facilities){
            if(facility.isOperational()){
                totalEquipment.addAll(facility.getEquipment());
            }
        }
        return totalEquipment;
    }
    public List<Agent> getAllAgents(){
        List<Agent> totalAgents= new ArrayList<>(); 
        
        for(Facility facility : facilities){
            if(facility.isOperational()){
                totalAgents.addAll(facility.getAgents());
            }
        }
        return totalAgents;
    }

    public boolean hasOperationalLab(){
        for(Facility facility : facilities){
            if(facility.getType().equals(FacilityType.LABORATORY)&&facility.isOperational()){
                return true;
            }
        }
        return false;
    }

    public void submitMission(Mission mission){
        if(!mission.hasRequiredEquipment(this)||!mission.hasSufficientRecourses(this)){
            mission.setState(MissionState.FAILED);
            missions.add(mission);
            return;
        }
        List<Agent> allAgents= getAllAgents();
        if(mission.assembleTeam(allAgents)){
            mission.consumeResources(this);
            mission.assignEquipment(this);
            mission.setState(MissionState.ASSIGNED);
            missions.add(mission);
        }
        else{
            mission.setState(MissionState.FAILED);
            missions.add(mission);
        }




    }

    public void advanceDay() {
        this.currentDay++;
        for(Mission mission: missions){
            if(mission.getState() == MissionState.ASSIGNED || mission.getState() == MissionState.IN_PROGRESS)
                mission.advanceTime();
        }
        
    }

    public int getCurrentDay() {
        return currentDay;
    }


    }

    
    

