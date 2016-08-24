package com.example.lawson.androidsummery.detectmemory.entity;

/**
 * Created by Administrator on 2016/8/22.
 */
public class ObjWithoutContext {

    private String content;

    private String title;

    public ObjWithoutContext(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public ObjWithoutContext(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ObjWithoutContext{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjWithoutContext that = (ObjWithoutContext) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        return title != null ? title.equals(that.title) : that.title == null;

    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
