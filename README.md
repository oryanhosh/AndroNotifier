# AndroNotifier
[![API](https://img.shields.io/badge/API-14%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=14)

:zap: AndroNotifier is an Android library to make it easy for app developers to send notifications on a specific time. :zap:


# Gradle Setup
Add it in your root build.gradle at the end of repositories:
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency
```gradle
	dependencies {
	        implementation 'com.github.oryanhosh:AndroNotifier:11'
	}
```

# Using
### Creating a Notification
```java
String id = "ID_1";
long when = System.currentTimeMillis() + 3600; // in an hour
String contentTitle = "Example Title";
String contentText = "Example Content";
String contentInfo = "Example Info";
int smallIcon = R.drawable.icon;
String intentExtraStringKey = "key";
String intentExtraStringValue = "value";

AndroNotification notification = new AndroNotification(id,
                    when,
                    contentTitle,
                    contentText,
                    contentInfo,
                    smallIcon,
                    getClass(),
                    intentExtraStringKey,
                    intentExtraStringValue);
AndroNotifier.addNotification(this,notification);
```
### Removing a Notification
```java
AndroNotifier.removeNotification(this, id);
```
### Retrieving a Notification ArrayList
```java
ArrayList<AndroNotification> notifications = AndroNotifier.getNotifications(this);
```
# Special Thanks :heart:
YotamDaBeast

