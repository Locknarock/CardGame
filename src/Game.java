import java.io.IOException;
import java.util.Scanner;

public class Game {
	Actions actions = new Actions();
	public Scanner sc = new Scanner(System.in);

	public void play() {

		while (true) {
			showMainMenu();
			int choice = sc.nextInt();
			if (choice == 0) {
				// Lines to exit cmd
				String os = System.getProperty("os.name").toLowerCase();

				if (os.contains("win")) {
					try {
						Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.exit(0);
				}
				break;
			}
			mainMenu(choice);
		}
		sc.close();
	}

	public void mainMenu(int choice) {

		switch (choice) {

		case 1:
			startGame();
			break;
		case 2:
			printRules();
			break;
		default:
			System.out.println("Wrong choice");
			break;
		}

	}

	public void printRules() {
		System.out.println("""
				The goal is to get 21 points.
				Jack/Queen/King are worth 10 points each.
				Ace is worth 11 points but if you have would have over 21 points the ace is then worth 1 point.
				If you go over 21 points you are burned and loose the round.
				The winner is the one who has the most points and is not burned.
				""");

	}

	public void startGame() {

		actions.constructDeck();

		while (true) {
			showGameMenu();
			int gameChoice = sc.nextInt();
			if (gameChoice == 3) {
				break;
			}
			gameMenu(gameChoice);

		}

	}

	public void gameMenu(int gameChoice) {

		switch (gameChoice) {

		case 1:
			actions.drawCard();
			break;
		case 2:
			actions.noMoreCards();
			break;
		case 4:
			actions.constructDeck();
			break;
		default:
			System.out.println("Blogas pasirinkimas");
			break;
		}

	}

	public void showMainMenu() {
		printLogo();
		System.out.println("""
				0 - Exit
				1 - Start game
				2 - Rules
				""");

	}

	public void printLogo() {

		char spade = '\u2660';
		char club = '\u2663';
		char diamond = '\u2666';
		char heart = '\u2665';
		System.out.println(" ___________");
		System.out.println("|           |");
		System.out.println("|           |");
		System.out.printf("| %s%s  21 %s%s |\n",spade,club,diamond,heart);
		System.out.println("|           |");
		System.out.println("|___________|");
		System.out.println();
	}

	public void showGameMenu() {
		System.out.println("""
				1 - Draw card
				2 - Enough cards
				3 - Go back
				4 - Play again
				""");
	}

}
