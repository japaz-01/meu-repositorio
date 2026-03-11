package geometria2;

import java.util.Scanner;

public class Calculadora {

    private double lado;
    private double base;
    private double altura;
    private double raio;
    private int opcao;

    public void menu() {
        System.out.println("1 - Area do Quadrado");
        System.out.println("2 - Area do Retangulo");
        System.out.println("3 - Area do Circulo");
        System.out.println("4 - Sair");
        System.out.print("Digite sua opcao: ");
    }

    public void executar() {
        Scanner scanner = new Scanner(System.in);

        opcao = scanner.nextInt();

        switch (opcao) {

            case 1:
                System.out.print("Digite o lado: ");
                lado = scanner.nextDouble();
                System.out.printf("Area do Quadrado = %.2f%n", lado * lado);
                break;

            case 2:
                System.out.print("Digite a base: ");
                base = scanner.nextDouble();
                System.out.print("Digite a altura: ");
                altura = scanner.nextDouble();
                System.out.printf("Area do Retangulo = %.2f%n", base * altura);
                break;

            case 3:
                System.out.print("Digite o raio: ");
                raio = scanner.nextDouble();
                System.out.printf("Area do Circulo = %.2f%n", 3.14 * (raio * raio));
                break;

            case 4:
                System.out.println("Encerrando...");
                break;

            default:
                System.out.println("Opcao invalida!");
                break;
        }

        scanner.close();
    }
}