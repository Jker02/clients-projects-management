import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Vector;
import java.util.Date;

public class Gui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Client Project Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Creazione della tabella per visualizzare i clienti
        String[] column = {"Name", "Surname", "Email", "Telephone Number", "Address"};
        DefaultTableModel tableModel = new DefaultTableModel(column, 0) {
        	 public boolean isCellEditable(int row, int column) {
                 return false;  // Disabilita la modifica delle celle
             }
        };
        JTable table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);  // Aggiungi la tabella in un JScrollPane
        scrollPane.setPreferredSize(new Dimension(200, 100));
        frame.add(scrollPane, BorderLayout.CENTER);

        // Pannello per i campi di input e il bottone
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension (100, 20));
        
        JLabel surnameLabel = new JLabel("Surname:");
        JTextField surnameField = new JTextField(15);
        surnameField.setPreferredSize(new Dimension (100, 20));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
        emailField.setPreferredSize(new Dimension (100, 20));
        
        JLabel telephoneNumberLabel = new JLabel("Telephone Number:");
        JTextField telephoneNumberField = new JTextField(15);
        telephoneNumberField.setPreferredSize(new Dimension (100, 20));
        
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(10);
        addressField.setPreferredSize(new Dimension (100, 20));

        JButton addButton = new JButton("Add Client");
        JButton deleteButton = new JButton("Delete Client");

        // Aggiungi i componenti al pannello
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(surnameLabel);
        inputPanel.add(surnameField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(telephoneNumberLabel);
        inputPanel.add(telephoneNumberField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        
        // Aggiungi il pannello di input al frame
        JScrollPane scrollInputPane = new JScrollPane(inputPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scrollInputPane, BorderLayout.SOUTH);

        Vector<Client> clientList = new Vector<Client>();

        // Funzione per aggiungere il cliente alla tabella
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String name = nameField.getText();
                String surname = surnameField.getText();
                String email = emailField.getText();
                String telephoneNumber = telephoneNumberField.getText();
                String address = addressField.getText();
                
                // Serie di controlli sui campi
                // Verifica che i campi non siano vuoti
                if (!name.isEmpty() && !email.isEmpty() && !surname.isEmpty() && !telephoneNumber.isEmpty() && !address.isEmpty()) {
                	// controllo formato email
                	if(email.indexOf("@") != -1){
                		// Controlla che l'email non sia già in uso
            			int i = 0;
            			while (i < clientList.size() && clientList.get(i).getEmail().compareTo(email) != 0) {
            				i++;
            			}
            			if (i == clientList.size()) {
            				// controlla formato numero di telefono
            				if (telephoneNumber.length() == 10) {
            					i = 0;
            					while (i < 10 && telephoneNumber.charAt(i) >= 48 && telephoneNumber.charAt(i) <= 57) {
            						i++;
            					}
            					if (i == 10) {
            						// Controlla che il numero di telefono non sia già in uso
            						i = 0;
            						while (i < clientList.size() && clientList.get(i).getTelephoneNumber().compareTo(telephoneNumber) != 0) {
            							i++;
            						}
            						if (i == clientList.size()) {
            							// Aggiungi il cliente alla tabella
            							tableModel.addRow(new Object[]{name, surname, email, telephoneNumber, address});
            							
            							// crea oggetto cliente e aggiunta alla lista dei clienti
            							Client client = new Client(name, surname, email, telephoneNumber, address);
            							clientList.add(client);
            							
            							// Pulisci i campi di input
            							nameField.setText("");
            							surnameField.setText("");
            							emailField.setText("");
            							telephoneNumberField.setText("");
            							addressField.setText("");
            						} else { // numero di telefono in uso
            							JOptionPane.showMessageDialog(frame, "Telephone Number is already used", "Erorr", JOptionPane.ERROR_MESSAGE);
            						}
            					} else { // formato numero di telefono non corretto
            						JOptionPane.showMessageDialog(frame, "Telephone number isn't in the correct format", "Error", JOptionPane.ERROR_MESSAGE);
            					}
            				} else { // formato numero di telefono non corretto
            					JOptionPane.showMessageDialog(frame, "Telephone number isn't in the correct format", "Error", JOptionPane.ERROR_MESSAGE);
            				}
            			} else { // email in uso
            				JOptionPane.showMessageDialog(frame, "Email is already used", "Error", JOptionPane.ERROR_MESSAGE);
            			}
                	} else { // formato email non corretto
                		JOptionPane.showMessageDialog(frame, "email isn't in the correct format", "Error", JOptionPane.ERROR_MESSAGE);
                	}
                } else { // campi vuoti
                	JOptionPane.showMessageDialog(frame, "All field must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Elimina cliente selezionando la riga
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ottieni la riga selezionata
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    // Rimuovi la riga selezionata
                    tableModel.removeRow(selectedRow);
                    
                    clientList.remove(selectedRow);
                } else {
                    // Mostra un messaggio se non è stata selezionata alcuna riga
                    JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) {
                	int clientSelectedRow = table.getSelectedRow();
                	Client c = clientList.get(table.getSelectedRow());
                    
                    JFrame newWindow = new JFrame(clientList.get(table.getSelectedRow()).getName() + " Projects");
                    newWindow.setSize(800, 600);
                    newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    
                    // nuova tabella nella nuova finestra di progetti del cliente
                    String[] newWindowColumn = {"Name", "Type", "Url", "Date", "City", "Status", "Price"}; // Colonne nuova finestra
                    
                    DefaultTableModel newWindowTableModel = new DefaultTableModel(newWindowColumn, 0) {
                   	 public boolean isCellEditable(int row, int column) {
                            return false;  // Disabilita la modifica delle celle
                        }
                    };
                    JTable newWindowTable = new JTable(newWindowTableModel);
                    for (Project clientProject : c.getClientProjects()) { // Aggiungi progetti cliente alla tabella
                    	newWindowTableModel.addRow(new Object[] {clientProject.getName(), clientProject.getType(), clientProject.getUrl(), clientProject.getDate(), clientProject.getCity(), clientProject.getStatus(), clientProject.getPrice()});
                    }
                    
                    newWindowTable.getTableHeader().setReorderingAllowed(false);
                    JScrollPane newWindowScrollPane = new JScrollPane(newWindowTable);  // Aggiungi la tabella in un JScrollPane nella nuova finestra
                    newWindowScrollPane.setPreferredSize(new Dimension(200, 100));
                    newWindow.add(newWindowScrollPane, BorderLayout.CENTER);
                    
               
                    // Input panel unova finestra
                    JPanel newWindowInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    
                    
                    JLabel newWindowNameLabel = new JLabel("Name:");
                    JTextField newWindowNameField = new JTextField(15);
                    newWindowNameField.setPreferredSize(new Dimension(100, 20));
                    
                    JLabel typeLabel = new JLabel("Type:");
                    JTextField typeField = new JTextField(15);
                    typeField.setPreferredSize(new Dimension(100, 20));

                    JLabel emailLabel = new JLabel("Email:");
                    JTextField emailField = new JTextField(15);
                    emailField.setPreferredSize(new Dimension(100, 20));
                    
                    JLabel urlLabel = new JLabel("Url:");
                    JTextField urlField = new JTextField(15);
                    urlField.setPreferredSize(new Dimension(100, 20));
                    
                    JLabel dayLabel = new JLabel("Day:");
                    SpinnerNumberModel daySpinnerModel = new SpinnerNumberModel(1, 1, 31, 1); 
                    JSpinner daySpinner = new JSpinner(daySpinnerModel);
                    daySpinner.setPreferredSize(new Dimension(100, 20));
                    
                    JLabel monthLabel = new JLabel("Month:");
                    SpinnerNumberModel monthSpinnerModel = new SpinnerNumberModel(1, 1, 12, 1); 
                    JSpinner monthSpinner = new JSpinner(monthSpinnerModel);
                    daySpinner.setPreferredSize(new Dimension(100, 20));
                    
                    JLabel yearLabel = new JLabel("Year:");
                    SpinnerNumberModel yearSpinnerModel = new SpinnerNumberModel(2025, 1950, 2026, 1); 
                    JSpinner yearSpinner = new JSpinner(yearSpinnerModel);
                    daySpinner.setPreferredSize(new Dimension(100, 20));
                    
                    JLabel cityLabel = new JLabel("City:");
                    JTextField cityField = new JTextField(15);
                    cityField.setPreferredSize(new Dimension(100, 20));
                    
                    JLabel statusLabel = new JLabel("Status:");
                    Choice statusChoice = new Choice();
                    statusChoice.add("Finished");
                    statusChoice.add("Running");
                    statusChoice.setPreferredSize(new Dimension(100, 20));
                    
                    JLabel priceLabel = new JLabel("Price:");
                    SpinnerNumberModel spinnerPriceModel = new SpinnerNumberModel(0.1, // valore iniziale
                    		0.1, // valore minimo
                    		Double.MAX_VALUE,// valore massimo
                            0.5); // incremento

                    JSpinner priceSpinner = new JSpinner(spinnerPriceModel);
                    priceSpinner.setPreferredSize(new Dimension(100, 20));

                    JButton newWindowAddButton = new JButton("Add Project");
                    JButton newWindowDeleteButton = new JButton("Delete Project");
                    
                    // Aggiungi i componenti al pannello
                    newWindowInputPanel.add(newWindowNameLabel);
                    newWindowInputPanel.add(newWindowNameField);
                    newWindowInputPanel.add(typeLabel);
                    newWindowInputPanel.add(typeField);
                    newWindowInputPanel.add(urlLabel);
                    newWindowInputPanel.add(urlField);
                    newWindowInputPanel.add(dayLabel);
                    newWindowInputPanel.add(daySpinner);
                    newWindowInputPanel.add(monthLabel);
                    newWindowInputPanel.add(monthSpinner);
                    newWindowInputPanel.add(yearLabel);
                    newWindowInputPanel.add(yearSpinner);
                    newWindowInputPanel.add(cityLabel);
                    newWindowInputPanel.add(cityField);
                    newWindowInputPanel.add(statusLabel);
                    newWindowInputPanel.add(statusChoice);
                    newWindowInputPanel.add(priceLabel);
                    newWindowInputPanel.add(priceSpinner);
                    
                    newWindowInputPanel.add(newWindowAddButton);
                    newWindowInputPanel.add(newWindowDeleteButton);
                    
                    newWindowAddButton.addActionListener(new ActionListener(){
                    	public void actionPerformed(ActionEvent e) {
                    		String projectName = newWindowNameField.getText();
                            String type = typeField.getText().toLowerCase();
                            String url = urlField.getText().toLowerCase();
                            int day = (int)daySpinner.getValue();
                            int month = (int)monthSpinner.getValue();
                            int year = (int)yearSpinner.getValue();
                            int[] dayMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                            if ((year%4==0 && year%100!=0) || year%400==0) {
                            	dayMonths[1] = 29;
                            }
                            String city = cityField.getText().toLowerCase();
                            String status = statusChoice.getSelectedItem();
                            double price = (double)priceSpinner.getValue();
                            
                            // Controllo campi vuoti
                            if (!projectName.isEmpty() && !type.isEmpty() && !url.isEmpty() && !city.isEmpty()) {
                            	// Controlla url
                            	if (url.startsWith("http://") || url.startsWith("https://")) {
                        			if (day <= dayMonths[month-1]) {
                        				String date = day + "-" + month + "-" + year;
                        				Project progetto = new Project(projectName, type, url, date, city, status, price);
                        				
                        				clientList.get(clientSelectedRow).getClientProjects().add(progetto);
                        				
                        				newWindowTableModel.addRow(new Object[] {projectName, type, url, date, city, status, price});
                        				
                        				newWindowNameField.setText("");
                        				typeField.setText("");
                        				urlField.setText("");
                        				cityField.setText("");
                        			} else { // formato data non corretto
                        				JOptionPane.showMessageDialog(newWindow, "Day dosen't exist in that year", "Error", JOptionPane.ERROR_MESSAGE);
                        			}
                            	} else { // formato url non corretto
                            		JOptionPane.showMessageDialog(newWindow, "Url format isn't in the correct", "Error", JOptionPane.ERROR_MESSAGE);
                            	}
                            } else { // campi vuoti
                            	JOptionPane.showMessageDialog(newWindow, "All field must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                    	}
                    });
                    
                    
                    
                    // Elimina project selezionando la riga
                    newWindowDeleteButton.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        // Ottieni la riga selezionata
	                        int projectSelectedRow = newWindowTable.getSelectedRow();
	
	                        if (projectSelectedRow != -1) {
	                            // Rimuovi la riga selezionata
	                            newWindowTableModel.removeRow(projectSelectedRow);
	                            
	                            clientList.get(clientSelectedRow).removeClientProject(projectSelectedRow);
	                        } else {
	                            // Mostra un messaggio se non è stata selezionata alcuna riga
	                            JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "Error", JOptionPane.WARNING_MESSAGE);
	                        }
	                    }
                    });
                    
                    // Aggiungi il pannello di input al frame
                    JScrollPane newWindowScrollInputPane = new JScrollPane(newWindowInputPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    newWindow.add(newWindowScrollInputPane, BorderLayout.SOUTH);
                    
                    newWindow.setVisible(true);                   
                }
            }
        });
        
        // Imposta la dimensione del frame e visualizzalo
        frame.setSize(1500, 900);
        frame.setVisible(true);
    }
}
