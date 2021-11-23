package COVID19_SIMULATOR;

public class Person{
    private State state;
    private boolean infected;
    private Person[] neighbors;

    public enum State{
        SUSCEPTIBLE,
        INFECTIOUS,
        VACCINATED,
    }

    public Person(){
        state = State.SUSCEPTIBLE;
        infected = false; 
    }

    public void setNeighbors(Person[] neighbors){
        for(int i=0; i<8; i++)
        {
            neighbors[i]=;
        }
        
    }

    public Person[] getNeighbors(){
      
        return this.neighbors; 
        
    }

    public void infectNeighbors(double infectionRate){

        Person[] test = getNeighbors();
        for(int i=0; i<8; i++)
        {
            if(test[i].state == State.SUSCEPTIBLE && infectionRate >= Math.random())
            {
                test[i].state =State.INFECTIOUS;
            }
        }
    }

    public void update(double recoveryRate){
        if(this.state == State.INFECTIOUS && recoveryRate>=Math.random())
        {
            setState(State.SUSCEPTIBLE);
        }
    }

    public void setState(State state){
        this.state = state; 
        if(state == State.INFECTIOUS) // 감염 상태로 바꿀 경우 
        {
            this.infected = true; // 불리언 값도 바꿔줌
        }
        else                     // 그 외 경우로 바꾸는 경우(백신, 안걸린 경우 등등)
        {
            this.infected = false; // 감염여부 거짓 
        }
    }

    public boolean isSuseceptible(){
        return this.state == State.SUSCEPTIBLE;
    }

    public boolean isInfectious(){
        return this.state == State.INFECTIOUS;
    }

    public boolean isVaccinated(){
        return this.state == State.VACCINATED;
    }

    public String toString(){

        if(this.state == State.SUSCEPTIBLE)
        {
            return ".";
        }
        else if(this.state == State.INFECTIOUS)
        {
            return "*";
        }
        else return "o";
    }


}