package ch.hevs.businessobject;

public enum PaymentStatus {
    ACCEPTED, PENDING, REFUSED;

    @Override
    public String toString() {
        // replace underscores with spaces and convert to lower case
        String s = name().replace('_', ' ').toLowerCase();

        // capitalize the first letter
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}