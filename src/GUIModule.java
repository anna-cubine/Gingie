import javax.swing.*;
import java.awt.*;

public class GUIModule {
	private final JFrame frame;
	private JPanel mainPanel;
	
	public GUIModule() {
		frame = new JFrame("Gingie");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);
		
		initGUI();
	}
	
	private void initGUI() {
		mainPanel = new MainCategoriesScreen();
		frame.add(mainPanel);
		frame.setVisible(true);
	}
	
	public void switchScreen(JPanel newPanel) {
		frame.remove(mainPanel);
		mainPanel = newPanel;
		frame.add(mainPanel);
		frame.revalidate();
		frame.repaint();
	}
	
	public static void main(String[] args) {
		new GUIModule();
	}
	public void connectToDatabase() {
		// Connect to the database
		String url = "jdbc:mysql://75.253.5.172:36750/gingie";
		String username = "jam";
		String password = "Sql3396!";

		// Create a connection
		try{
			java.sql.Connection con = java.sql.DriverManager.getConnection(url, username, password);
			System.out.println("Connected successfully");
		}catch(Exception e) {
			System.out.println ( "exception: " + e.getMessage ( ) );
		}
	}
	
	// Main Categories Screen
	class MainCategoriesScreen extends JPanel {
		public MainCategoriesScreen() {
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBackground( Color.orange);
			
			JButton backButton = new JButton("⬅️");
			JButton forwardButton = new JButton("➡️");
			JButton profileButton = new JButton("👤");
			JTextField searchField = new JTextField("Search", 20);
			JButton homeButton = new JButton("🏠");
			
			JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
			topLeft.add(backButton);
			topLeft.add(forwardButton);
			topLeft.add(searchField);
			
			JPanel topRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			topRight.add(profileButton);
			topRight.add(homeButton);
			
			topPanel.add(topLeft, BorderLayout.WEST);
			topPanel.add(topRight, BorderLayout.EAST);
			
			JPanel categoryPanel = new JPanel(new GridLayout(2, 4, 10, 10));
			categoryPanel.setBackground(Color.ORANGE);
			String[] categories = {"Breakfast", "Lunch", "Dinner", "Dessert", "Snacks", "Drinks", "All"};
			for (String category : categories) {
				JButton button = new JButton(category);
				button.setBackground(Color.ORANGE);
				categoryPanel.add(button);
				button.addActionListener(e -> switchScreen(new RecipeListScreen()));
			}
			
			homeButton.addActionListener(e -> switchScreen(new MainCategoriesScreen ()));
			backButton.addActionListener(e -> switchScreen(new MainCategoriesScreen()));
			forwardButton.addActionListener(e -> switchScreen(new RecipeListScreen()));
			
			
			
			add(topPanel, BorderLayout.NORTH);
			add(categoryPanel, BorderLayout.CENTER);
		}
	}
	
	// Recipe List Screen
	class RecipeListScreen extends JPanel {
		public RecipeListScreen() {
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBackground(Color.ORANGE);
			
			JButton backButton = new JButton("⬅️");
			JButton forwardButton = new JButton("➡️");
			JButton profileButton = new JButton("👤");
			JTextField searchField = new JTextField("Search", 20);
			JButton homeButton = new JButton("🏠");
			
			JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
			topLeft.add(backButton);
			topLeft.add(forwardButton);
			topLeft.add(searchField);
			
			JPanel topRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			topRight.add(profileButton);
			topRight.add(homeButton);
			
			topPanel.add(topLeft, BorderLayout.WEST);
			topPanel.add(new JLabel("Categories"), BorderLayout.CENTER);
			topPanel.add(topRight, BorderLayout.EAST);
			
			JPanel recipePanel = new JPanel(new GridLayout(2, 3, 10, 10));
			recipePanel.setBackground(Color.GRAY);
			
			for (int i = 0; i < 6; i++) {
				JPanel recipeCard = new JPanel();
				recipeCard.setBackground(Color.WHITE);
				recipeCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				recipeCard.setLayout(new BoxLayout(recipeCard, BoxLayout.Y_AXIS));
				
				JLabel picture = new JLabel("[PICTURE]", SwingConstants.CENTER);
				JLabel name = new JLabel("Dish Name", SwingConstants.CENTER);
				JLabel rating = new JLabel("Rating: ⭐⭐⭐⭐", SwingConstants.CENTER);
				JLabel category = new JLabel("Category", SwingConstants.CENTER);
				
				recipeCard.add(picture);
				recipeCard.add(name);
				recipeCard.add(rating);
				recipeCard.add(category);
				recipeCard.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						switchScreen(new RecipeDetailScreen());
					}
				});
				recipePanel.add(recipeCard);
			}
			
			add(topPanel, BorderLayout.NORTH);
			add(recipePanel, BorderLayout.CENTER);
			homeButton.addActionListener(e -> switchScreen(new MainCategoriesScreen ()));
			backButton.addActionListener(e -> switchScreen(new MainCategoriesScreen()));
			forwardButton.addActionListener(e -> switchScreen(new RecipeListScreen()));
		}
	}
	
	// Recipe Detail Screen
	class RecipeDetailScreen extends JPanel {
		public RecipeDetailScreen() {
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBackground(Color.LIGHT_GRAY);
			
			JButton backButton = new JButton("⬅️");
			JButton forwardButton = new JButton("➡️");
			JButton profileButton = new JButton("👤");
			JTextField searchField = new JTextField("Search", 20);
			JButton homeButton = new JButton("🏠");
			
			JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
			topLeft.add(backButton);
			topLeft.add(forwardButton);
			topLeft.add(searchField);
			
			JPanel topRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			topRight.add(profileButton);
			topRight.add(homeButton);
			
			topPanel.add(topLeft, BorderLayout.WEST);
			topPanel.add(topRight, BorderLayout.EAST);
			
			JPanel detailsPanel = new JPanel(new BorderLayout());
			detailsPanel.setBackground(Color.GRAY);
			
			JLabel picture = new JLabel("[FOOD PICTURE]", SwingConstants.CENTER);
			JLabel name = new JLabel("Dish Name", SwingConstants.CENTER);
			JLabel rating = new JLabel("Rating: ⭐⭐⭐⭐", SwingConstants.CENTER);
			
			JPanel textPanel = new JPanel(new GridLayout(1, 3));
			JTextArea ingredientsArea = new JTextArea("Ingredients:\n- Ing 1\n- Ing 2\n- Ing 3");
			JTextArea directionsArea = new JTextArea("Directions:\n1. Step one\n2. Step two\n3. Step three");
			JTextArea reviewsArea = new JTextArea("Reviews:");
			
			textPanel.add(ingredientsArea);
			textPanel.add(directionsArea);
			textPanel.add(reviewsArea);
			
			detailsPanel.add(picture, BorderLayout.NORTH);
			detailsPanel.add(name, BorderLayout.CENTER);
			detailsPanel.add(rating, BorderLayout.SOUTH);
			add(topPanel, BorderLayout.NORTH);
			add(detailsPanel, BorderLayout.CENTER);
			add(textPanel, BorderLayout.SOUTH);
			homeButton.addActionListener(e -> switchScreen(new MainCategoriesScreen ()));
			backButton.addActionListener(e -> switchScreen(new MainCategoriesScreen()));
			forwardButton.addActionListener(e -> switchScreen(new RecipeListScreen()));
		}
	}
}