import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*JFrame janela = new JFrame("Jogo de Sudoku");

        janela.setBounds(300,100, 900, 700);

        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout());
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeTextField = new JTextField(20);

        painel.add(nomeLabel);
        painel.add(nomeTextField);
        janela.add(painel);


        janela.setVisible(true);
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);*/

        List<List<String>> espacos = new ArrayList<>();

        for (int i = 0; i <= 8; i++) { // indice pra adicionar as linhas
            List<String> linhas = new ArrayList<>(); // cada vez que recomeçar o loop cria uma linha nova
            for (int j = 0; j <= 8; j++) { // dps de criada a linha vamos adicionar 9 elementos nela
                linhas.add("l: " + (i + 1) + " c:" + (j + 1));
            }
            espacos.add(linhas); //adiciona essa linha na matriz "espacos"
        }

        for (int i = 0; i <= 8; i++) {
                System.out.println(espacos.get(i) + "\t"); //exibe a matriz
            System.out.println();
        }
    }
}