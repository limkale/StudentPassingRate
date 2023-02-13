package com.studentPassingRate.login.dao;

import com.studentPasssingRate.user.model.UserModel;

public class LoginDao {

	public boolean validate(UserModel userModel) {
		if(userModel.getUserId().equals("user1") && userModel.getPassword().equals("password") ||
				userModel.getUserId().equals("user2") && userModel.getPassword().equals("password2")) {
			return true;
		}else {
			return false;
		}
	}
}
