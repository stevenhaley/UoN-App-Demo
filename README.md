# UoN-App-Demo
Repo for the practical session on Monday 15th June.

### Tasks

1. Create a system for saving and reopening a 'Person' according to the activity lifecycle
2. Add a way to change your biography
3. Send your position to the server, and verify its returned value.
4. Sort the list of other people based on distance
5. Send a connection request. Server structure is the same and the URL starts with `/connection_request_server/`
6. See who has sent you a connection request


### Useful Links

Retrofit: http://square.github.io/retrofit/

Android Developer site: http://developer.android.com/develop/index.html

### Code snippets
Examples of how to use the Gson and Guava libraries included in the project.

Convert an object to and from a String:
```java
Gson gson = new Gson();
Person person = getPersonFromSomewhere();
String personAsString = gson.toString(person);
Person newPerson = gson.fromJson(personAsString, Person.class);
```

Saving a String to the local storage. Option 1:
```java
String personAsString = getPersonAsString();
File file = new File(getFilesDir(), "person.json");
Files.write(personAsString, file, Charset.forName("UTF-8"));
String readFromStorage = Files.toString(file, Charset.forName("UTF-8"));
```

Option 2, use SharedPreferences: http://developer.android.com/guide/topics/data/data-storage.html#pref
