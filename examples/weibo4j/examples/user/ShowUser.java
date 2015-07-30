package weibo4j.examples.user;

import weibo4j.Users;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

public class ShowUser {

	public static void main(String[] args) {
//		String access_token = args[0];			
		String access_token = "2.00ZTQAvCOrgHnD16b836f56bX5c2ME";
		String screen_name ="莫大仙岳居";
		Users um = new Users();
		um.client.setToken(access_token);
		try {
			User user =
			um.showUserByScreenName(screen_name);
			Log.logInfo(user.getId());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
