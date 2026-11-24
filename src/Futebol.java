import java.util.Random;
import java.util.Scanner;

public class Futebol {

    static Scanner entrada = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        String nome;
        int habilidade = 40;
        int gols = 0;
        int saldo = 400;
        int partidas = 0;
        int opcao;

        int[] historico = new int[5];
        int quantidade = 0;

        System.out.print("Digite o nome do jogador: ");
        nome = entrada.nextLine();

        do {

            opcao = exibirMenu(nome, habilidade, gols, saldo);

            switch (opcao) {

                case 1:
                    mostrarJogador(nome, habilidade, gols, saldo, partidas);
                    break;

                case 2:

                    if (saldo < 200) {
                        System.out.println("Saldo insuficiente!");
                    } else if (habilidade == 100) {
                        System.out.println("Habilidade no maximo!");
                    } else {

                        saldo = saldo - 200;

                        int sorteio = random.nextInt(10) + 1;

                        System.out.println("Sorteio: " + sorteio);

                        habilidade = treinar(habilidade, sorteio);

                        if (sorteio >= 5) {
                            System.out.println("Treinamento concluido!");
                        } else {
                            System.out.println("Treinamento falhou!");
                        }

                        System.out.println("Habilidade: " + habilidade);
                        System.out.println("Saldo: R$ " + saldo);
                    }

                    break;

                case 3:

                    System.out.println("Escolha a estrategia:");
                    System.out.println("1 - Defensiva");
                    System.out.println("2 - Equilibrada");
                    System.out.println("3 - Ofensiva");

                    int estrategia = entrada.nextInt();

                    int bonus = 0;

                    if (estrategia == 1) {
                        bonus = 0;
                    } else if (estrategia == 2) {
                        bonus = 5;
                    } else if (estrategia == 3) {
                        bonus = 10;
                    } else {
                        System.out.println("Estrategia invalida!");
                        break;
                    }

                    int sorte = random.nextInt(11);

                    int desempenho = calcularDesempenho(habilidade, bonus, sorte);

                    int golsPartida = calcularGols(desempenho);
                    int premio = calcularPremio(desempenho);

                    partidas++;
                    gols = gols + golsPartida;
                    saldo = saldo + premio;

                    System.out.println("Sorte: " + sorte);
                    System.out.println("Desempenho: " + desempenho);

                    if (desempenho >= 90) {
                        System.out.println("Resultado: Vitoria!");
                    } else if (desempenho >= 75) {
                        System.out.println("Resultado: Vitoria!");
                    } else if (desempenho >= 60) {
                        System.out.println("Resultado: Empate!");
                    } else {
                        System.out.println("Resultado: Derrota!");
                    }

                    System.out.println("Gols: " + golsPartida);
                    System.out.println("Premio: R$ " + premio);
                    System.out.println("Saldo atual: R$ " + saldo);

                    if (quantidade < 5) {

                        historico[quantidade] = golsPartida;
                        quantidade++;

                    } else {

                        for (int i = 0; i < 4; i++) {
                            historico[i] = historico[i + 1];
                        }

                        historico[4] = golsPartida;
                    }

                    break;

                case 4:

                    System.out.println("===== RESULTADOS =====");
                    System.out.println("Partidas: " + partidas);
                    System.out.println("Gols acumulados: " + gols);
                    System.out.println("Saldo: R$ " + saldo);

                    System.out.println("Historico:");

                    for (int i = 0; i < quantidade; i++) {
                        System.out.println("Partida " + (i + 1) + ": "
                                + historico[i] + " gols");
                    }

                    break;

                case 0:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }

    public static int exibirMenu(String nome, int habilidade,
                                 int gols, int saldo) {

        System.out.println("\n===== JOGADOR DE FUTEBOL =====");
        System.out.println("Jogador: " + nome);
        System.out.println("Habilidade: " + habilidade);
        System.out.println("Gols: " + gols);
        System.out.println("Saldo: R$ " + saldo);

        System.out.println("\n1 - Visualizar jogador");
        System.out.println("2 - Treinar jogador");
        System.out.println("3 - Participar de partida");
        System.out.println("4 - Consultar resultados");
        System.out.println("0 - Sair");

        System.out.print("Escolha: ");

        return entrada.nextInt();
    }

    public static void mostrarJogador(String nome, int habilidade,
                                      int gols, int saldo, int partidas) {

        System.out.println("\n===== DADOS DO JOGADOR =====");
        System.out.println("Nome: " + nome);
        System.out.println("Habilidade: " + habilidade);
        System.out.println("Gols: " + gols);
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("Partidas: " + partidas);
    }

    public static int treinar(int habilidade, int sorteio) {

        if (sorteio >= 5) {
            habilidade = habilidade + 10;
        }

        if (habilidade > 100) {
            habilidade = 100;
        }

        return habilidade;
    }

    public static int calcularDesempenho(int habilidade,
                                         int bonus, int sorte) {

        return habilidade + bonus + sorte;
    }

    public static int calcularGols(int desempenho) {

        if (desempenho >= 90) {
            return 3;
        } else if (desempenho >= 75) {
            return 2;
        } else if (desempenho >= 60) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int calcularPremio(int desempenho) {

        if (desempenho >= 90) {
            return 800;
        } else if (desempenho >= 75) {
            return 600;
        } else if (desempenho >= 60) {
            return 300;
        } else {
            return 100;
        }
    }
}
