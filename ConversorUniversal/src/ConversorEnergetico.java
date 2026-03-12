public class ConversorEnergetico extends Conversor {

    private int tipoConversao;
    private String descricao;
    private double cargaCoulombs;
    private String categoria;

    private static final double JOULES_POR_CALORIA = 4.184;

    public static final String[] OPCOES = {
        "Joules para Calorias",
        "Calorias para Joules",
        "Joules para Volts",
        "Volts para Calorias"
    };

    public ConversorEnergetico() {
        super();
        this.tipoConversao = 1;
        this.cargaCoulombs = 1.0;
        this.categoria = "Energia";
    }

    public ConversorEnergetico(double valorEntrada, int tipoConversao, double cargaCoulombs, String usuario) {
        super(valorEntrada, unidadeOrigem(tipoConversao), unidadeDestino(tipoConversao), usuario);
        this.tipoConversao = tipoConversao;
        this.descricao = OPCOES[tipoConversao - 1];
        this.cargaCoulombs = (tipoConversao >= 3) ? cargaCoulombs : 1.0;
        this.categoria = (tipoConversao >= 3) ? "Eletrica" : "Energia";
    }

    private static String unidadeOrigem(int tipo) {
        switch (tipo) {
            case 1: return "J";
            case 2: return "cal";
            case 3: return "J";
            case 4: return "V";
            default: return "?";
        }
    }

    private static String unidadeDestino(int tipo) {
        switch (tipo) {
            case 1: return "cal";
            case 2: return "J";
            case 3: return "V";
            case 4: return "cal";
            default: return "?";
        }
    }

    @Override
    public void calcularConversao() {
        double entrada = getValorEntrada();
        double resultado;

        switch (tipoConversao) {
            case 1: resultado = entrada / JOULES_POR_CALORIA; break;
            case 2: resultado = entrada * JOULES_POR_CALORIA; break;
            case 3: resultado = (cargaCoulombs != 0) ? entrada / cargaCoulombs : 0.0; break;
            case 4: resultado = (entrada * cargaCoulombs) / JOULES_POR_CALORIA; break;
            default: resultado = 0;
        }

        setValorSaida(resultado);
    }

    @Override
    public String exibirResumo() {
        String infoCarga = (tipoConversao >= 3) ? "\n  Carga   : " + cargaCoulombs + " C" : "";
        return "[" + categoria.toUpperCase() + "] " + descricao + "\n" + super.exibirResumo() + infoCarga;
    }

    public int getTipoConversao() { return tipoConversao; }
    public void setTipoConversao(int t) {
        this.tipoConversao = t;
        this.descricao = (t >= 1 && t <= 4) ? OPCOES[t - 1] : "Desconhecida";
        setUnidadeOrigem(unidadeOrigem(t));
        setUnidadeDestino(unidadeDestino(t));
        this.categoria = (t >= 3) ? "Eletrica" : "Energia";
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String d) { this.descricao = d; }

    public double getCargaCoulombs() { return cargaCoulombs; }
    public void setCargaCoulombs(double c) { this.cargaCoulombs = c; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String c) { this.categoria = c; }
}
