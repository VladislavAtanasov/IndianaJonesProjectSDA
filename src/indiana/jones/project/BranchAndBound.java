package indiana.jones.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class BranchAndBound {

	private class Node implements Comparable<Node> {
		private List<Treasure> taken;
		private long value;
		private int weight;
		private long bound;
		private int level;

		public Node() {
			taken = new ArrayList<>();
			this.value = 0L;
			this.weight = 0;
			this.bound = 0L;
			this.level = 0;
		}

		public Node(Node parent) {
			this.level = parent.level + 1;
			taken = new ArrayList<>(parent.taken);
			this.value = parent.value;
			this.weight = parent.weight;
			this.bound = parent.bound;
		}

		@Override
		public int compareTo(Node o) {
			return (int) (o.bound - this.bound);
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", weight=" + weight + ", bound=" + bound + ", level=" + level + "]";
		}

	}

	private Node root;

	public BranchAndBound() {
		root = new Node();
	}

	public long knapsackWithoutRepetition(List<Treasure> items, int cap) {
		if (cap == 0) {
			return 0L;
		}

		Collections.sort(items, Treasure.byRatio());
		Node best = new Node();
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(root);
		this.computeBound(root, cap, items);

		while (!queue.isEmpty()) {
			Node current = queue.poll();

			if (current.level < items.size() - 1 && current.bound > best.value) {
				Node chosen = new Node(current);
				Treasure firstTreasure = items.get(current.level);
				chosen.weight += firstTreasure.getWeight();

				if (chosen.weight <= cap) {
					chosen.taken.add(firstTreasure);
					chosen.value += firstTreasure.getPrice();
					this.computeBound(chosen, cap, items);
					if (chosen.value > best.value) {
						best = chosen;
					}

					if (chosen.bound > best.value) {
						queue.offer(chosen);
					}

				}
				Node without = new Node(current);
				this.computeBound(without, cap, items);

				if (without.bound > best.value) {
					queue.offer(without);
				}

			}
		}
		// System.out.println("Answer: " + best.value);
		// System.out.println(best.value + " value " + best.weight + " weight "
		// + best.bound + " bound.");
		// System.out.println(best.taken.toString());
		// System.out.println("BRANCHA");
		return best.value;
	}

	private void computeBound(Node chosen, int capacity, List<Treasure> treasures) {
		int totalWeight = chosen.weight;
		int numberTaken = treasures.size() - 1;
		int i = chosen.level;
		long upperBound = chosen.value;
		Treasure tr;
		do {
			tr = treasures.get(i);
			if (totalWeight + tr.getWeight() > capacity) {
				break;
			}
			totalWeight += tr.getWeight();
			upperBound += tr.getPrice();
			i++; // 1 | 2
		} while (i <= numberTaken);

		upperBound += (capacity - totalWeight) * (tr.getPrice() / tr.getWeight());

		chosen.bound = upperBound;
	}

	public static void main(String[] args) {
		// Scanner sc = new Scanner(System.in);
		// List<Treasure> list = new ArrayList<>();
		// for (int i = 0; i < 4; i++) {
		// String[] splitted = sc.nextLine().split(" ");
		// list.add(new Treasure(splitted[0], Long.parseLong(splitted[1]),
		// Integer.parseInt(splitted[2])));
		// }
		// BranchAndBound branch = new BranchAndBound();
		// System.out.println(branch.knapsackWithoutRepetition(list, 0));
		// sc.close();
	}

}
