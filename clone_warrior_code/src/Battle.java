import java.util.List;

public class Battle {
    public void doBattle(List<CloneWarrior> warriors) {
        int i;
        // Report and fire

        System.out.println("\nThe clones are battling away! Number of warriors: "
                + warriors.size());

        for (CloneWarrior warrior : warriors) {
            warrior.reportStatus();
            warrior.fireWeapon();
        }

    }
}
