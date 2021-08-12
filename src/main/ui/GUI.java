package ui;

import exceptions.ExistingMovieException;
import model.Movie;
import model.MovieList;
import persistence.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


// Citation: Used the following resources for information on Swing

// https://www.codejava.net/java-se/swing/java-swing-example-for-searching-and-sorting-a-collection-of-objects-
// using-jlist

// Used this stack overflow post to learn how to make JTable uneditable
// https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable

// Used this resource to make a JTable
// https://www.javatpoint.com/java-jtable

// Used this resource to add sort ability
// https://www.codejava.net/java-se/swing/6-techniques-for-sorting-jtable-you-should-know

// Used this resource for deleting selected row
// http://mauricemuteti.info/how-to-delete-or-remove-selected-row-from-jtable-in-netbeans-java-tutorial-source-code/

// Used this resource for understanding how to play sound
// https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator

public class GUI extends JFrame {

    private MovieList movieList;

    private final JButton addButton = new JButton("Add");
    private final JButton deleteButton = new JButton("Delete");
    private final JButton saveButton = new JButton("Save");
    private final JButton loadButton = new JButton("Load");

    private final JLabel nameLabel = new JLabel("Name");
    private final JLabel genreLabel = new JLabel("Genre");
    private final JLabel ratingLabel = new JLabel("Rating");

    private JTable table = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();

    private final JTextField nameField = new JTextField(10);
    private final JTextField genreField = new JTextField(10);
    private final JTextField ratingField = new JTextField(10);

    private File sound = new File("./data/sound.wav");
    private static final String JSON_STORE = "./data/movielist.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    Object[] row = new Object[3];

    // EFFECTS: Creates the JFrame with chosen settings
    public GUI() {
        super("Movie Tracker");
        setSize(600, 400);
        centreOnScreen();
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        movieList = new MovieList("Movie List");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        makeTable();
        setPanels();
        buttonPanel();
        scrollPane();
        buttonActions();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // EFFECTS: Adds buttons to the panel and adds the panel to the frame
    public void buttonPanel() {
        JPanel panel = new JPanel();
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(loadButton);
        panel.add(saveButton);
        add(panel);
    }

    // EFFECTS: Adds a scroll pane view
    public void scrollPane() {
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    // EFFECTS: Adds fields and labels
    public void setPanels() {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel1.add(nameLabel);
        panel1.add(nameField);
        panel2.add(genreLabel);
        panel2.add(genreField);
        panel3.add(ratingLabel);
        panel3.add(ratingField);
        add(panel1);
        add(panel2);
        add(panel3);
    }

    // EFFECTS: Creates a JTable with name, genre and rating
    public void makeTable() {
        Object[] columns = {"Name", "Genre", "Rating"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setColumnCount(3);
        table.setModel(tableModel);
        table.setDefaultEditor(Object.class, null);
        table.setAutoCreateRowSorter(true);
    }

    // EFFECTS: Performs actions on the buttons
    public void buttonActions() {
        addButtonAction();
        deleteButtonAction();
        saveButtonAction();
        loadButtonAction();
    }

    // EFFECTS: Adds a movie to the table
    public void addButtonAction() {
        addButton.addActionListener(e -> {
            Movie movie = new Movie(nameField.getText(), genreField.getText(),
                    Integer.parseInt(ratingField.getText()));
            try {
                movieList.addAll(movie);
            } catch (ExistingMovieException existingMovieException) {
                JOptionPane.showMessageDialog(null,"Error!");
            }
            row[0] = nameField.getText();
            row[1] = genreField.getText();
            row[2] = Integer.parseInt(ratingField.getText());
            tableModel.addRow(row);
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(sound);
                clip.open(inputStream);
                clip.start();
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        });
    }

    // EFFECTS: Deletes a movie from the table
    public void deleteButtonAction() {
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            int selectedColumn = table.getSelectedColumn();
            if (selectedRow >= 0) {
                Object value = table.getModel().getValueAt(selectedRow, selectedColumn);
                String movie = value.toString();
                tableModel.removeRow(selectedRow);
                movieList.removeMovie(movie);
            }
        });
    }

    // EFFECTS: Saves the table to file
    public void saveButtonAction() {
        saveButton.addActionListener(e -> {
            try {
                jsonWriter.open();
                jsonWriter.write(movieList);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null, "Saved your movie list!");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        });
    }


    // EFFECTS: Loads the table from file
    public void loadButtonAction() {
        loadButton.addActionListener(e -> {
            try {
                movieList = jsonReader.read();
                for (Movie m : movieList.getAllMovies()) {
                    row[0] = m.getName();
                    row[1] = m.getGenre();
                    row[2] = m.getRating();
                    tableModel.addRow(row);
                }
                JOptionPane.showMessageDialog(null, "Loaded!");
            } catch (IOException | ExistingMovieException f) {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        });
    }

    // EFFECTS: Positions the frame in the centre of the screen
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // EFFECTS: Runs the GUI
    public static void main(String[] args) {
        new GUI();
    }
}
