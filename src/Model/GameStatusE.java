package Model;

public enum GameStatusE {
    NON_STARTED("Não iniciado"),
    INCOMPLETE("Jogo Incompleto"),
    COMPLETE("Jogo finalizado!");

    private final String label;
    private GameStatusE(final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
