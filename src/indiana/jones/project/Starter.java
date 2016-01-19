package indiana.jones.project;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Starter {

	private static final int WITH_REPETITIONS = 1;
	private static final int WITHOUT_REPETITIONS = -1;
	private static final int BRANCH_AND_BOUND = 0;

	public static void startConsole() {
		boolean run = true;
		Scanner sc = new Scanner(System.in);

		while (run) {
			try {
				System.out.println("Enter file name: ");
				String file = sc.nextLine();
				FileReader reader = new FileReader(file);
				reader.loadTreasures();
				List<Treasure> treasures = reader.getTreasures();
				System.out.println("Enter the bag capacity of Indiana Jones: ");
				int num = Integer.parseInt(sc.nextLine());
				System.out.println(
						"Maximum price that the bag of Indiana Jones can have is " + KnapSack.maxPrice(num, treasures));
				run = false;
				sc.close();
			} catch (IOException e) {
				System.out.println("Wrong file name or the file does not exist.");
			} catch (InputMismatchException | IllegalArgumentException e) {
				System.out.println("Invalid number for a capacity of the bag.");
			}
		}
	}

	public static String startDesktop(String file, int num, int repeat) throws IOException {
		FileReader reader = new FileReader(file);
		reader.loadTreasures();
		List<Treasure> treasures = reader.getTreasures();
		StringBuilder sb = new StringBuilder();
		long result = 0L;
		if (repeat == WITHOUT_REPETITIONS) {
			result = KnapSack.maxPriceWithoutRepetition(num, treasures);
		} else if (repeat == WITH_REPETITIONS) {
			result = KnapSack.maxPrice(num, treasures);
		} else if (repeat == BRANCH_AND_BOUND) {
			result = new BranchAndBound().knapsackWithoutRepetition(treasures, num);
		}
		sb.append("Max value: ");
		NumberFormat formatter = NumberFormat.getInstance();
		String res = formatter.format(result);
		sb.append(res);
		sb.append(" $.");
		return sb.toString();

	}

	public static String startDesktopAuto(int num, int repeat) throws IOException {
		TreasureGenerator generator = new TreasureGenerator();
		generator.loadRandomTreasures();
		List<Treasure> treasures = generator.getTreasures();
		StringBuilder sb = new StringBuilder();
		long result = 0L;
		if (repeat == WITHOUT_REPETITIONS) {
			result = KnapSack.maxPriceWithoutRepetition(num, treasures);
		} else if (repeat == WITH_REPETITIONS) {
			result = KnapSack.maxPrice(num, treasures);
		} else if (repeat == BRANCH_AND_BOUND) {
			result = new BranchAndBound().knapsackWithoutRepetition(treasures, num);
		}
		sb.append("Max value: ");
		NumberFormat formatter = NumberFormat.getInstance();
		String res = formatter.format(result);
		sb.append(res);
		sb.append(" $.");
		return sb.toString();

	}

	public static void main(String[] args) {
		while (true) {
			String choice = JOptionPane.showInputDialog(null, "Choose Desktop or Console: ");
			if (choice.toLowerCase().equals("desktop")) {
				IndianaJonesGUI.main(args);
				break;
			} else if (choice.toLowerCase().equals("console")) {
				startConsole();
				break;
			} else if (choice.toLowerCase().equals("exit")) {
				break;
			}
		}
	}
}
