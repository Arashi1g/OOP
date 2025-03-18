public class Clone_Trooper_Pilot extends CloneWarrior {
    // A constant to simulate an enhanced weapon power for pilots:
    private static final int PILOT_POWER = 20;

    // Private fields unique to this subclass
    private int power = PILOT_POWER;
    private int flightHours = 42; // Just a sample value

    // Must implement the abstract method from CloneWarrior
    @Override
    public void reportStatus() {
        System.out.println("  Clone_Trooper_Pilot status: flightHours = "
                + flightHours + ", power = " + power);
    }

    // Overriding the fireWeapon method
    @Override
    public int fireWeapon() {
        System.out.println("  Clone_Trooper_Pilot firing pilot weapon with power: " + power--);
        return power;
    }

    // method that is unique to this child class; it does not exist in parent.
    public void flyAirShip() {
        System.out.println("Clone_Trooper_Pilot is flying the airship!");
    }
}
