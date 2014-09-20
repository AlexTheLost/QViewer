package webcrawler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI extends JFrame {

	static private int BOR = 10;
	private WebCrawler wCrawler = new WebCrawler();

	public void createGUI() {

		final JFrame frame = new JFrame("QViewer");
		frame.setMinimumSize(new Dimension(800, 800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(BOR, BOR, BOR, BOR));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(Box.createVerticalGlue());

		final JTextField queryTextField = new JTextField();
		panel.add(queryTextField);

		JButton searchButton = new JButton("Search");
		panel.add(searchButton);

		panel.add(Box.createVerticalGlue());

		final JTextArea resultArea = new JTextArea();
		panel.add(resultArea);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result;
				try {
					result = wCrawler.getURLs(queryTextField.getText());
					resultArea.setText(result);
				} catch (IOException exc) {
					resultArea.setText("Error. Impossible get the result.");
					exc.printStackTrace();
				}
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
