import UserInterface.screen.MainScreen;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UImain {
    public static void main(String[] args) {
        Map<String,String> gamePositions = Stream.of(args)
                .collect(Collectors.toMap(
                        k -> k.split(";")[0],// pega a posição i e j
                        v -> v.split(";")[1] // pega o valor e se é fixo
                ));

        MainScreen mainScreen = new MainScreen(gamePositions);
        mainScreen.buildMainScreen();

    }
}
