import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeatherAppGui extends JFrame {
    private JSONObject weatherData;
   // private Map<String, String> passwordMap;

    public WeatherAppGui() {
        // Setup our GUI and add a title
        super("Weather App");

        // Initialize password map
     //   passwordMap = new HashMap<>();

        // Configure GUI to end the program's process once it has been closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set the size of our GUI (in pixels)
        setSize(600, 700);

        // Load our GUI at the center of the screen
        setLocationRelativeTo(null);

        // Make our layout manager null to manually position our components within the GUI
        setLayout(null);

        // Prevent any resize of our GUI
        setResizable(true);

        // Add GUI components with color
        addGuiComponents();
    }

    private void addGuiComponents() {
        // Define colors
        Color backgroundColor = new Color(124, 126, 240); // Light gray background
        Color textColor = new Color(16, 16, 16); // Dark gray text color
        Color accentColor = new Color(70, 130, 180); // Steel blue accent color

        // Set background color for the frame
        getContentPane().setBackground(backgroundColor);

        // Search field
        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(15, 15, 300, 45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        searchTextField.setBorder(BorderFactory.createLineBorder(accentColor, 5)); // Add border with accent color
        add(searchTextField);

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Dialog", Font.BOLD, 18));
        searchButton.setBounds(330, 15, 100, 45);
        searchButton.setBackground(accentColor);
        searchButton.setForeground(Color.WHITE); // White text color
        searchButton.setFocusPainted(false); // Remove focus border
        add(searchButton);

        // Weather condition image
        JLabel weatherConditionImage = new JLabel(loadImage("weatherapp/src/assets/cloudy.png"));
        weatherConditionImage.setBounds(25, 80, 400, 200);
        add(weatherConditionImage);

        // Temperature text
        JLabel temperatureText = new JLabel("10°C");
        temperatureText.setBounds(25, 300, 400, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        temperatureText.setForeground(textColor); // Dark gray text color
        add(temperatureText);

        // Weather condition description
        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(25, 365, 400, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        weatherConditionDesc.setForeground(textColor); // Dark gray text color
        add(weatherConditionDesc);

        // Panel for humidity and windspeed
        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(25, 420, 400, 100);
        infoPanel.setBackground(Color.WHITE); // White background for info panel
        infoPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2x2 grid layout with gaps
        infoPanel.setBorder(BorderFactory.createLineBorder(accentColor, 2)); // Add border with accent color

        // Humidity image and text
        JLabel humidityImage = new JLabel(loadImage("weatherapp/src/assets/humidity.png"));
        humidityImage.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(humidityImage);

        JLabel humidityText = new JLabel("Humidity: 100%");
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 20));
        humidityText.setForeground(textColor); // Dark gray text color
        infoPanel.add(humidityText);

        // Windspeed image and text
        JLabel windspeedImage = new JLabel(loadImage("weatherapp/src/assets/windspeed.png"));
        windspeedImage.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(windspeedImage);

        JLabel windspeedText = new JLabel("Wind Speed: 15 km/h");
        windspeedText.setFont(new Font("Dialog", Font.PLAIN, 20));
        windspeedText.setForeground(textColor); // Dark gray text color
        infoPanel.add(windspeedText);

        // Add info panel to frame
        add(infoPanel);

        // Forecast panel
        JPanel forecastPanel = new JPanel();
        forecastPanel.setBounds(25, 540, 550, 100);
        forecastPanel.setBackground(Color.WHITE);
        forecastPanel.setLayout(new GridLayout(1, 4, 10, 10)); // 1 row, 4 columns grid layout with gaps
        forecastPanel.setBorder(BorderFactory.createLineBorder(accentColor, 2));

        // Add forecast labels
       /* for (int i = 0; i < 4; i++) {
            JPanel innerPanel = new JPanel();
            innerPanel.setBackground(Color.WHITE);
            innerPanel.setLayout(new BorderLayout());

            JLabel dayLabel = new JLabel("Day " + (i + 1));
            dayLabel.setFont(new Font("Dialog", Font.BOLD, 16));
            dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
            innerPanel.add(dayLabel, BorderLayout.NORTH);

            JLabel forecastImage = new JLabel(loadImage("weatherapp/src/assets/cloudy.png"));
            forecastImage.setHorizontalAlignment(SwingConstants.CENTER);
            innerPanel.add(forecastImage, BorderLayout.CENTER);

            JLabel temperatureLabel = new JLabel("10°C");
            temperatureLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
            temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
            innerPanel.add(temperatureLabel, BorderLayout.SOUTH);

            forecastPanel.add(innerPanel);
        }

        add(forecastPanel);
*/
        // Credits label
        JLabel creditsLabel = new JLabel("Icons made by Freepik from www.flaticon.com");
        creditsLabel.setBounds(25, 600, 550, 30);
        creditsLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        creditsLabel.setForeground(textColor); // Dark gray text color
        add(creditsLabel);

        // Refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Dialog", Font.BOLD, 18));
        refreshButton.setBounds(450, 15, 125, 45);
        refreshButton.setBackground(accentColor);
        refreshButton.setForeground(Color.WHITE); // White text color
        refreshButton.setFocusPainted(false); // Remove focus border
        add(refreshButton);

        // Password management panel
        JPanel passwordPanel = new JPanel();
        passwordPanel.setBounds(450, 80, 125, 100);
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setBorder(BorderFactory.createLineBorder(accentColor, 2));

        // Search button action listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get location from user input
                String userInput = searchTextField.getText();

                // Validate input - remove whitespace to ensure non-empty text
                if (userInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a location.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Retrieve weather data
                weatherData = WeatherApp.getWeatherData(userInput);

                // Update GUI components
                if (weatherData != null) {
                    // Update weather condition image
                    String weatherCondition = (String) weatherData.get("weather_condition");
                    updateWeatherConditionImage(weatherConditionImage, weatherCondition);

                    // Update temperature text
                    double temperature = (double) weatherData.get("temperature");
                    temperatureText.setText(temperature + "°C");

                    // Update weather condition text
                    weatherConditionDesc.setText(weatherCondition);

                    // Update humidity text
                    long humidity = (long) weatherData.get("humidity");
                    humidityText.setText("Humidity: " + humidity + "%");

                    // Update windspeed text
                    double windspeed = (double) weatherData.get("windspeed");
                    windspeedText.setText("Wind Speed: " + windspeed + " km/h");
                } else {
                    // Handle case where weather data couldn't be retrieved
                    JOptionPane.showMessageDialog(null, "Error: Weather data not available for this location.",
                            "Weather Data Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Refresh button action listener
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset weather data
                weatherData = null;
                weatherConditionImage.setIcon(loadImage("weatherapp/src/assets/cloudy.png"));
                temperatureText.setText("10°C");
                weatherConditionDesc.setText("Cloudy");
                humidityText.setText("Humidity: 100%");
                windspeedText.setText("Wind Speed: 15 km/h");
                // Reset forecast labels
                Component[] innerPanels = forecastPanel.getComponents();
                for (Component component : innerPanels) {
                    if (component instanceof JPanel) {
                        JPanel innerPanel = (JPanel) component;
                        JLabel forecastImage = (JLabel) innerPanel.getComponent(1);
                        forecastImage.setIcon(loadImage("weatherapp/src/assets/cloudy.png"));
                        JLabel temperatureLabel = (JLabel) innerPanel.getComponent(2);
                        temperatureLabel.setText("10°C");
                    }
                }
            }
        });
    }

    private void updateWeatherConditionImage(JLabel weatherConditionImage, String weatherCondition) {
        ImageIcon icon = null;
        switch (weatherCondition) {
            case "Clear":
                icon = loadImage("weatherapp/src/assets/clear.png");
                break;
            case "Cloudy":
                icon = loadImage("weatherapp/src/assets/cloudy.png");
                break;
            case "Rain":
                icon = loadImage("weatherapp/src/assets/rain.png");
                break;
            case "Snow":
                icon = loadImage("weatherapp/src/assets/snow.png");
                break;
            default:
                // Handle unknown weather conditions
                break;
        }
        if (icon != null) {
            weatherConditionImage.setIcon(icon);
        }
    }

    // Used to create images in our GUI components
    private ImageIcon loadImage(String resourcePath) {
        try {
            // Read the image file from the path given
            BufferedImage image = ImageIO.read(new File(resourcePath));

            // Return an ImageIcon so that our component can render it
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not find resource " + resourcePath,
                    "Resource Loading Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}