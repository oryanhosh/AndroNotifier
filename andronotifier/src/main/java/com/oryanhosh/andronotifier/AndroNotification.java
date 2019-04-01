package com.oryanhosh.andronotifier;

public class AndroNotification {
    private String id;
    private long when;
    private String contentTitle;
    private String contentText;
    private String contentInfo;
    private int smallIcon;
    private String intentClassName;
    private String intentExtraStringKey;
    private String intentExtraStringValue;

    public AndroNotification(String id, long when, String contentTitle, String contentText, String contentInfo, int smallIcon, Class intentClass) {
        this.id = id;
        this.when = when;
        this.contentTitle = contentTitle;
        this.contentText = contentText;
        this.contentInfo = contentInfo;
        this.smallIcon = smallIcon;
        this.intentClassName = intentClass.getCanonicalName();
        this.intentExtraStringKey = "";
        this.intentExtraStringValue = "";
    }

    public AndroNotification(String id, long when, String contentTitle, String contentText, String contentInfo, int smallIcon) {
        this.id = id;
        this.when = when;
        this.contentTitle = contentTitle;
        this.contentText = contentText;
        this.contentInfo = contentInfo;
        this.smallIcon = smallIcon;
        this.intentClassName = "";
        this.intentExtraStringKey = "";
        this.intentExtraStringValue = "";
    }

    public AndroNotification(String id, long when, String contentTitle, String contentText, String contentInfo, int smallIcon, Class intentClass, String intentExtraStringKey, String intentExtraStringValue) {
        this.id = id;
        this.when = when;
        this.contentTitle = contentTitle;
        this.contentText = contentText;
        this.contentInfo = contentInfo;
        this.smallIcon = smallIcon;
        this.intentClassName = intentClass.getCanonicalName();
        this.intentExtraStringKey = intentExtraStringKey;
        this.intentExtraStringValue = intentExtraStringValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentInfo() {
        return contentInfo;
    }

    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo;
    }

    public int getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(int smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getIntentClassName() {
        return intentClassName;
    }

    public void setIntentClassName(String intentClassName) {
        this.intentClassName = intentClassName;
    }

    public String getIntentExtraStringKey() {
        return intentExtraStringKey;
    }

    public void setIntentExtraStringKey(String intentExtraStringKey) {
        this.intentExtraStringKey = intentExtraStringKey;
    }

    public String getIntentExtraStringValue() {
        return intentExtraStringValue;
    }

    public void setIntentExtraStringValue(String intentExtraStringValue) {
        this.intentExtraStringValue = intentExtraStringValue;
    }
}
