package indiana.jones.project;

import java.util.List;

public class KnapSackWithoutRepetition implements KnapsackAlgorithm {

	private static KnapsackAlgorithm algorithm;

	private KnapSackWithoutRepetition() {
		if (algorithm != null) {
			throw new AssertionError();
		}
	}

	public static KnapsackAlgorithm createKnapSackWithoutRepetition() {
		if (algorithm == null) {
			algorithm = new KnapSackWithoutRepetition();
		}

		return algorithm;
	}

	@Override
	public long findMaxBagPrice(List<Treasure> treasures, int cap) {
		int N = treasures.size();
		long[] prices = new long[N + 1];
		int[] weights = new int[N + 1];
		int index = 1;
		for (Treasure tr : treasures) {
			prices[index] = tr.getPrice();
			weights[index] = tr.getWeight();
			index++;
		}

		long[][] options = new long[N + 1][cap + 1];
		boolean[][] sol = new boolean[N + 1][cap + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= cap; j++) {
				long opt1 = options[i - 1][j];
				long opt2 = Integer.MIN_VALUE;

				if (weights[i] <= j) {
					opt2 = prices[i] + options[i - 1][j - weights[i]];
				}
				options[i][j] = Math.max(opt1, opt2);
				sol[i][j] = (opt2 > opt1);
			}
		}

		boolean[] take = new boolean[N + 1];
		for (int n = N, w = cap; n > 0; n--) {
			if (sol[n][w]) {
				take[n] = true;
				w -= weights[n];
			} else {
				take[n] = false;
			}
		}

		long result = 0L;
		for (int i = 0; i < take.length; i++) {
			if (take[i]) {
				result += prices[i];
			}
		}
		return result;
	}

}
