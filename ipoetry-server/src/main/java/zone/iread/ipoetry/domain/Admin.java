package zone.iread.ipoetry.domain;

import java.io.Serializable;

/**
 * 管理员
 * 为了实现前后端分离，需要做序列化，则实现Serializable接口
 */
public class Admin implements Serializable {

    private Integer id;//主键
    private String name;//账号
    private String password;//密码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}