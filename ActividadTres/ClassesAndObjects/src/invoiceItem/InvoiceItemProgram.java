package invoiceItem;

public class InvoiceItemProgram {
    public static void main(String[] args) {
        InvoiceItem item = new InvoiceItem("HP-0177", "Computador HP mini Pro 400 G9", 2, 1200.0);

        System.out.println(item);
        System.out.println(item.getTotal());

        item.setQty(1);
        System.out.println(item.getTotal());
        System.out.println(item.getQty());

        item.setUnitPrice(1100.0);
        item.setQty(3);
        System.out.println(item);
        System.out.println(item.getTotal());

    }
}
