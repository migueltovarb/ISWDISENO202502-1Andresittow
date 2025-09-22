package author.book;

public class AuthorAndBookProgram {
    public static void main(String[] args) {
        Author author = new Author("Katerin", "kate@gmail.com", 'f');

        System.out.println(author);
        author.setEmail("natalia@gmail.com");
        System.out.println(author);

        Book book = new Book("It",  author, 20, 3);
        System.out.println(book);

        book.setQty(1);
        book.setPrice(25);
        System.out.println(book);
    }
}
