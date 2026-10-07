package com.movieticketbooking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MovieTicketFrame extends JFrame {

    private final MovieService movieService = new MovieService();
    private final ShowService showService = new ShowService();
    private final SeatService seatService = new SeatService();
    private final BookingService bookingService = new BookingService();
    private final JComboBox<Movie> moviePicker = new JComboBox<>();
    private final JComboBox<Show> showPicker = new JComboBox<>();
    private final JTextField customerName = new JTextField(18);
    private final JPanel seatPanel = new JPanel(new GridLayout(4, 5, 8, 8));
    private final Set<Integer> selectedSeats = new LinkedHashSet<>();
    private final JToggleButton[] seatButtons = new JToggleButton[20];
    private final DefaultTableModel movieRows = new DefaultTableModel(
            new Object[] {"Movie", "Genre", "Language", "Price"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final DefaultTableModel bookingRows = new DefaultTableModel(
            new Object[] {"Booking ID", "Customer", "Movie", "Theatre", "Show", "Seats", "Total"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public MovieTicketFrame() {
        super("Movie Ticket Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(780, 570));
        setSize(900, 660);
        setLocationRelativeTo(null);
        buildInterface();
        refreshMovies();
        refreshBookings();
    }

    private void buildInterface() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Movies", createMoviesPanel());
        tabs.addTab("Book tickets", createBookingPanel());
        tabs.addTab("Bookings", createBookingsPanel());
        add(tabs);
    }

    private JPanel createMoviesPanel() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("Now showing");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        panel.add(title, BorderLayout.NORTH);
        JTable table = new JTable(movieRows);
        table.setRowHeight(30);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        JButton refreshButton = new JButton("Refresh movies");
        refreshButton.addActionListener(event -> refreshMovies());
        JPanel footer = new JPanel(new BorderLayout());
        footer.add(new JLabel("Choose a movie in Book tickets to continue."), BorderLayout.WEST);
        footer.add(refreshButton, BorderLayout.EAST);
        panel.add(footer, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createBookingPanel() {
        JPanel panel = new JPanel(new BorderLayout(16, 16));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel fields = new JPanel(new GridLayout(3, 2, 10, 10));
        fields.add(new JLabel("Customer name"));
        fields.add(customerName);
        fields.add(new JLabel("Movie"));
        fields.add(moviePicker);
        fields.add(new JLabel("Theatre and show time"));
        fields.add(showPicker);
        panel.add(fields, BorderLayout.NORTH);

        JPanel seats = new JPanel(new BorderLayout(8, 8));
        seats.setBorder(BorderFactory.createTitledBorder("Select seats (green = available, red = booked)"));
        seatPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        for (int index = 0; index < seatButtons.length; index++) {
            int seatNumber = index + 1;
            JToggleButton button = new JToggleButton("" + seatNumber);
            button.setOpaque(true);
            button.setBackground(new Color(218, 238, 224));
            button.addActionListener(event -> toggleSeat(seatNumber));
            seatButtons[index] = button;
            seatPanel.add(button);
        }
        seats.add(seatPanel, BorderLayout.CENTER);
        JLabel screen = new JLabel("SCREEN", JLabel.CENTER);
        screen.setOpaque(true);
        screen.setBackground(new Color(42, 57, 68));
        screen.setForeground(Color.WHITE);
        screen.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        seats.add(screen, BorderLayout.NORTH);
        panel.add(seats, BorderLayout.CENTER);

        JButton bookButton = new JButton("Confirm booking");
        bookButton.addActionListener(event -> confirmBooking());
        JPanel footer = new JPanel(new BorderLayout());
        footer.add(new JLabel("Selected seats are reserved after payment confirmation."), BorderLayout.WEST);
        footer.add(bookButton, BorderLayout.EAST);
        panel.add(footer, BorderLayout.SOUTH);

        moviePicker.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Movie) {
                    Movie movie = (Movie) value;
                    setText(movie.name + " - Rs." + movie.price);
                }
                return this;
            }
        });
        showPicker.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Show) {
                    Show show = (Show) value;
                    setText(show.theatreName + " - " + show.time);
                }
                return this;
            }
        });
        moviePicker.addActionListener(event -> refreshShows());
        refreshShows();
        refreshSeatButtons();
        return panel;
    }

    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("Your bookings");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        panel.add(title, BorderLayout.NORTH);
        JTable table = new JTable(bookingRows);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        JButton cancelButton = new JButton("Cancel selected booking");
        cancelButton.addActionListener(event -> cancelBooking(table));
        panel.add(cancelButton, BorderLayout.SOUTH);
        return panel;
    }

    private void refreshMovies() {
        movieRows.setRowCount(0);
        moviePicker.removeAllItems();
        for (Movie movie : movieService.movies) {
            movieRows.addRow(new Object[] {movie.name, movie.genre, movie.language, "Rs." + movie.price});
            moviePicker.addItem(movie);
        }
        refreshShows();
    }

    private void refreshShows() {
        Movie selectedMovie = (Movie) moviePicker.getSelectedItem();
        showPicker.removeAllItems();
        if (selectedMovie != null) {
            for (Show show : showService.shows) {
                if (show.movieName.equals(selectedMovie.name)) {
                    showPicker.addItem(show);
                }
            }
        }
        showPicker.setEnabled(showPicker.getItemCount() > 0);
    }

    private void toggleSeat(int seatNumber) {
        JToggleButton button = seatButtons[seatNumber - 1];
        if (button.isSelected()) {
            selectedSeats.add(seatNumber);
            button.setBackground(new Color(248, 216, 159));
        } else {
            selectedSeats.remove(seatNumber);
            button.setBackground(new Color(218, 238, 224));
        }
    }

    private void refreshSeatButtons() {
        selectedSeats.clear();
        for (int index = 0; index < seatButtons.length; index++) {
            if (seatButtons[index] == null) {
                continue;
            }
            Seat seat = seatService.seats.get(index);
            seatButtons[index].setSelected(false);
            seatButtons[index].setEnabled(!seat.booked);
            seatButtons[index].setBackground(seat.booked
                    ? new Color(230, 190, 190) : new Color(218, 238, 224));
        }
    }

    private void confirmBooking() {
        String name = customerName.getText().trim();
        Movie movie = (Movie) moviePicker.getSelectedItem();
        Show show = (Show) showPicker.getSelectedItem();
        if (name.isEmpty() || movie == null || show == null || selectedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Enter a customer name, choose a show, and select at least one seat.",
                    "Incomplete booking", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ArrayList<Integer> seats = new ArrayList<>(selectedSeats);
        double total = movie.price * seats.size();
        int payment = JOptionPane.showConfirmDialog(this,
                "Confirm payment of Rs." + total + " for " + seats.size() + " seat(s)?",
                "Payment", JOptionPane.OK_CANCEL_OPTION);
        if (payment != JOptionPane.OK_OPTION) {
            return;
        }

        Booking booking = new Booking(bookingService.bookingCounter++, name, movie.name,
                show.theatreName, show.time, seats, total);
        bookingService.bookings.add(booking);
        for (int seatNumber : seats) {
            seatService.seats.get(seatNumber - 1).booked = true;
        }
        JOptionPane.showMessageDialog(this, "Booking confirmed. Your booking ID is " + booking.bookingId + ".");
        customerName.setText("");
        refreshSeatButtons();
        refreshBookings();
    }

    private void refreshBookings() {
        bookingRows.setRowCount(0);
        for (Booking booking : bookingService.bookings) {
            bookingRows.addRow(new Object[] {booking.bookingId, booking.customerName,
                    booking.movieName, booking.theatreName, booking.showTime,
                    booking.seats.toString(), "Rs." + booking.totalPrice});
        }
    }

    private void cancelBooking(JTable table) {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a booking to cancel.");
            return;
        }
        int bookingId = (Integer) bookingRows.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Cancel booking " + bookingId + "?", "Cancel booking", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        Booking bookingToCancel = null;
        for (Booking booking : bookingService.bookings) {
            if (booking.bookingId == bookingId) {
                bookingToCancel = booking;
                break;
            }
        }
        if (bookingToCancel != null) {
            for (int seatNumber : bookingToCancel.seats) {
                seatService.seats.get(seatNumber - 1).booked = false;
            }
            bookingService.bookings.remove(bookingToCancel);
        }
        refreshBookings();
        refreshSeatButtons();
    }
}