// File : ProductCategory.java

public enum ProductCategory {
    ELECTRONICS("Electronics"),
    FOOD("Food"),
    CLOTHING("Clothing"), 
    FURNITURE("Furniture"),
    TOYS("Toys"), 
    HEALTH("Health & Beauty"), 
    SPORTS("Sports & Outdoors"),
    OTHER("Other");

    private final String displayName;

    ProductCategory(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    @Override
    public String toString()
    {
        return displayName;
    }
}
