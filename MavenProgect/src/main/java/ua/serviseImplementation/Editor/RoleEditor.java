package ua.serviseImplementation.Editor;

import java.beans.PropertyEditorSupport;

import ua.service.RoleService;
import ua.shop_e.Role;

public class RoleEditor extends PropertyEditorSupport{

	private final RoleService roleService;

	public RoleEditor(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Role role = roleService.findOne(Integer.valueOf(text));
		setValue(role);
	}
}