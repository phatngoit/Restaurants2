package vn.com.phatngoit.restaurants2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import vn.com.phatngoit.restaurants2.Class.MenuClass;
import vn.com.phatngoit.restaurants2.Class.MenuTieuDeClass;
import vn.com.phatngoit.restaurants2.Class.RestaurantClass;
import vn.com.phatngoit.restaurants2.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding binding;
    List<MenuTieuDeClass> menuTieuDeClasses;
    MenuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);

        //adapter = new MenuAdapter(menuTieuDeClasses);

//        Intent intent = getIntent();
//        binding.tvTenNhaHang.setText(intent.getStringExtra("TenNhaHang").toString());

        ////nhận từng field
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("ChiTiet");
//        binding.tvTenNhaHang.setText( bundle.getString("TenNhaHang", "").toString());
//        binding.tvTrangThai.setText(bundle.getString("TrangThai", "").toString());
//        binding.tvGia.setText(String.valueOf(bundle.getInt("GiaGiaoToiThieu", 0)));
//        binding.imgHinhAnh.setImageResource(bundle.getInt("HinhAnh", 0));

        //nhận đối tượng
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ChiTiet");
        RestaurantClass ob = (RestaurantClass) bundle.getSerializable("data");

        binding.tvTenNhaHang.setText(ob.TenNhaHang);
        binding.tvTrangThai.setText(ob.TrangThai);
        binding.tvGia.setText(" $"+ String.valueOf(ob.GiaGiaoToiThieu) + " /");
        binding.imgHinhAnh.setImageResource(ob.HinhAnh);

        menuTieuDeClasses = new ArrayList<>();
        adapter = new MenuAdapter(menuTieuDeClasses);

        menuTieuDeClasses.add(new MenuTieuDeClass("Starters"));
        menuTieuDeClasses.add(new MenuClass("Starters","Chicken Soup", "aaaaa", 12));
        menuTieuDeClasses.add(new MenuClass("Starters", "Chicken Chowmein", "bbbb", 36));

        menuTieuDeClasses.add(new MenuTieuDeClass("Mains"));
        menuTieuDeClasses.add(new MenuClass("Mains","The Noodle Special Sauce", "Served", 10));
        menuTieuDeClasses.add(new MenuClass("Mains","The Prawn Noodle Bowl", "vfcxvcx", 8));


        binding.recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
    }
}
