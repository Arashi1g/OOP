public abstract class CloneWarrior {
    // A constant to simulate "pistol power":
    private static final int PISTOL_POWER = 10;

    // Private field with default power:
    private int power = PISTOL_POWER;

    // Abstract method (no implementation here)
    public abstract void reportStatus();

    // A concrete method with default "pistol firing" behavior
    public int fireWeapon() {
        System.out.println("CloneWarrior firing default weapon with power: " + power);
        return power;
    }
}
