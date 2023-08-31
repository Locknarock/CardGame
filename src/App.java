import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class App {

	public static void main(String[] args) {

		System.setProperty("file.encoding", "UTF-8");
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));

		Game game = new Game();
		game.play();

	}

}
