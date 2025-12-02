public class Actor {
    private int actorID;
    private String firstName;
    private String lastName;

    public Actor(int actorID, String firstName, String lastName){
        this.actorID = actorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    @Override
    public String toString() {
        return String.format("%d: %s %s\n", actorID, firstName, lastName);
    }
}
