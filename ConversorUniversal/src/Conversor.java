import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Conversor {

    private double valorEntrada;
    private double valorSaida;
    private String unidadeOrigem;
    private String unidadeDestino;
    private String dataHora;
    private String usuario;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Conversor() {
        this.dataHora = LocalDateTime.now().format(FORMATTER);
        this.usuario = "Usuario";
    }

    public Conversor(double valorEntrada, String unidadeOrigem, String unidadeDestino, String usuario) {
        this.valorEntrada = valorEntrada;
        this.unidadeOrigem = unidadeOrigem;
        this.unidadeDestino = unidadeDestino;
        this.usuario = usuario;
        this.dataHora = LocalDateTime.now().format(FORMATTER);
    }

    public abstract void calcularConversao();

    public String exibirResumo() {
        return "  Entrada : " + valorEntrada + " " + unidadeOrigem + "\n" +
               "  Saida   : " + valorSaida + " " + unidadeDestino + "\n" +
               "  Data    : " + dataHora + "\n" +
               "  Usuario : " + usuario;
    }

    public double getValorEntrada() { return valorEntrada; }
    public void setValorEntrada(double v) {
        this.valorEntrada = v;
        this.dataHora = LocalDateTime.now().format(FORMATTER);
    }

    public double getValorSaida() { return valorSaida; }
    public void setValorSaida(double v) { this.valorSaida = v; }

    public String getUnidadeOrigem() { return unidadeOrigem; }
    public void setUnidadeOrigem(String u) { this.unidadeOrigem = u; }

    public String getUnidadeDestino() { return unidadeDestino; }
    public void setUnidadeDestino(String u) { this.unidadeDestino = u; }

    public String getDataHora() { return dataHora; }
    public void setDataHora(String d) { this.dataHora = d; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String u) { this.usuario = u; }
}
