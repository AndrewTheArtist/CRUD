package models;

public class Recipe {

    protected int recipe_id;
    protected String recipe_description;
    protected String ingredients;

    public Recipe() {
    }

    public Recipe(int id) {
        this.recipe_id = id;
    }

    public Recipe(int id, String description, String ingredients) {
        this(description, ingredients);
        this.recipe_id = id;
    }

    public Recipe(String description, String ingredients) {
        this.recipe_description = description;
        this.ingredients = ingredients;
    }

    public int getId() {
        return recipe_id;
    }

    public void setId(int id) {
        this.recipe_id = id;
    }

    public String getDescription() {
        return recipe_description;
    }

    public void setDescription(String description) {
        this.recipe_description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

}
