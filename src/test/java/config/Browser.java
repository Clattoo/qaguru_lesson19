package config;

public enum Browser {
    CHROME, FIREFOX;

    public String browserToLowerCase() {
        return this.name().toLowerCase();
    }
}