package colony;
 
import agents.Engineer;
import agents.ExplorationRover;
import agents.RepairRobot;
import agents.Scientist;
import emergencies.OxygenLeak;
import emergencies.RoverBreakdown;
import enums.EnvironmentCondition;
import enums.EquipmentType;
import enums.ResourceType;
import enums.SkillType;
import equipments.Equipment;
import facilities.Habitat;
import facilities.Laboratory;
import java.util.Arrays;
import missions.ExplorationMission;
 
public class Main {
    public static void main(String[] args) {
        MarsColony colony = new MarsColony();
 
        Habitat habA = new Habitat("Habitat A");
        Laboratory lab1 = new Laboratory("Lab 1");
 
        habA.getResources().addAmount(ResourceType.OXYGEN, 100);
        habA.getResources().addAmount(ResourceType.BATTERY, 30);
        habA.getResources().addAmount(ResourceType.SPARE_PARTS, 5);
 
        habA.addEquipment(new Equipment("EQ-1", EquipmentType.EXPLORATION));
        habA.addEquipment(new Equipment("EQ-2", EquipmentType.EXPLORATION));
 
        Scientist sci1 = new Scientist("S-1", "Habitat A", "Omar", Arrays.asList(SkillType.EXPLORATION));
        Engineer eng1 = new Engineer("E-1", "Habitat A", "Ali", Arrays.asList(SkillType.REPAIR));
        ExplorationRover rover1 = new ExplorationRover("R-1", "Habitat A");
        RepairRobot bot1 = new RepairRobot("B-1", "Habitat A");
 
        habA.addAgent(sci1);
        habA.addAgent(eng1);
        habA.addAgent(rover1);
        habA.addAgent(bot1);
 
        colony.addFacility(habA);
        colony.addFacility(lab1);
 

        System.out.println("\nSCENARIO 1: Successful Exploration:");
        colony.printStatusReport();
        ExplorationMission m1 = new ExplorationMission("Jezero Crater", SkillType.EXPLORATION);
        colony.submitMission(m1);
        for(int i = 0; i < 4; i++){
            colony.advanceDay();
        }
        System.out.println("Mission state: " + m1.getState());
        colony.printStatusReport();
 

        System.out.println("\nSCENARIO 2: Resource Shortage:");
        ExplorationMission m2 = new ExplorationMission("Valles Marineris", SkillType.EXPLORATION);
        colony.submitMission(m2);
        System.out.println("Mission state: " + m2.getState());
 

        System.out.println("\nSCENARIO 3: Mission Rejection (Unsuitable Agent):");
        habA.getResources().addAmount(ResourceType.BATTERY, 30);
        ExplorationMission m3 = new ExplorationMission("North Pole", SkillType.MEDICAL);
        colony.submitMission(m3);
        System.out.println("Mission state: " + m3.getState());
        colony.printStatusReport();

        System.out.println("\nSCENARIO 4: Oxygen Leak Emergency:");
        OxygenLeak leak = new OxygenLeak(habA);
        colony.addEmergency(leak);
        colony.advanceDay();
        System.out.println("Emergency state: " + leak.state + ", Habitat A operational: " + habA.isOperational());
        colony.printStatusReport();
 

        System.out.println("\nSCENARIO 5: Rover Breakdown During a Mission:");
        ExplorationMission m4 = new ExplorationMission("Gale Crater", SkillType.EXPLORATION);
        colony.submitMission(m4);
        RoverBreakdown breakdown = new RoverBreakdown(rover1);
        colony.addEmergency(breakdown);
        colony.advanceDay();
        System.out.println("Emergency state: " + breakdown.state + ", mission state: " + m4.getState()
                + ", rover available again: " + rover1.isAvailable());
 
        // Scenario 6: Environmental Effect (Dust Storm)
        System.out.println("\nSCENARIO 6: Environmental Effect (Dust Storm):");
        ExplorationMission m5 = new ExplorationMission("Olympus Mons", SkillType.EXPLORATION);
        colony.submitMission(m5);                        // accepted: weather is normal
        colony.setEnvironment(EnvironmentCondition.DUST_STORM);   // fails the active exploration mission
        System.out.println("Active mission state: " + m5.getState());
        ExplorationMission m6 = new ExplorationMission("Hellas Basin", SkillType.EXPLORATION);
        colony.submitMission(m6);                        // refused during the storm
        System.out.println("New mission state: " + m6.getState());
    }
}