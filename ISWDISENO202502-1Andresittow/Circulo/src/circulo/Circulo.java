package circulo;

public class Circulo {

	private double radio;
	
	public Circulo() {
		radio = 1.0;
	}
	
	public Circulo(double radio) {
		this.radio = radio;
	}

	public double getRadio() {
		return radio;
	}
	
	public void setRadio(double radio) {
		this.radio = radio;
	}
	
	public double getArea() {
        return Math.PI * Math.pow(radio, 2);
    }
	
	public double getPerimetro() {
		double perimetro = 2 * Math.PI * radio;
		return perimetro;
	}
	
	@Override
	public String toString() {
		return "Circulo [radio del circulo =" + radio + ", con área =" + getArea()
		+ ", y con perímetro =" + getPerimetro() + "]";
	}
}
