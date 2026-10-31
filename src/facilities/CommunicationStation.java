package facilities;
import enums.FacilityType;


public class CommunicationStation extends Facility{
    CommunicationStation(){
        super(FacilityType.COMMUNICATION_STATION);
    }
    public void sendMessage(String message){
        System.out.println("Message sent: " + message);
    }
    public void receiveMessage(String message){
        System.out.println("Message received: " + message);
    }
}
