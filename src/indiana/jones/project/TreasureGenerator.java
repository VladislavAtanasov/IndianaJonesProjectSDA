package indiana.jones.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreasureGenerator {
	private List<Treasure> treasures;
	private static final String[] RANDOM_NAMES = { "chocolate", "bread", "milk", "tomatoes", "carrots", "yoghurt",
			"stake", "potatoes", "book", "cooker", "microwave", "glasses", "cup", "spoon", "fork", "playstation" };
	private static final int NUMBER_OF_TREASURES = 10000;

	public TreasureGenerator() {
		treasures = new ArrayList<>();
	}

	public void loadRandomTreasures() {
		for (int i = 0; i < NUMBER_OF_TREASURES; i++) {
			addRandomTreasure();
		}
	}

	private void addRandomTreasure() {
		Random rd = new Random();
		int indexName = rd.nextInt(RANDOM_NAMES.length);
		String name = RANDOM_NAMES[indexName];
		long price = rd.nextInt(1_000_000) + 1;
		int weight = rd.nextInt(150) + 1;
		treasures.add(new Treasure(name, price, weight));
	}

	public List<Treasure> getTreasures() {
		return treasures;
	}

	public static void main(String[] args) {
		// TreasureGenerator gen = new TreasureGenerator();
		// System.out.println(gen.loadRandomTreasures());
	}

}
