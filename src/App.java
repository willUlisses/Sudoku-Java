import Model.Square;
import Model.SudokuBoard;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Util.BoardTemplate.BOARD_TEMPLATE;


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
        switch (option) {
            case 1 -> startGame(positions);
            case 2 -> inputNumber();
            case 3 -> removeNumber();
            case 4 -> showGame();
            case 5 -> showGameStatus();
            case 6 -> clearGame();
            case 7 -> finishGame();
            case 8 -> {
                System.exit(0);
                scanner.close();
            }
            default -> System.out.println("Opção inválida, tente novamente uma das opções listadas no menu");
        }
    }
    }

    private static void startGame(final Map<String, String> positions) {
        if (Objects.nonNull(board)) {
            System.out.println("O jogo já foi iniciado");
            return;
        }

        List<List<Square>> squares = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            squares.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++){
                String squareConfig = positions.get("%s,%s".formatted(i,j));
                Integer expectedContent = Integer.parseInt(squareConfig.split(",")[0]);
                Boolean squareStatus = Boolean.parseBoolean(squareConfig.split(",")[1]);
                Square square = new Square(expectedContent, squareStatus);
                squares.get(i).add(square);
            }
        }
        board = new SudokuBoard(squares);
        System.out.println("O jogo foi iniciado com sucesso!");
    }

    public static void inputNumber() {
        if (Objects.isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.println("Informe a linha da posição que você quer modificar");
        int row = runUntilValueIsValid(0,8);
        System.out.println("Agora informe a coluna dessa posição:");
        int col = runUntilValueIsValid(0,8);
        System.out.println("Finalmente, informe o valor que será inserido:");
        int value = runUntilValueIsValid(1,9);
        if (!board.changeValue(row,col,value)){
            System.out.printf("A posição (%s,%s) tem um valor fixo\n", row, col);
        }
    }

    public static void removeNumber() {
        if (Objects.isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        System.out.println("Informe a linha da posição que você quer remover");
        int row = runUntilValueIsValid(0,8);
        System.out.println("Agora informe a coluna dessa posição:");
        int col = runUntilValueIsValid(0,8);
        if (!board.clearValue(row,col)){
            System.out.printf("A posição (%s,%s) tem um valor fixo\n", row, col);
        }
    }

    private static void showGame(){
        if (Objects.isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        Object[] args = new Object[81]; //existem 81 posições a serem exibidas, pois é um board de 9 x 9
        int argPos = 0;
        for (int i = 0; i < BOARD_LIMIT; i++) { //itera sobre a linha
            for (int j = 0; j < BOARD_LIMIT; j++) { //avança sobre cada coluna
                Square currentSquare = board.getBoard().get(i).get(j); //vai pegar cada quadrado da linha i
                args[argPos++] = " " + (Objects.isNull(currentSquare.getContent()) ? " " : currentSquare.getContent());
                // a linha acima vai exibir o quadrado com o seu valor se ele não for nulo
            }
        }
        System.out.println("A situação atual do jogo é a seguinte:");
        System.out.printf((BOARD_TEMPLATE) + "\n", args);
    }

    public static void showGameStatus() {
        if (Objects.isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        System.out.println("Atualmente o jogo se encontra como: " + board.getStatus().getLabel());
        if (board.hasErrors()) {
            System.out.println("O seu jogo contém erros:");
        } else {
            System.out.println("Atualmente o jogo não contém erros");
        }
    }

    public static void clearGame(){
        if (Objects.isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        System.out.println("Você tem certeza que deseja que o jogo seja limpo e o progresso seja perdido? (digite 'y' ou 'n')");
        String choice = scanner.next();
        while (!choice.equalsIgnoreCase("y") || !choice.equalsIgnoreCase("n")){
            System.out.println("Informe 'y' ou 'n' para confirmar ou recusar respectivamente");
            choice = scanner.next();
        }

        if (choice.equalsIgnoreCase("y")){
            board.reset();
        }
    }

    public static void finishGame() {
        if (Objects.isNull(board)){
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        if (board.gameIsFinished()) {
            System.out.println("O board não contém nenhum erro e seu jogo foi finalizado com sucesso!!");
            showGame();
            board = null;
        } else if (board.hasErrors()) {
            System.out.println("Seu jogo contém erros, verifique seu board.");
        } else {
            System.out.println("Você esqueceu algum espaço, verifique seu board");
        }
    }

    private static int runUntilValueIsValid(final int MIN, final int MAX) {
        int value = scanner.nextInt();
        while (value < MIN || value > MAX) {
            System.out.printf("informe um número entre %s e %s\n", MIN, MAX);
            value = scanner.nextInt();
        }
        return value;
    }
}