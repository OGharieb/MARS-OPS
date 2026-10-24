package agents;

public interface Agent {
    String getID();
    String getLocation();
    void setLocation(String Location);
    boolean isAvailable();
    void setAvailable(boolean available);
    boolean canRespond(Emergency emergency);
    boolean canPerform(Mission mission);
}
