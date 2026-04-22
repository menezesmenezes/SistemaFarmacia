package main;

import dao.MedicamentoDAO;
import model.Medicamento;

import java.sql.Date;
import java.util.Scanner;

import db.Conexao;
import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        try{
            Connection conn = Conexao.conectar();
            System.out.println("Conectado com sucesso!" + conn);
        }catch (Exception e){
            System.out.println("Erro ao conectar:");
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        MedicamentoDAO dao = new MedicamentoDAO();

        int opcao;

        do {
            System.out.println("\n=== SISTEMA FARMÁCIA ===");
            System.out.println("1 - Cadastrar medicamento");
            System.out.println("2 - Listar medicamento");
            System.out.println("3 - Remover medicamento");
            System.out.println("4 - Sair");
            System.out.println("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("Digite o nome:");
                    String nome = sc.nextLine();

                    System.out.println("Digite a quantidade: ");
                    int quantidade = sc.nextInt();
                    sc.nextLine();

                    Date validade = null;
                    boolean dataValida = false;

                    while (!dataValida){
                        System.out.println("Digite validade (YYYY-MM-DD:)");
                        String data = sc.nextLine();

                        try {
                            validade = Date.valueOf(data);
                            dataValida = true;
                        }catch (Exception e) {
                            System.out.println("Data inválida! Tente novamente.");
                        }
                    }
                    Medicamento m = new Medicamento(nome, quantidade, validade);
                    dao.inserir(m);
                    break;
                case 2:
                    dao.listar();
                    break;
                case 3:
                    boolean removido = false;
                    while (!removido){
                        System.out.println("Digite o nome do medicamento para remover:");
                        String nomeRemover = sc.nextLine();
                        removido = dao.remover(nomeRemover);
                        if (!removido){
                            System.out.println("Tente novamente!");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        } while (opcao !=4);
        sc.close();
    }
}
