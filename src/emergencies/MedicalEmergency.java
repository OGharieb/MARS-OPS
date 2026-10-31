package emergencies;
import agents.CrewMember;
public class  MedicalEmergency extends Emergency {
    public final CrewMember agent;
    public MedicalEmergency(CrewMember agent) {
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
