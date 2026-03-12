public class ConversorTermico extends Conversor {

    private int tipoConversao;
    private String descricao;
    private double fatorCorrecao;
    private boolean conversaoCritica;

    public static final String[] OPCOES = {
        "Celsius para Fahrenheit",
        "Celsius para Kelvin",
        "Fahrenheit para Celsius",
        "Kelvin para Fahrenheit"
    };

    public ConversorTermico() {
        super();
        this.tipoConversao = 1;
        this.fatorCorrecao = 0.0;
        this.conversaoCritica = false;
    }

    public ConversorTermico(double valorEntrada, int tipoConversao, String usuario) {
        super(valorEntrada, unidadeOrigem(tipoConversao), unidadeDestino(tipoConversao), usuario);
        this.tipoConversao = tipoConversao;
        this.descricao = OPCOES[tipoConversao - 1];
        this.fatorCorrecao = (tipoConversao == 2) ? 273.15 : 0.0;
        this.conversaoCritica = false;
    }

    private static String unidadeOrigem(int tipo) {
        switch (tipo) {
            case 1: case 2: return "C";
            case 3: return "F";
            case 4: return "K";
            default: return "?";
        }
    }

    private static String unidadeDestino(int tipo) {
        switch (tipo) {
            case 1: return "F";
            case 2: return "K";
            case 3: return "C";
            case 4: return "F";
            default: return "?";
        }
    }

    @Override
    public void calcularConversao() {
        double entrada = getValorEntrada();
        double resultado;

        switch (tipoConversao) {
            case 1: resultado = (entrada * 9.0 / 5.0) + 32; break;
            case 2: resultado = entrada + 273.15; break;
            case 3: resultado = (entrada - 32) * 5.0 / 9.0; break;
            case 4:
                resultado = (entrada >= 0) ? (entrada - 273.15) * 9.0 / 5.0 + 32 : Double.NaN;
                break;
            default: resultado = 0;
        }

        this.conversaoCritica = (tipoConversao == 4 && entrada < 0) ? true : false;
        setValorSaida(resultado);
    }

    @Override
    public String exibirResumo() {
        String aviso = conversaoCritica ? "\n  AVISO: Valor Kelvin negativo invalido!" : "";
        return "[TEMPERATURA] " + descricao + "\n" + super.exibirResumo() + aviso;
    }

    public int getTipoConversao() { return tipoConversao; }
    public void setTipoConversao(int t) {
        this.tipoConversao = t;
        this.descricao = (t >= 1 && t <= 4) ? OPCOES[t - 1] : "Desconhecida";
        setUnidadeOrigem(unidadeOrigem(t));
        setUnidadeDestino(unidadeDestino(t));
        this.fatorCorrecao = (t == 2) ? 273.15 : 0.0;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String d) { this.descricao = d; }

    public double getFatorCorrecao() { return fatorCorrecao; }
    public void setFatorCorrecao(double f) { this.fatorCorrecao = f; }

    public boolean isConversaoCritica() { return conversaoCritica; }
    public void setConversaoCritica(boolean c) { this.conversaoCritica = c; }
}
