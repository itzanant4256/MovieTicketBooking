public class Movie {

    int id;
    String name;
    String genre;
    String language;
    double price;

    public Movie(int id, String name, String genre,
                 String language, double price) {

        this.id = id;
        this.name = name;
        this.genre = genre;
        this.language = language;
        this.price = price;
    }

    public void displayMovie() {

        System.out.println(
            id + ". " + name +
            " | Genre: " + genre +
            " | Language: " + language +
            " | Ticket: Rs." + price
        );
    }
}