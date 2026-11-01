package emergencies;
import agents.Agent;
import agents.CrewMember;
import colony.MarsColony;
import enums.EmergencyType;
import facilities.Laboratory;
 
 
public class Fire extends Emergency {
    public final Laboratory affectedLaboratory;
 
    private boolean hasStarted = false;
 
    public Fire(Laboratory affectedLaboratory) {
        super(EmergencyType.FIRE);
        this.affectedLaboratory = affectedLaboratory;
        this.location = affectedLaboratory.facilityName;
    }
 
    @Override 
    public void happen(MarsColony colony) {
        if (hasStarted) {
            return;
        }
        hasStarted = true;
        System.out.println("Fire has broken out at " + location + "!!!!");
        affectedLaboratory.damageAllEquipments();
        affectedLaboratory.looseOxygen(5);
        affectedLaboratory.setOperational(false);
        for (Agent agent : affectedLaboratory.getAgents()) {
            if (agent.isHuman()) {
                colony.addEmergency(new MedicalEmergency((CrewMember) agent));
            }
        }
    }
    
}
