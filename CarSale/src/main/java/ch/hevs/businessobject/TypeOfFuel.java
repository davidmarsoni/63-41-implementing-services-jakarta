package ch.hevs.businessobject;

/**
 * Represents the type of fuel of a car.
 */
public enum TypeOfFuel {
    GASOLINE, DIESEL, ELECTRIC, HYBRID;

    @Override
    public String toString() {
        // replace underscores with spaces and convert to lower case
        String s = name().replace('_', ' ').toLowerCase();

        // capitalize the first letter
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}