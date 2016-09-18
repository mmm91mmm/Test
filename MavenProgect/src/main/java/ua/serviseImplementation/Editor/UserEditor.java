package ua.serviseImplementation.Editor;

import java.beans.PropertyEditorSupport;

import ua.service.UserService;
import ua.shop_e.User;

public class UserEditor extends PropertyEditorSupport{

	private final UserService userService;

	public UserEditor(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		User user = userService.findOneUser(Integer.valueOf(text));
		setValue(user);
	}
}