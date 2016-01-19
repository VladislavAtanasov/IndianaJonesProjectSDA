package indiana.jones.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

	private Path filePath;
	private List<Treasure> treasures;
	private static final String DEFAULT_FOLDER = "resources";

	public FileReader(String fileURL) {
		filePath = Paths.get(DEFAULT_FOLDER, fileURL);
		treasures = new ArrayList<>();
	}

	public void loadTreasures() throws IOException {
		Files.lines(filePath).forEach(line -> addTreasure(line));
	}

	private void addTreasure(String line) {
		String[] splitted = line.split(" ");
		String treasureName = splitted[0];
		long treasurePrice = Long.parseLong(splitted[1]);
		int treasureWeight = Integer.parseInt(splitted[2]);
		treasures.add(new Treasure(treasureName, treasurePrice, treasureWeight));
	}

	public List<Treasure> getTreasures() {
		return treasures;
	}

	public void setTreasures(List<Treasure> treasures) {
		this.treasures = treasures;
	}

}
