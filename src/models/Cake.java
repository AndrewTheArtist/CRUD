package models;

public class Cake {

    protected int cake_id;
    protected String cake_name;
    protected int recipe_id;

    public Cake() {
    }

    public Cake(int id) {
        this.cake_id = id;
    }

    public Cake(int id, String name, int recipe_ID) {
        this(name, recipe_ID);
        this.cake_id = id;
    }

    public Cake(String name, int recipe_ID) {
        this.cake_name = name;
        this.recipe_id = recipe_ID;
    }


    public int getId() {
        return cake_id;
    }

    public void setId(int id){
        this.cake_id = id;
    }

    public String getName() {
        return cake_name;
    }

    public void setName(String name) {
        this.cake_name = name;
    }






}
