package bot;

import java.util.List;
import java.util.Random;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import bot.db.dao.MessageManager;
import bot.db.dao.UserManager;
import bot.db.model.Message;
import bot.db.model.User;

public class TestBot extends TelegramLongPollingBot{
	
	private MessageManager messageManager;
	private UserManager userManager;
	
	public TestBot() {
		messageManager = new MessageManager();
		userManager = new UserManager();
	}

	@Override
    public void onUpdateReceived(Update update) {
	    if (update.hasMessage() && update.getMessage().hasText()) {
	    	
	        String messageText = update.getMessage().getText();
	        String userFirstName = update.getMessage().getChat().getFirstName();
	        String userLastName = update.getMessage().getChat().getLastName();
	        String username = update.getMessage().getChat().getUserName();
	        long userId = update.getMessage().getChat().getId();
	        long chatId = update.getMessage().getChatId();

	        /*SendMessage message = new SendMessage() 
	                .setChatId(chatId)
	                .setText(messageText);*/

	        Message dbMessage = new Message();		//a new message to be added to the db
	        //dbMessage.setUserId(userId);
	        dbMessage.setChatId(chatId);
	        dbMessage.setUserMessage(messageText);
	        
	        SendPhoto photo = new SendPhoto();
	        photo.setChatId(chatId);
	        photo.setCaption("Photo");
	        String pictureURL = "";
	        
	        if(messageText.equals("1"))
	        {
		        List<String> pics = Util.getListOfBoobs(new Random().nextInt(551)+1);
		        String picNum = pics.get(new Random().nextInt(pics.size()));
		        pictureURL = "http://media.oboobs.ru/boobs_preview/"+picNum+".jpg";
		        
		        dbMessage.setMessageType(1);
	        }
	        else {
				pictureURL = Util.getPhotoArchive();
				dbMessage.setMessageType(2);
			}
	        dbMessage.setReply(pictureURL);
	        
	        photo.setPhoto(pictureURL);

	        Util.log(userFirstName, userLastName, Long.toString(userId), username, messageText, pictureURL);

	        try {
	            //sendMessage(message); // Sending our message object to user
	            sendPhoto(photo);
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	        
	        //DB staff
	        User user = userManager.getUserById(userId);
	        if(user==null){	//user has never written anything to the Bot	       
	        	user = new User(userId, username, userFirstName, userLastName);
	        	userManager.addValue(user);
	        }
	        dbMessage.setId(user.getId());
	        messageManager.addValue(dbMessage);
	        
	    }
    }

    @Override
    public String getBotUsername() {
        return Util.getPropertyValue("name");
    }

    @Override
    public String getBotToken() {
        return Util.getPropertyValue("token");
    }

}
