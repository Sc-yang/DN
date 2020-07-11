package com.priv.expression1;

public class JdbcUtil {
    private String driverClass;
    private String url;
    private String userName;
    private String userPassWord;

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    @Override
    public String toString() {
        return "JdbcUtil{" +
                "driverClass='" + driverClass + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassWord='" + userPassWord + '\'' +
                '}';
    }
}
