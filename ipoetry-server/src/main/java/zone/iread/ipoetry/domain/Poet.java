package zone.iread.ipoetry.domain;

import java.io.Serializable;

public class Poet implements Serializable {

    private int id;
    private String name;
    private String period;
    private String introduction;
    private String pic;
    private Byte sex;

    public Poet() {
    }

    public Poet(int id, String name, String period, String introduction, Byte sex) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.introduction = introduction;
        this.sex = sex;
    }

    public Poet(int id, String name, String period, String introduction, String pic, Byte sex) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.introduction = introduction;
        this.pic = pic;
        this.sex = sex;
    }

    public Poet(String name, String period, String introduction, String pic, Byte sex) {
        this.name = name;
        this.period = period;
        this.introduction = introduction;
        this.pic = pic;
        this.sex = sex;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
