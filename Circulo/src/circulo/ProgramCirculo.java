package circulo;

public class ProgramCirculo {
	public static void main(String[] args) {
		var circulo = new Circulo(5);
		
		System.out.println(circulo);
		circulo.getArea();
		circulo.getPerimetro();
		circulo.setRadio(7);
		circulo.getArea();
		
		System.out.println(circulo);
		
	}
}
