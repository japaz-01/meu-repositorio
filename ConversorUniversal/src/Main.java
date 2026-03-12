import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Conversor> historico = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    private static String nomeUsuario = "Usuario";

    private static final String[] MENU_PRINCIPAL = {
        "Nova Conversao   (Cadastrar)",
        "Ver Historico    (Consultar)",
        "Editar Conversao (Alterar)",
        "Remover Conversao(Deletar)",
        "Sair"
    };

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("      CONVERSOR UNIVERSAL         ");
        System.out.println("==================================");

        System.out.print("Digite seu nome: ");
        nomeUsuario = sc.nextLine().trim();

        int opcao;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            for (int i = 0; i < MENU_PRINCIPAL.length; i++) {
                System.out.println("[" + (i + 1) + "] " + MENU_PRINCIPAL[i]);
            }

            opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1: menuNovaConversao();   break;
                case 2: menuVerHistorico();    break;
                case 3: menuEditarConversao(); break;
                case 4: menuRemoverConversao();break;
                case 5: System.out.println("Ate logo, " + nomeUsuario + "!"); break;
                default: System.out.println("Opcao invalida.");
            }
        } while (opcao != 5);

        sc.close();
    }

    private static void menuNovaConversao() {
        System.out.println("\n--- NOVA CONVERSAO ---");
        System.out.println("[1] Temperatura");
        System.out.println("[2] Energia");
        int tipo = lerInteiro("Escolha: ");

        boolean tipoValido = (tipo == 1 || tipo == 2);
        if (!tipoValido) {
            System.out.println("Tipo invalido.");
            return;
        }

        if (tipo == 1) {
            novaConversaoTermica();
        } else {
            novaConversaoEnergetica();
        }
    }

    private static void novaConversaoTermica() {
        System.out.println("\n-- Temperatura --");
        for (int i = 0; i < ConversorTermico.OPCOES.length; i++) {
            System.out.println("[" + (i + 1) + "] " + ConversorTermico.OPCOES[i]);
        }
        int sub = lerInteiro("Conversao: ");
        if (sub < 1 || sub > 4) { System.out.println("Opcao invalida."); return; }

        double val = lerDouble("Valor de entrada: ");

        ConversorTermico ct = new ConversorTermico(val, sub, nomeUsuario);
        ct.calcularConversao();
        historico.add(ct);

        System.out.println("\nConversao salva!");
        System.out.println(ct.exibirResumo());
    }

    private static void novaConversaoEnergetica() {
        System.out.println("\n-- Energia --");
        for (int i = 0; i < ConversorEnergetico.OPCOES.length; i++) {
            System.out.println("[" + (i + 1) + "] " + ConversorEnergetico.OPCOES[i]);
        }
        int sub = lerInteiro("Conversao: ");
        if (sub < 1 || sub > 4) { System.out.println("Opcao invalida."); return; }

        double val = lerDouble("Valor de entrada: ");
        double carga = 1.0;

        if (sub >= 3) {
            carga = lerDoubleComPadrao("Carga em Coulombs (Enter = 1 C): ", 1.0);
        }

        ConversorEnergetico ce = new ConversorEnergetico(val, sub, carga, nomeUsuario);
        ce.calcularConversao();
        historico.add(ce);

        System.out.println("\nConversao salva!");
        System.out.println(ce.exibirResumo());
    }

    private static void menuVerHistorico() {
        System.out.println("\n--- HISTORICO ---");

        if (historico.isEmpty()) {
            System.out.println("Nenhuma conversao registrada.");
            return;
        }

        int idx = 1;
        for (Conversor c : historico) {
            System.out.println("\nRegistro #" + idx++);
            System.out.println(c.exibirResumo());
            System.out.println("----------------------");
        }
        System.out.println("Total: " + historico.size() + " registro(s)");
    }

    private static void menuEditarConversao() {
        System.out.println("\n--- EDITAR CONVERSAO ---");

        if (historico.isEmpty()) {
            System.out.println("Historico vazio.");
            return;
        }

        listarIndices();
        int idx = lerInteiro("Numero do registro: ") - 1;

        if (idx < 0 || idx >= historico.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        Conversor c = historico.get(idx);
        double novoVal = lerDouble("Novo valor de entrada: ");
        c.setValorEntrada(novoVal);
        c.calcularConversao();

        System.out.println("\nRegistro atualizado!");
        System.out.println(c.exibirResumo());
    }

    private static void menuRemoverConversao() {
        System.out.println("\n--- REMOVER CONVERSAO ---");

        if (historico.isEmpty()) {
            System.out.println("Historico vazio.");
            return;
        }

        listarIndices();
        int idx = lerInteiro("Numero do registro: ") - 1;

        if (idx < 0 || idx >= historico.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        Conversor removido = historico.remove(idx);
        String tipo = (removido instanceof ConversorTermico) ? "Termica" : "Energetica";
        System.out.println("Conversao " + tipo + " removida.");
    }

    private static void listarIndices() {
        int idx = 1;
        for (Conversor c : historico) {
            String tipo = (c instanceof ConversorTermico) ? "[Temp]" : "[Enrg]";
            System.out.println("[" + idx++ + "] " + tipo + " Entrada: " + c.getValorEntrada() + " -> Saida: " + c.getValorSaida());
        }
    }

    private static int lerInteiro(String prompt) {
        int valor = 0;
        boolean valido = false;
        do {
            try {
                System.out.print(prompt);
                valor = sc.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero inteiro valido.");
                sc.nextLine();
            }
        } while (!valido);
        sc.nextLine();
        return valor;
    }

    private static double lerDouble(String prompt) {
        double valor = 0;
        boolean valido = false;
        do {
            try {
                System.out.print(prompt);
                valor = sc.nextDouble();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero valido.");
                sc.nextLine();
            }
        } while (!valido);
        sc.nextLine();
        return valor;
    }

    private static double lerDoubleComPadrao(String prompt, double padrao) {
        System.out.print(prompt);
        String linha = sc.nextLine().trim();
        return linha.isEmpty() ? padrao : Double.parseDouble(linha.replace(",", "."));
    }
}
