import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GUIModule {
	private final JFrame frame;
	private JPanel mainPanel;
	
	// Global Button Declarations
	private final JButton backButton = new JButton("⬅️");
	private final JButton forwardButton = new JButton("➡️");
	private final JButton profileButton = new JButton("👤");
	private final JTextField searchField = new JTextField("Search", 20);
	private final JButton homeButton = new JButton("🏠");
	
	// Color scheme matching the provided theme
	private final Color topPanelColor = new Color(255, 140, 0); // Orange
	private final Color backgroundColor = new Color(211, 211, 211); // Light gray
	private final Color buttonColor = new Color(255, 165, 0); // Orange for buttons
	
	// Sample categories and recipes for search
	private final List<String> categories = List.of("Breakfast", "Lunch", "Dinner", "Dessert", "Snacks", "Drinks", "All");
	private final List<Recipe> recipes = List.of(
			new Recipe("Pancakes", "Breakfast"),
			new Recipe("Salad", "Lunch"),
			new Recipe("Steak", "Dinner"),
			new Recipe("Brownie", "Dessert"),
			new Recipe("Smoothie", "Drinks"),
			new Recipe("Cookies", "Snacks")
	);
	
	// Current logged-in user
	private User currentUser = null;
	
	public GUIModule() {
		frame = new JFrame("Home Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);
		
		initGUI();
		addGlobalButtonListeners();
		addSearchListener();
	}
	
	private void initGUI() {
		mainPanel = new MainCategoriesScreen(categories);
		frame.add(mainPanel);
		frame.setVisible(true);
	}
	
	private void addGlobalButtonListeners() {
		homeButton.addActionListener(e -> switchScreen(new MainCategoriesScreen(categories)));
		backButton.addActionListener(e -> switchScreen(new MainCategoriesScreen(categories)));
		forwardButton.addActionListener(e -> switchScreen(new RecipeListScreen(recipes)));
		
		profileButton.addActionListener(e -> {
			if (currentUser == null) {
				showLoginOrRegisterDialog();
			} else {
				switchScreen(new UserProfileScreen(currentUser));
			}
		});
	}
	
	private void addSearchListener() {
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchText = searchField.getText().trim().toLowerCase();
				if (mainPanel instanceof MainCategoriesScreen) {
					((MainCategoriesScreen) mainPanel).filterCategories(searchText);
				} else if (mainPanel instanceof RecipeListScreen) {
					((RecipeListScreen) mainPanel).filterRecipes(searchText);
				}
			}
		});
	}
	
	// Method to show Login or Register dialog if no user is logged in
	private void showLoginOrRegisterDialog() {
		int choice = JOptionPane.showOptionDialog(frame, "Do you want to log in or register?",
				"Profile", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new String[]{"Log In", "Register"}, "Log In");
		
		if (choice == JOptionPane.YES_OPTION) {
			LoginDialog loginDialog = new LoginDialog(frame);
			loginDialog.setVisible(true);
			
			// If login successful, set the logged-in user
			currentUser = new User(1, "exampleUser", "password", "user@example.com");
			
		} else if (choice == JOptionPane.NO_OPTION) {
			RegisterDialog registerDialog = new RegisterDialog(frame);
			registerDialog.setVisible(true);
			
			// Simulate registration (create a new user)
			currentUser = new User(2, "newUser", "password", "newuser@example.com");
		}
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
	
	// Helper Method to Create Top Panel
	private JPanel createTopPanel() {
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(topPanelColor);
		
		JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topLeft.setBackground(topPanelColor);
		topLeft.add(backButton);
		topLeft.add(forwardButton);
		topLeft.add(searchField);
		
		JPanel topRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		topRight.setBackground(topPanelColor);
		topRight.add(profileButton);
		topRight.add(homeButton);
		
		topPanel.add(topLeft, BorderLayout.WEST);
		topPanel.add(topRight, BorderLayout.EAST);
		return topPanel;
	}
	
	// Main Categories Screen
	class MainCategoriesScreen extends JPanel {
		private final List<JButton> categoryButtons = new ArrayList<>();
		
		public MainCategoriesScreen(List<String> categories) {
			setLayout(new BorderLayout());
			setBackground(backgroundColor);
			
			JPanel topPanel = createTopPanel();
			add(topPanel, BorderLayout.NORTH);
			
			JPanel categoryPanel = new JPanel(new GridLayout(2, 4, 10, 10));
			categoryPanel.setBackground(backgroundColor);
			
			for (String category : categories) {
				JButton button = new JButton(category);
				button.setBackground(buttonColor); // Set the button color to orange
				button.setForeground(Color.WHITE); // Set text color to white for contrast
				button.setOpaque(true); // Ensure the color is applied to the button
				button.setBorderPainted(false); // Remove border for a cleaner look
				button.addActionListener(e -> switchScreen(new RecipeListScreen(recipes)));
				categoryButtons.add(button);
				categoryPanel.add(button);
			}
			
			add(categoryPanel, BorderLayout.CENTER);
		}
		
		public void filterCategories(String searchText) {
			for (JButton button : categoryButtons) {
				boolean isVisible = button.getText().toLowerCase().contains(searchText);
				button.setVisible(isVisible);
			}
		}
	}
	
	// Recipe List Screen
	class RecipeListScreen extends JPanel {
		private final List<JPanel> recipeCards = new ArrayList<>();
		
		public RecipeListScreen(List<Recipe> recipes) {
			setLayout(new BorderLayout());
			setBackground(backgroundColor);
			
			JPanel topPanel = createTopPanel();
			add(topPanel, BorderLayout.NORTH);
			
			JPanel recipePanel = new JPanel(new GridLayout(2, 3, 10, 10));
			recipePanel.setBackground(backgroundColor);
			
			for (Recipe recipe : recipes) {
				JPanel recipeCard = createRecipeCard(recipe);
				recipeCards.add(recipeCard);
				recipePanel.add(recipeCard);
			}
			
			add(recipePanel, BorderLayout.CENTER);
		}
		
		private JPanel createRecipeCard(Recipe recipe) {
			JPanel recipeCard = new JPanel();
			recipeCard.setBackground(Color.WHITE);
			recipeCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			recipeCard.setLayout(new BoxLayout(recipeCard, BoxLayout.Y_AXIS));
			
			JLabel picture = new JLabel("[PICTURE]", SwingConstants.CENTER);
			JLabel name = new JLabel(recipe.getName(), SwingConstants.CENTER);
			JLabel rating = new JLabel("Rating: ⭐⭐⭐⭐", SwingConstants.CENTER);
			JLabel category = new JLabel(recipe.getCategory(), SwingConstants.CENTER);
			
			recipeCard.add(picture);
			recipeCard.add(name);
			recipeCard.add(rating);
			recipeCard.add(category);
			recipeCard.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					switchScreen(new RecipeDetailScreen(recipe));
				}
			});
			return recipeCard;
		}
		
		public void filterRecipes(String searchText) {
			for (JPanel card : recipeCards) {
				JLabel nameLabel = (JLabel) card.getComponent(1); // Recipe name label
				boolean isVisible = nameLabel.getText().toLowerCase().contains(searchText);
				card.setVisible(isVisible);
			}
		}
	}
	
	// Recipe Detail Screen
	class RecipeDetailScreen extends JPanel {
		public RecipeDetailScreen(Recipe recipe) {
			setLayout(new BorderLayout());
			setBackground(backgroundColor);
			
			JPanel topPanel = createTopPanel();
			add(topPanel, BorderLayout.NORTH);
			
			JPanel detailsPanel = new JPanel(new BorderLayout());
			detailsPanel.setBackground(Color.GRAY);
			
			JLabel picture = new JLabel("[FOOD PICTURE]", SwingConstants.CENTER);
			JLabel name = new JLabel(recipe.getName(), SwingConstants.CENTER);
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
			add(detailsPanel, BorderLayout.CENTER);
			add(textPanel, BorderLayout.SOUTH);
		}
	}
	
	// User Profile Screen
	class UserProfileScreen extends JPanel {
		public UserProfileScreen(User user) {
			setLayout(new BorderLayout());
			setBackground(backgroundColor);
			
			JPanel topPanel = createTopPanel();
			add(topPanel, BorderLayout.NORTH);
			
			JPanel profileDetailsPanel = new JPanel();
			profileDetailsPanel.setLayout(new BoxLayout(profileDetailsPanel, BoxLayout.Y_AXIS));
			profileDetailsPanel.setBackground(backgroundColor);
			profileDetailsPanel.add(new JLabel("Username: " + user.getUsername()));
			profileDetailsPanel.add(new JLabel("Email: " + user.getEmail()));
			
			add(profileDetailsPanel, BorderLayout.CENTER);
		}
	}
	
	// Recipe Class
	class Recipe {
		private final String name;
		private final String category;
		
		public Recipe(String name, String category) {
			this.name = name;
			this.category = category;
		}
		
		public String getName() {
			return name;
		}
		
		public String getCategory() {
			return category;
		}
	}
}