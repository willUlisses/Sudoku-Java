import Model.Square;
import Model.SudokuBoard;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    private final static Scanner scanner = new Scanner(System.in);

    private static SudokuBoard board;

    private final static int BOARD_LIMIT = 9;

    public static void main(String[] args) {
        final Map<String, String> positions = Stream.of(args)
                .collect(Collectors.toMap(
                        k -> k.split(";")[0], //pega a linha e a coluna (posições como chave)
                        v -> v.split(";")[1] //pega o expectedContent e se ele é fixo ou não
                ));
    int option = 0;
    while (true) {
        System.out.println("Selecione uma das opções a seguir:");
        System.out.println("1 - Iniciar um novo jogo");
        System.out.println("2 - Colocar um novo número");
        System.out.println("3 - Remover um número");
        System.out.println("4 - Visualizar jogo atual");
        System.out.println("5 - Verificar status do jogo");
        System.out.println("6 - Limpar jogo");
        System.out.println("7 - Finalizar Jogo");
        System.out.println("8 - Sair");

        option = scanner.nextInt();
        /*switch (option) {
            case 1 -> startGame(positions);
            case 2 ->
            case 3 ->
            case 4 ->
            case 5 ->
            case 6 ->
            case 7 ->
            case 8 ->
            default -> System.out.println("Opção inválida, tente novamente uma das opções listadas no menu");
        }*/
    }
    }

    private static void startGame(final Map<String, String> positions) {
        if (Objects.nonNull(board)) {
            System.out.println("O jogo já foi iniciado");
            return;
        }

        List<List<Square>> squares = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i ++) {
            squares.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++){
                 String squareConfig = positions.get("%s, %s".formatted(i,j));
                 Integer expectedContent = Integer.parseInt(squareConfig.split(",")[0]);
                 boolean squareStatus = Boolean.parseBoolean(squareConfig.split(",")[1]);
                 Square square = new Square(expectedContent, squareStatus);
                 squares.get(i).add(square);
            }
        }
        board = new SudokuBoard(squares);
        System.out.println("O jogo foi iniciado com sucesso!");
    }
}