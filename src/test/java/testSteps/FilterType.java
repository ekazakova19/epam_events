package testSteps;

public enum FilterType {
    LOCATION("location"),
    CATEGORY("category"),
    LANGUAGE("language");

    private String type;

    FilterType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
