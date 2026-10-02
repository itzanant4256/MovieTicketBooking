public class Show {

    int id;
    String movieName;
    String theatreName;
    String time;

    public Show(int id, String movieName,
                String theatreName, String time) {

        this.id = id;
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.time = time;
    }

    public void displayShow() {

        System.out.println(
            id + ". " + movieName +
            " | " + theatreName +
            " | Time: " + time
        );
    }
}