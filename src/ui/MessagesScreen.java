package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Message;
import dao.MessageDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MessagesScreen extends Application {
	
    private ListView<Message> messagesListView = new ListView<>();
    private MessageDAO messageDAO = new MessageDAO();

    @Override
    public void start(Stage primaryStage) {
        ObservableList<Message> messages = FXCollections.observableArrayList(messageDAO.getAllMessages());
        messagesListView.setItems(messages);
        messagesListView.setCellFactory(param -> new ListCell<Message>() {
            @Override
            protected void updateItem(Message item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMessageContent() + " - " + item.getTimestamp());
                }
            }
        });

        Button newMessageButton = new Button("New Message");
        newMessageButton.setOnAction(e -> showNewMessageDialog());

        Button viewReplyButton = new Button("View Reply");
        viewReplyButton.setOnAction(e -> viewSelectedMessageReply());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteSelectedMessage());
        
        VBox layout = new VBox(10, messagesListView, newMessageButton, viewReplyButton, deleteButton);
        Scene scene = new Scene(layout, 400, 600);
        primaryStage.setTitle("Messages");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showNewMessageDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Message");
        dialog.setHeaderText("Send a new message");
        dialog.setContentText("Message:");

        dialog.showAndWait().ifPresent(messageContent -> {
            Message message = new Message();
            message.setPatientID(1); // Assuming a fixed patient ID for demonstration
            message.setMessageContent(messageContent);
            message.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            messageDAO.saveMessage(message);
            refreshMessages();
        });
    }

    private void viewSelectedMessageReply() {
        Message selectedMessage = messagesListView.getSelectionModel().getSelectedItem();
        if (selectedMessage != null && selectedMessage.getReplyContent() != null && !selectedMessage.getReplyContent().isEmpty()) {
            Alert replyAlert = new Alert(Alert.AlertType.INFORMATION);
            replyAlert.setTitle("Message Reply");
            replyAlert.setHeaderText("Reply Content:");
            replyAlert.setContentText(selectedMessage.getReplyContent());
            replyAlert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Reply");
            alert.setHeaderText(null);
            alert.setContentText("This message has not received a reply yet.");
            alert.showAndWait();
        }
    }
    
    private void deleteSelectedMessage() {
        Message selectedMessage = messagesListView.getSelectionModel().getSelectedItem();
        if (selectedMessage != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this message?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                messageDAO.deleteMessage(selectedMessage.getMessageID());
                refreshMessages();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Message Selected");
            alert.setContentText("Please select a message to delete.");
            alert.showAndWait();
        }
    }

    private void refreshMessages() {
        ObservableList<Message> messages = FXCollections.observableArrayList(messageDAO.getAllMessages());
        messagesListView.setItems(messages);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
