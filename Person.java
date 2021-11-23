public enum State{
    SUSCEPTIBLE,
    INFECTIOUS,
    VACCINATED,
}

public class Person{
    private State state;
    private boolean infected;
    private Person[] neighbors;

    public Person(){


    }

    public void setNeighbors(Person[] neighbors){

    }

    public Person[] getNeighvors(){}

    public void infectNeighbors(double infectionRate){}

    public void update(double recoveryRate){}

    public void setState(State state){}

    public boolean isSuseceptible(){}

    public boolean isInfectious(){}

    public boolean isVaccinated(){}

    public String toString(){}


}