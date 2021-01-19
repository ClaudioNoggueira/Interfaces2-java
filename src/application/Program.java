package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			System.out.println("Entre com os dados do contrato.");
			System.out.print("Número: ");
			int number = input.nextInt();
			System.out.print("Data (DD/MM/AAAA): ");
			Date date = sdf.parse(input.next());
			System.out.print("Valor do contrato : R$ ");
			double value = input.nextDouble();

			Contract contract = new Contract(number, date, value);

			System.out.print("\nNúmero de parcelas: ");
			int n = input.nextInt();

			ContractService contractService = new ContractService(new PaypalService());

			contractService.processContract(contract, n);

			System.out.println("\nPARCELAS:");
			int i = 1;
			for (Installment item : contract.getInstallments()) {
				System.out.println(i + "ª " + item);
				i++;
			}
		} catch (InputMismatchException e) {
			System.out.println();
			System.out.println("Erro de compatibilidade. Foi inserido um tipo de dado inadequado para operação.");
			System.out.println("Reinicie o programa para tentar novamente.");
		} catch (ParseException e) {
			System.out.println();
			System.out.println("Não foi possível converter a data.");
			System.out.println("Certifique de seguir o padrão (DD/MM/AAAA). Ex: 31/12/2021");
			System.out.println("Reinicie o programa para tentar novamente.");
		} finally {
			if (input != null)
				input.close();
		}
	}
}
