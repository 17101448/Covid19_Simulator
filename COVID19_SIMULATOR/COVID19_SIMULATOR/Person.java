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
        setNeighbors(this.neighbors);
    }
    

 public void setNeighbors(Person[] neighbors){
    this.neighbors = neighbors; 
}

  public Person[] getNeighbors(){
        return this.neighbors; 
}

    public void infectNeighbors(double infectionRate){
       // System.out.println("이웃전파 들어옴");
        for(int i=0; i<8; i++)
        {
            if(!(getNeighbors()[i].isVaccinated()))
            {
                if(getNeighbors()[i].isSuseceptible() && infectionRate >= Math.random())
                {
                    //System.out.print("ROfI"+"["+i+"]"+getNeighbors()[i]+" / ");
                    getNeighbors()[i].infected = true; 
                }
            }
            
        }
        System.out.println();
    }
    //의심 -> 감염 infected시, 회복률에 따라 
    public void update(double recoveryRate){
        if(this.infected && recoveryRate>=Math.random())
        {
            setState(State.SUSCEPTIBLE);
            this.infected = false; 
        }else if(this.infected)
        {
            setState(State.INFECTIOUS);
        }
    }

    public void setState(State state){
        this.state = state; 
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