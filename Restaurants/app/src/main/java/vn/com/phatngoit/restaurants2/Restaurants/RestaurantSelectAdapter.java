package vn.com.phatngoit.restaurants2.Restaurants;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.com.phatngoit.restaurants2.R;
import vn.com.phatngoit.restaurants2.databinding.ItemRecyclerviewBinding;

/**
 * Created by HV on 5/30/2018.
 */

public class RestaurantSelectAdapter extends RecyclerView.Adapter {
    //1. Khởi tạo danh sách
    List<RestaurantClass> listRest;
    Callback callback;

    public interface Callback {
        void onMenuClick(int position, RestaurantClass ob);
    }

    public void onClick(Callback callback) {
        this.callback = callback;
    }

    public RestaurantSelectAdapter(List<RestaurantClass> list) {
        this.listRest = list;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
    //-----------------------------------

    //3. Khởi tạo layout của 1 dòng
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Lấy layout trong thư mục res
        //đối tượng hỗ trợ truy xuất tài nguyên layout trong thư mục res/layout
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //binding đến layout muốn lấy (giao diện itemRecylerView phải nằm trọng cặp thẻ <layout></layout>)
        ItemRecyclerviewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recyclerview, parent, false);
        //lấy layout ra
        View view = binding.getRoot();

        //Cung cấp layout cho ViewHolder
        RecyclerView.ViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    //4. Gán giá trị từng đối tượng vào layout
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        //Tìm layout đối tượng
        ItemRecyclerviewBinding binding = DataBindingUtil.findBinding(holder.itemView);

        //Lấy giá trị từng dòng trong danh sách
        RestaurantClass restaurantClass = listRest.get(position);
        //gán giá trị vừa lấy vào đối tượng layout
        binding.tvTenNhaHang.setText(restaurantClass.TenNhaHang);
        binding.tvGia.setText("$" + String.valueOf(restaurantClass.GiaGiaoToiThieu) + " / ");
        binding.tvTrangThai.setText(restaurantClass.TrangThai);
        binding.imageView4.setImageResource(restaurantClass.HinhAnh);

        binding.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onMenuClick(holder.getAdapterPosition(), listRest.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    //2. Tổng số lượng item của danh sách
    @Override
    public int getItemCount() {
        return listRest.size();
    }
}
