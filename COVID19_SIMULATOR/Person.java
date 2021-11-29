package COVID19_SIMULATOR;
import COVID19_SIMULATOR.Vaccine;
import COVID19_SIMULATOR.MainEXE;
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
        //this.setNeighbors(this.neighbors);
    }
    

    //
    public void setNeighbors(Person[] neighbors){
    this.neighbors = neighbors;
    }


    public Person[] getNeighbors(){
        return this.neighbors; 
    }

    public void infectNeighbors(double infectionRate){
        Person[] riskOfInfection = getNeighbors(); // 감염자의 이웃을 감염위험군(riskOfInfection) Person객체를 가지는 1차원 배열로 설정한다.
        for(int i=0; i<9; i++) // 해당 배열 요소 전체에 접근하여 
        {
            if(riskOfInfection[i].state == State.SUSCEPTIBLE && infectionRate >= Math.random())
            //
            {
                riskOfInfection[i].setState(State.INFECTIOUS);
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

    public boolean isSusceptible(){
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
        else if(this.state == State.VACCINATED) return "o";
        else return "";
    }



}