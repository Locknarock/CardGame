import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Actions {

	List<Card> deck = new ArrayList<>(52);
	List<Card> player = new ArrayList<>();
	List<Card> program = new ArrayList<>();
	int playerScore = 0;
	int programScore = 0;
	boolean playerBurned;
	boolean programBurned;

	Random rnd = new Random();

	public void constructDeck() {
		playerScore = 0;
		programScore = 0;
		player.clear();
		program.clear();
		deck.clear();

		while (deck.size() < 52) {
			Card card = constructCard();
			if (!isCardDuplicate(card)) {
				deck.add(card);
			}
		}

//		deck.forEach(System.out::println);

	}

	private boolean isCardDuplicate(Card card) {
		for (Card existingCard : deck) {
			if (existingCard.getName().equals(card.getName()) && existingCard.getSuit() == (card.getSuit())
					&& existingCard.getValue() == card.getValue()) {
				return true;
			}
		}
		return false;
	}

	private Card constructCard() {
		String name = generateName();
		char suit = generateSuit();
		int value = generateValue(name);
		if (name.equals("Number")) {
			name = Integer.toString(value);
		}

		Card card = new Card(name, suit, value);
		return card;
	}

	private int generateValue(String name) {

		int value;

		switch (name) {
		case "A":
			value = 11;
			break;
		case "K":
		case "Q":
		case "J":
			value = 10;
			break;
		case "Number":
			value = rnd.nextInt(9) + 1;
			break;
		default:
			value = 0;
		}
		;
		return value;
	}

	private String generateName() {

		String name;

		switch (rnd.nextInt(5) + 1) {
		case 1:
			name = "Number";
			break;
		case 2:
			name = "J";
			break;
		case 3:
			name = "Q";
			break;
		case 4:
			name = "K";
			break;
		case 5:
			name = "A";
			break;
		default:
			name = "Invalid card";
		}
		;

		return name;

	}

	private char generateSuit() {

		char suit;
		switch (rnd.nextInt(4) + 1) {
		case 1:
			suit = '\u2663';
			break;
		case 2:
			suit = '\u2666';
			break;
		case 3:
			suit = '\u2665';
			break;
		case 4:
			suit = '\u2660';
			break;
		default:
			suit = '*';
		}
		;

		return suit;

	}

	public void drawCard() {
		int draw = rnd.nextInt(deck.size());
		player.add(deck.get(draw));
		deck.remove(deck.get(draw));
		showCards();
		showScore();

		if (deck.isEmpty()) {
			System.out.println("No more cards");
		}

//		System.out.println(draw);
//		System.out.println(deck.size());

	}

	public void showScore() {
		countPlayerScore();

		if (playerScore > 21) {
			for (Card card : player) {
				if (card.getName().equals("A")) {
					card.setValue(1);

				}
			}
			countPlayerScore();

		}

		if (playerScore > 21) {
			playerBurned = true;

			System.out.println();
			System.out.println("You got burned");
			noMoreCards();

		} else {
			playerBurned = false;
			System.out.println();
			System.out.println("Your score: " + playerScore);
			System.out.println();
		}

	}

	public void countPlayerScore() {
		playerScore = 0;
		for (int i = 0; i < player.size(); i++) {
			playerScore += player.get(i).getValue();
		}

	}

	public void showCards() {

		System.out.println("Your hand: ");
		player.forEach(System.out::println);
		  
	}	
		

	public void noMoreCards() {

		programDraw();

//		if ((playerScore > programScore) && (playerBurned == false)) {
//			System.out.println("Congratulations you won!");
//			System.out.println("Your score: " + playerScore);
//			System.out.println("Computer score: " + programScore);
//
//		}
//		if ((playerScore > programScore) && (playerBurned == true)) {
//			System.out.println("Better luck next time, you lost");
//			System.out.println("Your score: " + playerScore);
//			System.out.println("Computer score: " + programScore);
//
//		}
//		if ((programScore > playerScore) && (programBurned == false)) {
//
//			System.out.println("Better luck next time, you lost");
//			System.out.println("Your score: " + playerScore);
//			System.out.println("Computer score: " + programScore);
//		}
//		if ((programScore > playerScore) && (programBurned == true)) {
//
//			System.out.println("Congratulations you won!");
//			System.out.println("Your score: " + playerScore);
//			System.out.println("Computer score: " + programScore);
//
//		}
//		if (((programScore == playerScore && programBurned == false && playerBurned == false))
//				|| ((programScore == playerScore && programBurned == true && playerBurned == true))) {
//
//			System.out.println("It's a tie!");
//			System.out.println("You both got: " + playerScore);
//
//		}
//		if (((playerScore > programScore && programBurned == true && playerBurned == true))
//				|| (playerScore < programScore && programBurned == true && playerBurned == true)) {
//			System.out.println("It's a tie!");
//			System.out.println("Since you both burned");
//			System.out.println("Your score: " + playerScore);
//			System.out.println("Computer score: " + programScore);
//		}

		if (playerScore > programScore) {
			if (playerBurned) {
				System.out.println("Better luck next time, you lost");
			} else {
				System.out.println("Congratulations you won!");
			}
		} else if (playerScore < programScore) {
			if (programBurned) {
				System.out.println("Congratulations you won!");
			} else {
				System.out.println("Better luck next time, you lost");
			}
		} else {
			if (playerBurned && programBurned) {
				System.out.println("It's a tie!");
				System.out.println("Since you both burned");
				System.out.println("Both got: " + playerScore);
				System.out.println();
			} else {
				System.out.println("It's a tie!");
				System.out.println("You both got: " + playerScore);
				System.out.println();
				return;
			}
		}

		System.out.println("Your score: " + playerScore);
		System.out.println("Computer score: " + programScore);
		System.out.println();

	}

	public void programDraw() {

		while (programScore < 15) {

			int programdraw = rnd.nextInt(deck.size());
			program.add(deck.get(programdraw));
			deck.remove(deck.get(programdraw));

			countProgramScore();

			if (programScore > 21) {
				for (Card card : program) {
					if (card.getName().equals("A")) {
						card.setValue(1);

					}
				}
				countProgramScore();

			}

		}
		programBurned = (programScore > 21) ? true : false;
	}

	public void countProgramScore() {
		programScore = 0;
		for (int i = 0; i < program.size(); i++) {
			programScore += program.get(i).getValue();
		}

	}

}
