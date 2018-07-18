package vn.com.phatngoit.restaurants2.Restaurants;

import java.io.Serializable;

/**
 * Created by HV on 5/30/2018.
 */

public class RestaurantClass implements Serializable {

    public String TenNhaHang, TrangThai;
    public int GiaGiaoToiThieu;
    public int HinhAnh;

    public RestaurantClass() {

    }

    public RestaurantClass(String tenNhaHang, String trangThai, int giaGiaoToiThieu, int hinhAnh) {
        TenNhaHang = tenNhaHang;
        TrangThai = trangThai;
        GiaGiaoToiThieu = giaGiaoToiThieu;
        HinhAnh = hinhAnh;
    }

    public RestaurantClass(RestaurantClass ob) {
        this.TenNhaHang = ob.TenNhaHang;
        this.TrangThai = ob.TrangThai;
        this.GiaGiaoToiThieu = ob.GiaGiaoToiThieu;
        this.HinhAnh = ob.HinhAnh;
    }
}
