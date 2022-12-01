package view;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import photos.Album;
import photos.Photo;
import photos.Tag;

public class SearchResultsController {

	private Scene scene;
	private Stage stage;
	private Parent root;
	@FXML
	private ListView<ImageView> listView;
	@FXML
	private TextField queryField;
	@FXML
	private DatePicker startDatePicker;
	@FXML
	private DatePicker endDatePicker;
	private ObservableList<Photo> matches = FXCollections.observableArrayList();
	ObservableList<ImageView> items = FXCollections.observableArrayList();

	public void goBack(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("nonAdminHomepage.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void createAlbum(ActionEvent e) throws IOException {
		TextInputDialog td = new TextInputDialog("enter any text");

		td.setHeaderText("enter album name");

		Optional<String> result = td.showAndWait();
		String tag;
		if (result.isEmpty()) {
			return;
		} else {
			tag = result.get();
		}
		Album newAlbum = new Album(tag);
		for (Album album : LoginScreenController.currentUser.getAlbumList()) {
			if (album.getName().equals(newAlbum.getName())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("Duplicate name");
				alert.showAndWait();
				return;
			}
		}
		LoginScreenController.currentUser.addAlbum(newAlbum);
		for (Photo p : matches) {
			newAlbum.addPhoto(p);
		}
	}

	public void searchByDate(ActionEvent e) throws IOException {
		LocalDate localDate1 = startDatePicker.getValue();
		Instant instant1 = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
		Date startDate = Date.from(instant1);

		LocalDate localDate2 = endDatePicker.getValue();
		Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
		Date endDate = Date.from(instant2);

		System.out.println(startDate + " " + endDate);

		for (Album a : Photos.uapp.listUserAlbums(LoginScreenController.currentUser.getName())) {
			for (Photo p : a.getPhotos()) {
				System.out.println(p.getDate());
				if (p.getDate().after(startDate) && p.getDate().before(endDate)) {
					if (matches.contains(p)) {
						continue;
					} else {
						matches.add(p);
					}

				}
			}
		}
		for (int i = 0; i < matches.size(); i++) {
			ImageView temp = new ImageView(matches.get(i).getImage());
			temp.setFitHeight(75);
			temp.setFitWidth(75);
			temp.setPreserveRatio(true);
			items.add(temp);
		}

		listView.setItems(items);
	}

	public void search(ActionEvent e) throws IOException {
		String query = queryField.getText();
		String[] tokens = query.split(" ");
		if (tokens.length == 1) {
			String[] split = query.split("=");
			String tagName = split[0];
			String tagValue = split[1];
			Tag compare = new Tag(tagName, tagValue);
			for (Album a : Photos.uapp.listUserAlbums(LoginScreenController.currentUser.getName())) {
				for (Photo p : a.getPhotos()) {
					for (Tag t : p.getTags()) {
						if (t.getTagName().equals(tagName) && t.getTagValue().equals(tagValue)) {
							if (matches.contains(p)) {
								continue;
							} else {
								matches.add(p);
							}
						}
					}
				}
			}
		} else if (tokens.length == 3) {

			if (tokens[1].compareTo("AND") == 0) {
				String[] split1 = tokens[0].split("=");
				String tagName1 = split1[0];
				String tagValue1 = split1[1];
				Tag compare1 = new Tag(tagName1, tagValue1);

				String[] split2 = tokens[2].split("=");
				String tagName2 = split2[0];
				String tagValue2 = split2[1];
				Tag compare2 = new Tag(tagName2, tagValue2);

				for (Album a : Photos.uapp.listUserAlbums(LoginScreenController.currentUser.getName())) {
					for (Photo p : a.getPhotos()) {
						boolean flag1 = false;
						boolean flag2 = false;
						for (Tag t : p.getTags()) {
							if (t.getTagName().equals(tagName1) && t.getTagValue().equals(tagValue1)) {
								flag1 = true;
							} else if (t.getTagName().equals(tagName2) && t.getTagValue().equals(tagValue2)) {
								flag2 = true;
							}
						}
						if (flag1 && flag2) {
							if (matches.contains(p)) {
								continue;
							} else {
								matches.add(p);
							}
						}
					}
				}
			} else if (tokens[1].compareTo("OR") == 0) {
				String[] split1 = tokens[0].split("=");
				String tagName1 = split1[0];
				String tagValue1 = split1[1];
				Tag compare1 = new Tag(tagName1, tagValue1);

				String[] split2 = tokens[2].split("=");
				String tagName2 = split2[0];
				String tagValue2 = split2[1];
				Tag compare2 = new Tag(tagName2, tagValue2);

				for (Album a : Photos.uapp.listUserAlbums(LoginScreenController.currentUser.getName())) {
					for (Photo p : a.getPhotos()) {
						boolean flag1 = false;
						boolean flag2 = false;
						for (Tag t : p.getTags()) {
							if (t.getTagName().equals(tagName1) && t.getTagValue().equals(tagValue1)) {
								flag1 = true;
							} else if (t.getTagName().equals(tagName2) && t.getTagValue().equals(tagValue2)) {
								flag2 = true;
							}
						}
						if (flag1 || flag2) {
							if (matches.contains(p)) {
								continue;
							} else {
								matches.add(p);
							}
						}
					}
				}
			}

		}

		for (int i = 0; i < matches.size(); i++) {
			ImageView temp = new ImageView(matches.get(i).getImage());
			temp.setFitHeight(75);
			temp.setFitWidth(75);
			temp.setPreserveRatio(true);
			items.add(temp);
		}

		listView.setItems(items);
	}

}
