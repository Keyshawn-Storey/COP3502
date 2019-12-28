public class Pakuri {
    private String species;
    private int attack, defense, speed;

    //the Pakuri  is created and then the values of each category is given.
    public Pakuri(String species) {
        this.species = species;
        attack = species.length() * 7 + 9;
        defense = species.length() * 5 + 17;
        speed = species.length() * 6 + 13;
    }

    //returns the species
    public String getSpecies() {
        return species;
    }

    //returns the attack value
    public int getAttack() {
        return attack;
    }

    //returns the defense value
    public int getDefense() {
        return defense;
    }

    //returns the speed value.
    public int getSpeed() {
        return speed;
    }

    //sets the attack of the Pakuri
    public void setAttack(int newAttack) {
        attack = newAttack;
    }

    //evolve a species by a set mulitplier.
    public void evolve() {
        attack *= 2;
        defense *= 4;
        speed *= 3;
    }
}
