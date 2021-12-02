package COVID19_SIMULATOR;
import COVID19_SIMULATOR.Vaccine;
import COVID19_SIMULATOR.MainEXE;
public class Person{
    State state;
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
        setNeighbors(this.neighbors);
    }
    

 public void setNeighbors(Person[] neighbors){
    this.neighbors = neighbors; 
}

  public Person[] getNeighbors(){
        return this.neighbors; 
}

    public void infectNeighbors(double infectionRate){
        System.out.println("이웃전파 들어옴");
        for(int i=0; i<8; i++)
        {
            if(!(getNeighbors()[i].isVaccinated()))
            {
                if(getNeighbors()[i].isSuseceptible() && infectionRate >= Math.random())
                {
                    System.out.print("ROfI"+"["+i+"]"+getNeighbors()[i]+" / ");
                    getNeighbors()[i].setState(State.INFECTIOUS);
                }
            }
            
        }
        System.out.println();
    }

    public void update(double recoveryRate){
        if(isInfectious() && recoveryRate>=Math.random())
        {
            setState(State.SUSCEPTIBLE);
        }
    }

    public void setState(State state){
        this.state = state; 
        if(isInfectious()) // 감염 상태로 바꿀 경우 
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
        else if(this.state == State.VACCINATED) return "o";
        else return "";
    }



}