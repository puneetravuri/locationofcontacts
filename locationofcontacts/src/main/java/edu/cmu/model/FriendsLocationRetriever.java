package edu.cmu.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FriendsLocationRetriever {

	public void retrieveLocations(String connectionData,
			HttpServletRequest request) {

		List<Person> persons = new ArrayList<Person>();
		List<String> locations = new ArrayList<String>();

		Document doc = Jsoup.parse(connectionData);

		Elements people = doc.getElementsByTag("person");

		for (int i = 0; i < people.size(); i++) {
			Element person = people.get(i);

			String id = new String();
			Elements nodes = person.getElementsByTag("id");
			if (nodes != null && nodes.size() > 0)
				id = nodes.get(0).text().trim();

			String firstName = new String();
			nodes = person.getElementsByTag("first-name");
			if (nodes != null && nodes.size() > 0)
				firstName = nodes.get(0).text().trim();

			String lastName = new String();
			nodes = person.getElementsByTag("last-name");
			if (nodes != null && nodes.size() > 0)
				lastName = nodes.get(0).text().trim();

			String profileURL = new String();
			nodes = person.getElementsByTag("public-profile-url");
			if (nodes != null && nodes.size() > 0)
				profileURL = nodes.get(0).text().trim();

			String profilePicture = new String();
			nodes = person.getElementsByTag("picture-url");
			if (nodes != null && nodes.size() > 0)
				profilePicture = nodes.get(0).text().trim();

			String location = new String();
			nodes = person.getElementsByTag("location");
			if (nodes != null && nodes.size() > 0)
				location = nodes.get(0).getElementsByTag("name").get(0).text()
						.trim()
						+ ", "
						+ nodes.get(0).getElementsByTag("code").get(0).text()
								.trim();

			Person p = new Person(id, firstName, lastName, profilePicture,
					profileURL, location);

			if (location.length() > 0)
				locations.add(location);
			persons.add(p);
		}

		request.setAttribute("locations", locations);
		request.setAttribute("persons", persons);
	}
}
