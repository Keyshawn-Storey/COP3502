import java.util.ArrayList;
import java.util.Comparator;

public class Pakudex {
    private int capacity;
    private int numberOfCritters = 0;
    private ArrayList<Pakuri> pakuris = new ArrayList<>();


    //creates Pakudex with a default capacity of 20.
    public Pakudex() {
        capacity = 20;
    }

    //creates pakudex with inputed capacity.
    public Pakudex(int capacity) {
        this.capacity = capacity;
    }

    // returns the number of Pakuri
    public int getSize() {
        return numberOfCritters;
    }

    // returns the capacity available.
    public int getCapacity() {
        return capacity - numberOfCritters;
    }

    // returns an array of all the Pakuri.
    public String[] getSpeciesArray() {
        String[] arr = new String[getSize()];
        if (arr.length == 0) {
            return null;
        } else {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = pakuris.get(i).getSpecies();
            }
        }
        return arr;
    }

    // creates and int array of the stats
    public int[] getStats(String species) {
        int[] arr = new int[3];

        // loops to find the pakuri name in the function.
        for (Pakuri pakuri : pakuris) {
            if (pakuri.getSpecies().equals(species)) {
                arr[0] = pakuri.getAttack();
                arr[1] = pakuri.getDefense();
                arr[2] = pakuri.getSpeed();
            }
        }
        // if the pakuri does not exits then it returns null else it returns the array.
        if (arr[2] == 0) {
            return null;
        }
        return arr;
    }

    // uses comparator to sort the Pakuri in order.
    public void sortPakuri() {
        Comparator<Pakuri> compareBySpeciesName = Comparator.comparing(Pakuri::getSpecies);
        pakuris.sort(compareBySpeciesName);

    }

    public boolean addPakuri(String species) {
        //loop checks for if there is a pakuri with the name already.
        for (Pakuri pakuri : pakuris) {
            if (pakuri.getSpecies().equals(species)) {
                return false;
            }
        }
        // this checks to see if there is enough space to add the pakuri.
        //The pakuri is then added to an arraylist.
        if (pakuris.size() < capacity) {
            pakuris.add(new Pakuri(species));
            numberOfCritters++;
            return true;
        } else {
            return false;
        }
    }

    //evolves the species if it exists. The loop checks each pakuri in the array list.
    public boolean evolveSpecies(String species) {
        for (Pakuri pakuri : pakuris) {
            if (pakuri.getSpecies().equals(species)) {
                pakuri.evolve();
                return true;
            }
        }
        return false;
    }
}