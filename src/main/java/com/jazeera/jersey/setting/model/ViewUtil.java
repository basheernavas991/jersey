package com.jazeera.jersey.setting.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

/**
 * Utility class for adding Validation Messages to <code>Model</code>
 * <p>Possible Validation Messages are of Type defined in MessagesType enum Class
 * @author navas
 * @since 0.1.0
 *
 */
public class ViewUtil {

	private List<String> success = new ArrayList<String>(), 
								errors = new ArrayList<String>(),
								errorMessages = new ArrayList<String>(),
								warnings = new ArrayList<String>(),
								infos = new ArrayList<String>();
	
	/**
	 * Add Success Messages to Model
	 * @param model
	 * @param message
	 */
	public void addSuccess(Model model, String message){
		List<String> messages = getMessages(model, MessageType.SUCCESS);
		messages.add(message);
		model.addAttribute(MessageType.SUCCESS.getName(), messages);
	}
	
	/**
	 * Add Error Messages to Model
	 * @param model
	 * @param message
	 */
	public void addError(Model model, String message){
		List<String> messages = getMessages(model, MessageType.ERROR);
		messages.add(message);
		model.addAttribute(MessageType.ERROR.getName(), messages);
	}
	
	/**
	 * Add Error Messages to Model
	 * @param model
	 * @param message
	 */
	public void addErrorMessage(Model model, String message){
		List<String> messages = getMessages(model, MessageType.ERROR_MESSAGE);
		messages.add(message);
		model.addAttribute(MessageType.ERROR_MESSAGE.getName(), messages);
	}
	
	
	
	/**
	 * Add Info Messages to Model
	 * @param model
	 * @param message
	 */
	public void addInfo(Model model, String message){
		List<String> messages = getMessages(model, MessageType.INFO);
		messages.add(message);
		model.addAttribute(MessageType.INFO.getName(), messages);
	}
	
	/**
	 * Add Warning Messages to Model
	 * @param model
	 * @param message
	 */
	public void addWarning(Model model, String message){
		List<String> messages = getMessages(model, MessageType.WARNING);
		messages.add(message);
		model.addAttribute(MessageType.WARNING.getName(), messages);
	}
	
	@SuppressWarnings("unchecked")
	private List<String> getMessages(Model model, MessageType type){
		if(model.containsAttribute(type.getName())){
			return (List<String>) model.asMap().get(type.getName());
		}else{
			return getMessages(type);
		}
	}
	
	private List<String> getMessages(MessageType type) {
		if(type == MessageType.SUCCESS){
			return success;
		}else if(type == MessageType.ERROR){
			return errors;
		}else if(type == MessageType.WARNING){
			return warnings;
		}else{
			return infos;
		}
	}

	private enum MessageType {
		
		SUCCESS("success"),
		ERROR("error"),
		ERROR_MESSAGE("errorMessage"),
		WARNING("warning"),
		INFO("info");
		
		private final String name;

	    /**
	     * @param text
	     */
	    private MessageType(String name) {
	        this.name = name;
	    }
	    
	    private String getName() {
			return name;
		}
	}
}

