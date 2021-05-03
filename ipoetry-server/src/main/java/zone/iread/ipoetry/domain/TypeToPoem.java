package zone.iread.ipoetry.domain;

/**
 * 诗词对应类型
 */
public class TypeToPoem {

    private int id;
    private int typeId;
    private int poemId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getPoemId() {
        return poemId;
    }

    public void setPoemId(int poemId) {
        this.poemId = poemId;
    }
}
