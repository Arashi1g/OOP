import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create an instance of the pilot.
        System.out.println("\n Main creating two warrior instances\n");
        CloneWarrior pilot = new Clone_Trooper_Pilot();
        CloneWarrior commando = new Clone_Commando();

        // Build the list of CloneWarriors:
        List<CloneWarrior> squad = new ArrayList<>();
        squad.add(pilot);
        squad.add(commando);

        // Create a Battle instance:
        Battle battle = new Battle();

        // Send the clone warriors into battle.
        battle.doBattle(squad);

    }
}
