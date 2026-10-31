package colony;

import agents.Engineer;
import agents.ExplorationRover;
import agents.RepairRobot;
import agents.Scientist;
import emergencies.OxygenLeak;
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
        EmergencyHandler handler = new EmergencyHandler();

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

        // Scenario 1: Successful Exploration
        System.out.println("\n--- SCENARIO 1: Successful Exploration ---");
        colony.printStatusReport();
        ExplorationMission m1 = new ExplorationMission("Jezero Crater", SkillType.EXPLORATION);
        colony.submitMission(m1);
        for(int i = 0; i < 4; i++){
            colony.advanceDay();
        }
        colony.printStatusReport();

        // Scenario 2: Mission Rejection (Unsuitable Skill)
        System.out.println("\n--- SCENARIO 2: Mission Rejection (Unsuitable Agent) ---");
        ExplorationMission m2 = new ExplorationMission("North Pole", SkillType.MEDICAL);
        colony.submitMission(m2);

        // Scenario 3: Resource Shortage (Only 10 battery left, needs 20)
        System.out.println("\n--- SCENARIO 3: Resource Shortage ---");
        ExplorationMission m3 = new ExplorationMission("Valles Marineris", SkillType.EXPLORATION);
        colony.submitMission(m3);
        colony.printStatusReport();

        // Scenario 4: Emergency Handling (Oxygen Leak)
        System.out.println("\n--- SCENARIO 4: Oxygen Leak Emergency ---");
        OxygenLeak leak = new OxygenLeak(habA);
        handler.handleEmergency(colony);
        colony.printStatusReport();

        // Scenario 5: Environmental Effect (Dust Storm)
        System.out.println("\n--- SCENARIO 5: Environmental Effect ---");
        habA.getResources().addAmount(ResourceType.BATTERY, 50);
        colony.setEnvironment(EnvironmentCondition.DUST_STORM);
        ExplorationMission m4 = new ExplorationMission("Olympus Mons", SkillType.EXPLORATION);
        colony.submitMission(m4);
    }
}