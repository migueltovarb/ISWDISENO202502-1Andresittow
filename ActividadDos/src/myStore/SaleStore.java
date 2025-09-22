package myStore;

import java.util.Scanner;

public class SaleStore {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("¡Bienvenido!");
		System.out.println("Ingrese la cantidad de productos a comprar.");
		int quantity = sc.nextInt();
		final double DISCOUNT_TYPE_1 = 0.10; 
		final double DISCOUNT_TYPE_2 = 0.20; 
		final double DISCOUNT_TYPE_3 = 0.05; 
		final double SPECIAL_DISCOUNT = 0.10;
		
		while (quantity < 1) {
			System.out.println("La cantidad debe ser al menos 1. Ingrese de nuevo:");
		    quantity = sc.nextInt();
		}
		
		double total = 0;
		double withoutDiscount = 0;
		double[] price = new double[quantity];
		int[] typeProduct = new int[quantity];
		String productName;
		double[] finalPrice = new double[quantity]; 
		
		for (int i = 0; i < quantity; i++) {
			System.out.println("Ingrese el nombre del producto " + (i + 1) + ", por favor:");
			productName = sc.next();
			
			
			System.out.println("Ingrese el tipo (1: ropa, 2: tecnología, 3: alimentos) " + (i + 1) + ", por favor:");
			typeProduct[i] = sc.nextInt();
			
			System.out.println("Ingrese el precio del producto " + (i + 1) + ", por favor:");
			price[i] = sc.nextDouble();
			
			switch (typeProduct[i]) {
			case 1:
				finalPrice[i] = price[i] * (1 - DISCOUNT_TYPE_1);
                break;
            case 2:
            	finalPrice[i] = price[i] * (1 - DISCOUNT_TYPE_2);
                break;
            case 3:
            	finalPrice[i] = price[i] * (1 - DISCOUNT_TYPE_3);
                break;
            default:
                System.out.println("Tipo inválido, no aplica descuento");
                finalPrice[i] = price[i];
			}
			
			total += finalPrice[i];
			withoutDiscount += price[i];
		}
		
		System.out.println("Total sin descuento: $" + withoutDiscount + ".");
		
		if (total > 600.00) {
			total *= (1 - SPECIAL_DISCOUNT);
			System.out.println("Se aplicó un descuento especial del 10%");
			System.out.println("Su total a pagar es de: $" + total+ ".");
		}
		
		double purchaseSavings = withoutDiscount - total;
		System.out.println("Se ahorró: $" + purchaseSavings + ".");	
	}
}