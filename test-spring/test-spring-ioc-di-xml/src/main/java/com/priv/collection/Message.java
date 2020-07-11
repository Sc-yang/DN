package com.priv.collection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 此消息类依然若干集合
 * 此消息类的对象依然若干集合数据
 */
public class Message {

    private List list;
    private Set set;
    private Map map;
    private Properties props;

    public void setList(List list) {
        this.list = list;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "Message{" +
                "list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", props=" + props +
                '}';
    }
}
