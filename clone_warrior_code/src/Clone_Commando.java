public class Clone_Commando extends CloneWarrior {
    // A constant to simulate "commando power":
    private static final int COMMANDO_POWER = 30;

    // Private fields
    private int power = COMMANDO_POWER;
    private int fightHours = 100; // Arbitrary example value

    // Must implement the abstract method from CloneWarrior
    @Override
    public void reportStatus() {
        System.out.println("  Clone_Commando status: fightHours = "
                + fightHours + ", power = " + power);
    }

    // Overriding the fireWeapon method
    @Override
    public int fireWeapon() {
        System.out.println("  Clone_Commando firing weapon with power: " + power--);
        return power;
    }
}
