
/**
 * A basic class to hold information about game items
 *
 * @author Daniel Zetterstrom
 * @version Final
 */
public class Item
{
    private String description;
    private int weight;
    
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
    }
    /**
     * Returns description of the Item
     * @returns String description of the item
     */
    public String getDescription(){
        return this.description;
    }
    /**
     * Returns weight of the Item
     * @returns Integer weight of the item
     */
    public int getWeight(){
        return this.weight;
    }
}
