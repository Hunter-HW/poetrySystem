package zone.iread.ipoetry.service;

public interface AdminService {

    /**
     * 验证密码是否正确
     */
    public boolean verifyPassword(String username,String password);
}
