package indiana.jones.project;

import java.util.List;

public class KnapSack implements KnapsackAlgorithm {

	private static KnapsackAlgorithm algorithm;

	private KnapSack() {
		if (algorithm != null) {
			throw new AssertionError();
		}
	}

	public static KnapsackAlgorithm createKnapSackWithRepetition() {
		if (algorithm == null) {
			algorithm = new KnapSack();
		}

		return algorithm;
	}

	@Override
	public long findMaxBagPrice(List<Treasure> treasures, int cap) {
		long[] dp = new long[cap + 1];
		dp[0] = 0L;

		for (int i = 1; i < dp.length; i++) {
			long max = 0L;

			for (Treasure treasure : treasures) {
				int remain = i - treasure.getWeight();
				if (remain < 0)
					continue;

				long option = dp[remain] + treasure.getPrice();

				if (max < option) {
					max = option;
				}
			}

			dp[i] = max;
		}

		return dp[cap];
	}
}
