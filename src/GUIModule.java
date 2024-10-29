import javax.swing.*;
import java.awt.*;

public class GUIModule {
	private JFrame frame;
	private JPanel mainPanel;
	
	public GUIModule() {
		frame = new JFrame("Recipe Manager");
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
	
	// Main Categories Screen
	class MainCategoriesScreen extends JPanel {
		public MainCategoriesScreen() {
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBackground(Color.LIGHT_GRAY);
			
			JButton backButton = new JButton("⬅️");
			JButton profileButton = new JButton("👤");
			JTextField searchField = new JTextField("Search", 20);
			
			JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
			topLeft.add(backButton);
			topLeft.add(searchField);
			
			JPanel topRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			topRight.add(profileButton);
			
			topPanel.add(topLeft, BorderLayout.WEST);
			topPanel.add(topRight, BorderLayout.EAST);
			
			JPanel categoryPanel = new JPanel(new GridLayout(2, 4, 10, 10));
			categoryPanel.setBackground(Color.GRAY);
			String[] categories = {"Breakfast", "Lunch", "Dinner", "Dessert", "Snacks", "Drinks", "All"};
			for (String category : categories) {
				JButton button = new JButton(category);
				button.setBackground(Color.ORANGE);
				categoryPanel.add(button);
				button.addActionListener(e -> switchScreen(new RecipeListScreen()));
			}
			
			add(topPanel, BorderLayout.NORTH);
			add(categoryPanel, BorderLayout.CENTER);
		}
	}
	
	// Recipe List Screen
	class RecipeListScreen extends JPanel {
		public RecipeListScreen() {
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBackground(Color.LIGHT_GRAY);
			
			JButton backButton = new JButton("⬅️");
			JTextField searchField = new JTextField("Search", 20);
			
			JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
			topLeft.add(backButton);
			topLeft.add(searchField);
			
			topPanel.add(topLeft, BorderLayout.WEST);
			topPanel.add(new JLabel("Categories"), BorderLayout.CENTER);
			
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
		}
	}
	
	// Recipe Detail Screen
	class RecipeDetailScreen extends JPanel {
		public RecipeDetailScreen() {
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBackground(Color.LIGHT_GRAY);
			
			JButton backButton = new JButton("⬅️");
			JTextField searchField = new JTextField("Search", 20);
			
			JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
			topLeft.add(backButton);
			topLeft.add(searchField);
			
			topPanel.add(topLeft, BorderLayout.WEST);
			
			JPanel detailsPanel = new JPanel(new BorderLayout());
			detailsPanel.setBackground(Color.GRAY);
			
			JLabel picture = new JLabel("[FOOD PICTURE]", SwingConstants.CENTER);
			JLabel name = new JLabel("Dish Name", SwingConstants.CENTER);
			JLabel rating = new JLabel("Rating: ⭐⭐⭐⭐", SwingConstants.CENTER);
			
			JPanel textPanel = new JPanel(new GridLayout(1, 3));
			JTextArea ingredientsArea = new JTextArea("Ingredients:\n- Ing 1\n- Ing 2\n- Ing 3");
			JTextArea directionsArea = new JTextArea("Directions:\n1. Step one\n2. Step two\n3. Step three");
			JTextArea reviewsArea = new JTextArea("Reviews:\nGreat recipe!");
			
			textPanel.add(ingredientsArea);
			textPanel.add(directionsArea);
			textPanel.add(reviewsArea);
			
			detailsPanel.add(picture, BorderLayout.NORTH);
			detailsPanel.add(name, BorderLayout.CENTER);
			detailsPanel.add(rating, BorderLayout.SOUTH);
			add(topPanel, BorderLayout.NORTH);
			add(detailsPanel, BorderLayout.CENTER);
			add(textPanel, BorderLayout.SOUTH);
		}
	}
}