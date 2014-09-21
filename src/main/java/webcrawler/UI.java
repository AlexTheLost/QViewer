package webcrawler;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
	static private int BOR = 10;
	private WebCrawler wCrawler = new WebCrawler();

	public void createGUI() {
		final JFrame frame = new JFrame("QViewer");
		frame.setMinimumSize(new Dimension(800, 800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(BOR, BOR, BOR, BOR));
		panel.setLayout(new BorderLayout());

		final JTextField queryTextField = new JTextField();
		panel.add(queryTextField, BorderLayout.PAGE_START);

		final JButton searchButton = new JButton("Search");
		panel.add(searchButton, BorderLayout.LINE_END);

		final JTextArea resultArea = new JTextArea();
		panel.add(resultArea, BorderLayout.CENTER);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread() {
					public void run() {
						searchButton.setEnabled(false);
						String result;
						try {
							String query = queryTextField.getText();
							if (query.isEmpty()) {
								resultArea.setText("Empty query!");
								return;
							}
							result = wCrawler.getURLs(query);
							resultArea.setText(result);
						} catch (IOException exc) {
							resultArea
									.setText("Error! Impossible get the result.");
							exc.printStackTrace();
						} finally {
							searchButton.setEnabled(true);
						}
					}
				};
				t.start();
			}
		});

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		frame.setPreferredSize(new Dimension(260, 225));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				UI ui = new UI();
				ui.createGUI();
			}
		});
	}
}
