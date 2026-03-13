import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {

    static ArrayList<String> listaNome = new ArrayList<>();
    static final String CAMINHO_ARQUIVO = "dados/meus_nomes.txt";

    public static void main(String[] args) {

        criarPasta();
        lerArquivo();

        Scanner teclado = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Listar nomes");
            System.out.println("2 - Adicionar nome");
            System.out.println("3 - Consultar nome");
            System.out.println("4 - Alterar nome");
            System.out.println("5 - Excluir nome");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            try {
                opcao = Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida.");
                continue;
            }

            switch (opcao) {
                case 1 -> listarNomes();
                case 2 -> adicionarNome(teclado);
                case 3 -> consultarNome(teclado);
                case 4 -> alterarNome(teclado);
                case 5 -> excluirNome(teclado);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opcao invalida.");
            }
        }

        teclado.close();
    }

    static void criarPasta() {
        File pasta = new File("dados");
        pasta.mkdir();
    }

    static void lerArquivo() {
        File arquivo = new File(CAMINHO_ARQUIVO);

        if (!arquivo.exists()) {
            System.out.println("Arquivo nao encontrado. Lista vazia.");
            return;
        }

        try {
            Scanner leitorArquivo = new Scanner(arquivo);

            while (leitorArquivo.hasNextLine()) {
                String linha = leitorArquivo.nextLine().trim();
                if (!linha.isEmpty()) {
                    listaNome.add(linha);
                }
            }

            leitorArquivo.close();
            System.out.println(listaNome.size() + " nome(s) carregado(s).");

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    static void salvarArquivo() {
        try {
            FileWriter arquivoTexto = new FileWriter(CAMINHO_ARQUIVO);
            PrintWriter gravador = new PrintWriter(arquivoTexto);

            for (String nome : listaNome) {
                gravador.println(nome);
            }

            gravador.close();
            System.out.println("Arquivo salvo.");

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    static void listarNomes() {
        if (listaNome.isEmpty()) {
            System.out.println("Lista vazia.");
            return;
        }
        System.out.println("Nomes na lista:");
        for (int i = 0; i < listaNome.size(); i++) {
            System.out.println("[" + i + "] " + listaNome.get(i));
        }
    }

    static void adicionarNome(Scanner teclado) {
        System.out.print("Nome: ");
        String nome = teclado.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("Nome nao pode ser vazio.");
            return;
        }

        listaNome.add(nome);
        salvarArquivo();
        System.out.println("Nome adicionado.");
    }

    static void consultarNome(Scanner teclado) {
        System.out.print("Nome a consultar: ");
        String nome = teclado.nextLine().trim();

        if (listaNome.contains(nome)) {
            System.out.println("Encontrado na posicao " + listaNome.indexOf(nome) + ".");
        } else {
            System.out.println("Nome nao encontrado.");
        }
    }

    static void alterarNome(Scanner teclado) {
        listarNomes();
        if (listaNome.isEmpty()) return;

        System.out.print("Nome a alterar: ");
        String nomeAntigo = teclado.nextLine().trim();
        int indice = listaNome.indexOf(nomeAntigo);

        if (indice == -1) {
            System.out.println("Nome nao encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nomeNovo = teclado.nextLine().trim();

        if (nomeNovo.isEmpty()) {
            System.out.println("Nome nao pode ser vazio.");
            return;
        }

        listaNome.set(indice, nomeNovo);
        salvarArquivo();
        System.out.println("Nome alterado.");
    }

    static void excluirNome(Scanner teclado) {
        listarNomes();
        if (listaNome.isEmpty()) return;

        System.out.print("Nome a excluir: ");
        String nome = teclado.nextLine().trim();

        if (listaNome.contains(nome)) {
            listaNome.remove(nome);
            salvarArquivo();
            System.out.println("Nome removido.");
        } else {
            System.out.println("Nome nao encontrado.");
        }
    }
}
