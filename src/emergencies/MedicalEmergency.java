package emergencies;
import agents.Agent;
public class  MedicalEmergency extends Emergency {
    private final Agent agent;
    public MedicalEmergency(Agent agent) {
        super(enums.EmergencyType.MEDICAL_EMERGENCY);
        this.agent = agent;
        this.location = agent.getLocation();
    }

    @Override
    public void happen() {
        System.out.println("A medical emergency has occurred to agent " + this.agent.getId() + " at " + this.location);
        this.agent.setAvailable(false);
    }
    
}
