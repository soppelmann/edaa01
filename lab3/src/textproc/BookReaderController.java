package textproc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// needs application javafx
public class BookReaderController {

    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 300, 800));
    }

    private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

        //(listvy, knappar etc.)
        //Lägg till listan
        var model = new SortedListModel<>(counter.getWordList());
        var list = new JList<>(model);

        //Lägg till scrollbar
        JScrollPane scrollPane = new JScrollPane(list);
        pane.add(scrollPane, BorderLayout.CENTER); //places scrollable list on top of pane

        //Lägg till knappar och sökfält

        JPanel controlsPanel = new JPanel(); //collects controls

        JButton alphBtn = new JButton("Alphabetic");
        JButton freqBtn = new JButton("Frequency");

        JTextField searchTxt = new JTextField();
        searchTxt.setPreferredSize( new Dimension( 200, 24 ) );

        JButton searchBtn = new JButton("Find");

        controlsPanel.add(alphBtn);
        controlsPanel.add(freqBtn);
        controlsPanel.add(searchTxt);
        controlsPanel.add(searchBtn);

        pane.add(controlsPanel, BorderLayout.SOUTH); //adds controls to bottom of pane

        // addaction till knappar m.h.a anonyma klasser, returns ointressanta då vi bara vill sortera osv.

        alphBtn.addActionListener((e) -> model.sort((m1, m2) -> m1.getKey().compareTo(m2.getKey()))); //alfanumerisk
        freqBtn.addActionListener((e) -> model.sort((m1, m2) -> m2.getValue() - m1.getValue())); //frekvens
        searchTxt.addActionListener(e -> searchBtn.doClick()); //valfri

        searchBtn.addActionListener((e) -> {
            String txt = searchTxt.getText().trim().toLowerCase(); //valfri

            try {
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.getElementAt(i).getKey().equals(txt)) {
                        list.ensureIndexIsVisible(i);
                        list.setSelectedIndex(i);
                        return;
                    }
                }
                throw new Exception("Inga resultat hittades"); //valfri

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Inga resultat hittades");
            }

        });

        frame.pack();
        frame.setVisible(true);
    }
}