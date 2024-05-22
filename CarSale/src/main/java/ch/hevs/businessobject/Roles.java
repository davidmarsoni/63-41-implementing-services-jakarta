package ch.hevs.businessobject;

public enum Roles {
   ADMIN, OWNER, BUYER,UNKNOWN;

    @Override
    public String toString() {
        return  name().replace('_', ' ').toLowerCase();
    }
}
