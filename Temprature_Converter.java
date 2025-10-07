import javax.swing.*;

public class TemperatureConverter extends JFrame {

    private JComboBox<String> fromUnit, toUnit;
    private JTextField inputField, outputField;
    private StringBuilder currentInput = new StringBuilder();

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 420);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] units = {"Celsius", "Fahrenheit"};

        JLabel fromLabel = new JLabel("From");
        fromLabel.setBounds(30, 20, 50, 30);
        add(fromLabel);

        fromUnit = new JComboBox<>(units);
        fromUnit.setBounds(80, 20, 100, 30);
        add(fromUnit);

        JLabel toLabel = new JLabel("To");
        toLabel.setBounds(30, 60, 50, 30);
        add(toLabel);

        toUnit = new JComboBox<>(units);
        toUnit.setBounds(80, 60, 100, 30);
        add(toUnit);

        JLabel inputLabel = new JLabel("Enter the Value:");
        inputLabel.setBounds(200, 20, 100, 30);
        add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(300, 20, 70, 30);
        inputField.setEditable(false);
        add(inputField);

        JLabel outputLabel = new JLabel("Converted Value:");
        outputLabel.setBounds(200, 60, 110, 30);
        add(outputLabel);

        outputField = new JTextField();
        outputField.setBounds(300, 60, 70, 30);
        outputField.setEditable(false);
        add(outputField);

        JButton convertBtn = new JButton("Convert");
        convertBtn.setBounds(150, 100, 100, 30);
        add(convertBtn);

        JPanel keypad = new JPanel();
        keypad.setLayout(new java.awt.GridLayout(4, 4, 5, 5));
        keypad.setBounds(60, 150, 260, 200);
        add(keypad);

        String[] keys = {
            "1", "2", "3", "AC",
            "4", "5", "6", "<-",
            "7", "8", "9", "±",
            "0", ".",
        };

        for (String key : keys) {
            JButton btn = new JButton(key);
            if (!key.isEmpty()) {
                btn.addActionListener(e -> handleKeyPress(key));
            } else {
                btn.setEnabled(false);
            }
            keypad.add(btn);
        }

        convertBtn.addActionListener(e -> convertTemperature());
    }

    private void handleKeyPress(String key) {
        switch (key) {
            case "AC":
                currentInput.setLength(0);
                break;
            case "<-":
                if (currentInput.length() > 0)
                    currentInput.deleteCharAt(currentInput.length() - 1);
                break;
            case "±":
                if (currentInput.length() > 0 && currentInput.charAt(0) == '-') {
                    currentInput.deleteCharAt(0);
                } else {
                    currentInput.insert(0, "-");
                }
                break;
            default:
                currentInput.append(key);
                break;
        }
        inputField.setText(currentInput.toString());
    }

    private void convertTemperature() {
        try {
            double value = Double.parseDouble(currentInput.toString());
            String from = (String) fromUnit.getSelectedItem();
            String to = (String) toUnit.getSelectedItem();

            double result = value;
            if (from.equals("Celsius") && to.equals("Fahrenheit")) {
                result = value * 9 / 5 + 32;
            } else if (from.equals("Fahrenheit") && to.equals("Celsius")) {
                result = (value - 32) * 5 / 9;
            }

            outputField.setText(String.format("%.2f", result));
        } catch (Exception e) {
            outputField.setText("Error");
        }
    }

    public static void main(String[] args) {
        new TemperatureConverter().setVisible(true);
    }
}