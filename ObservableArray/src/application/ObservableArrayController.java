package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter.Change;

public class ObservableArrayController {
		
	@FXML
	private ComboBox<Person> comboBox;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField surnameTextField;

	@FXML
	private Button addButton;

	@FXML
	private Button removeButton;
	
	  @FXML
	    private ListView<Person> listView;
	
	

	private ObservableList<Person> personList;
	private ListProperty<Person> listProperty;
	private Person person;

	@FXML
	public void initialize() {
		
		int j = 0;
		List<Person> arrayList = new ArrayList<>();
		Random rand = new Random();
    	int  n = rand.nextInt(20) + 1;
    	
		arrayList.add(0,new Person(String.valueOf(n), "Rambo"));
		arrayList.add(1,new Person("James", String.valueOf(n)));
		arrayList.add(2,new Person(String.valueOf(n), "Jones"));
		arrayList.add(3,new Person("Robin", String.valueOf(n)));

		
		
		  ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
		        @Override
		        public Thread newThread(Runnable r) {
		            Thread thread = new Thread(r);
		            thread.setDaemon(true);
		            return thread;
		        }
		    });
		    scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
		    	
		        @Override
		        public void run() {
		        
		        	
		            final Runnable runnable = new Runnable() {
		                @Override
		                public void run() {
		                
		                	 int n = rand.nextInt(20) + 1;
		                	
		            		arrayList.set(0,new Person(String.valueOf(n), "Rambo"));
		            		 n = rand.nextInt(20) + 1;
		            		arrayList.set(1,new Person("James", String.valueOf(n)));
		            		  n = rand.nextInt(20) + 1;
		            		arrayList.set(2,new Person(String.valueOf(n), "Jones"));
		            		 n = rand.nextInt(20) + 1;
		            		arrayList.set(3,new Person("Robin", String.valueOf(n)));

		                	
		                	
		                	
		            	
		            		
		            		
		            		
		            		
		            		listProperty = new SimpleListProperty<>();
		            		personList = FXCollections.observableArrayList(arrayList);
		            		listProperty.set(personList);
		            		comboBox.itemsProperty().bindBidirectional(listProperty);
		            		listView.itemsProperty().bindBidirectional(listProperty);
		            		
		            		Button button = new Button();
		            		
		            		
		                }
		            };
		            Platform.runLater(runnable);
		        }
		    }, 0, 1, TimeUnit.SECONDS);

		 
		
		
		
		
		
		
		
		


		
		//personList.addListener(this::onChanged);

	}

	@FXML
	private void addPerson() {
		person = new Person(nameTextField.getText(), surnameTextField.getText());
		personList.add(person);
		System.out.println(personList);
	}

	@FXML
	private void deletePerson() {
		Person person = comboBox.getSelectionModel().getSelectedItem();
		personList.remove(person);
		System.out.println(personList);

	}
	
}
