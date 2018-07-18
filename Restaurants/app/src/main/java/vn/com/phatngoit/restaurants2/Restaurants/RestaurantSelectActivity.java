package vn.com.phatngoit.restaurants2.Restaurants;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import vn.com.phatngoit.restaurants2.Menu.MenuActivity;
import vn.com.phatngoit.restaurants2.R;
import vn.com.phatngoit.restaurants2.databinding.ActivityRestaurantSelectBinding;

public class RestaurantSelectActivity extends AppCompatActivity {

    ActivityRestaurantSelectBinding binding;
    List<RestaurantClass> listRest = new ArrayList<>();
    RestaurantSelectAdapter adapter = new RestaurantSelectAdapter(listRest);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_select);

        //add list object

        RestaurantClass item = new RestaurantClass();
        item.TenNhaHang = "The Noodle";
        item.GiaGiaoToiThieu = 25;
        item.TrangThai = "Open";
        item.HinhAnh = R.drawable.noodles;
        listRest.add(item);

        item = new RestaurantClass();
        item.TenNhaHang = "Subway";
        item.GiaGiaoToiThieu = 15;
        item.TrangThai = "Open";
        item.HinhAnh = R.drawable.subway;
        listRest.add(item);

        item = new RestaurantClass();
        item.TenNhaHang = "Dunkin Donuts";
        item.GiaGiaoToiThieu = 55;
        item.TrangThai = "Closed";
        item.HinhAnh = R.drawable.dunkindonuts;
        listRest.add(item);

        item = new RestaurantClass();
        item.TenNhaHang = "Jack in the box";
        item.GiaGiaoToiThieu = 55;
        item.TrangThai = "Open";
        item.HinhAnh = R.drawable.jackinthebox;
        listRest.add(item);

        item = new RestaurantClass();
        item.TenNhaHang = "The Noodle";
        item.GiaGiaoToiThieu = 25;
        item.TrangThai = "Open";
        item.HinhAnh = R.drawable.noodles;
        listRest.add(item);

        item = new RestaurantClass();
        item.TenNhaHang = "Subway";
        item.GiaGiaoToiThieu = 15;
        item.TrangThai = "Open";
        item.HinhAnh = R.drawable.subway;
        listRest.add(item);

        item = new RestaurantClass();
        item.TenNhaHang = "Dunkin Donuts";
        item.GiaGiaoToiThieu = 55;
        item.TrangThai = "Closed";
        item.HinhAnh = R.drawable.dunkindonuts;
        listRest.add(item);

        item = new RestaurantClass();
        item.TenNhaHang = "Jack in the box";
        item.GiaGiaoToiThieu = 55;
        item.TrangThai = "Open";
        item.HinhAnh = R.drawable.jackinthebox;
        listRest.add(item);

        //khởi tạo adapter
        binding.recylerViewList.setAdapter(adapter);

        //cung cấp adapter cho recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recylerViewList.setLayoutManager(linearLayoutManager);


        //event
        adapter.onClick(new RestaurantSelectAdapter.Callback() {
            @Override
            public void onMenuClick(int position, RestaurantClass ob) {
                //Toast.makeText(getApplicationContext(), ob.TenNhaHang, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

                //truyền từng field
//                Bundle bundle = new Bundle();
//                bundle.putString("TenNhaHang", ob.TenNhaHang);
//                bundle.putString("TrangThai", ob.TrangThai);
//                bundle.putInt("GiaGiaoToiThieu", ob.GiaGiaoToiThieu);
//                bundle.putInt("HinhAnh", ob.HinhAnh);

//                intent.putExtra("ChiTiet", bundle);

                //truyền đối tượng
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", ob);
                intent.putExtra("ChiTiet", bundle);
                startActivity(intent);
            }
        });
    }
}
