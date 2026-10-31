package facilities;
import enums.FacilityType;


public class CommunicationStation extends Facility{
    CommunicationStation(String facilityName){
        super(FacilityType.COMMUNICATION_STATION, facilityName);
    }
    public void sendMessage(String message){
        System.out.println("Message sent: " + message);
    }
    public void receiveMessage(String message){
        System.out.println("Message received: " + message);
    }
}
