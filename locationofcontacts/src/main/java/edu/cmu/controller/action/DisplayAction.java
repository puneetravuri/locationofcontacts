package edu.cmu.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import edu.cmu.model.FriendsLocationRetriever;
import edu.cmu.utils.OAuthUtils;

/**
 * Performs display action
 * 
 */
public class DisplayAction extends Action {

	private String peopleURL = "http://api.linkedin.com/v1/people/~/connections:(id,first-name,last-name,picture-url,location)";

	@Override
	public String getName() {
		return "display.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Token accessToken = (Token) session.getAttribute("accessToken");
		OAuthService serv = OAuthUtils.getOAuthService();

		if (accessToken == null) {
			System.out.println("Access Token is null");
			return "login.jsp";
		}

		OAuthRequest authReq = new OAuthRequest(Verb.GET, peopleURL);
		serv.signRequest(accessToken, authReq);
		Response res = authReq.send();

		FriendsLocationRetriever fsr = new FriendsLocationRetriever();
		fsr.retrieveLocations(res.getBody(), request);

		return "display.jsp";
	}
}
