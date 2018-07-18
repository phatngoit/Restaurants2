package vn.com.phatngoit.restaurants2.Menu;

public class MenuClass extends MenuTieuDeClass {
    public String TenMon, DienGiai;
    public Integer Gia;

    public MenuClass(String menu, String tenMon, String dienGiai, Integer gia) {
        super(menu);
        TenMon = tenMon;
        DienGiai = dienGiai;
        Gia = gia;
    }
}
