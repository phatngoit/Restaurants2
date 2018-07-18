package vn.com.phatngoit.restaurants2;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vn.com.phatngoit.restaurants2.Class.MenuClass;
import vn.com.phatngoit.restaurants2.Class.MenuTieuDeClass;
import vn.com.phatngoit.restaurants2.databinding.ItemMenuNoidungBinding;
import vn.com.phatngoit.restaurants2.databinding.ItemMenuTieudeBinding;
//import vn.com.phatngoit.restaurants2.databinding.ItemMenuBinding;

public class MenuAdapter extends RecyclerView.Adapter {
    List<MenuTieuDeClass> listMenu;
    List<MenuTieuDeClass> tieuDeClassList = new ArrayList<>();
    int i = 0;

    public MenuAdapter(List<MenuTieuDeClass> listMenu) {
        this.listMenu = listMenu;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        MenuTieuDeClass menuTieuDeClass = listMenu.get(position);
        if (menuTieuDeClass instanceof MenuClass) {
            return 2;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if (viewType == 2) {
            ItemMenuNoidungBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_menu_noidung, parent, false);
            view = binding.getRoot();
        }
        else {
            ItemMenuTieudeBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_menu_tieude, parent, false);
            view = binding.getRoot();
        }

        RecyclerView.ViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final MenuTieuDeClass menuTieuDeClass = listMenu.get(position);

        if (menuTieuDeClass instanceof MenuClass) {
            ItemMenuNoidungBinding itemMenuNoidungBinding = DataBindingUtil.findBinding(holder.itemView);
            MenuClass menuClass = ((MenuClass) menuTieuDeClass);
            itemMenuNoidungBinding.tvTenMon.setText(menuClass.TenMon);
            itemMenuNoidungBinding.tvDienGiai.setText(menuClass.DienGiai);
            itemMenuNoidungBinding.tvGia.setText("$" + String.valueOf(menuClass.Gia));
        }
        else {
            final ItemMenuTieudeBinding binding = DataBindingUtil.findBinding(holder.itemView);
            binding.tvTieuDe.setText(menuTieuDeClass.Menu);

//            binding.tvTieuDe.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (i == 0) {
//                        for (MenuTieuDeClass tieuDeClass : listMenu) {
//                            if (tieuDeClass instanceof MenuClass && tieuDeClass.Menu.equals(menuTieuDeClass.Menu)) {
//                                tieuDeClassList.add(tieuDeClass);
//                            }
//                        }
//                        listMenu.removeAll(tieuDeClassList);
//                        notifyDataSetChanged();
//                        i++;
//                    }
//                    else {
//                        int pos = position;
//                        for (MenuTieuDeClass tieuDeClass : tieuDeClassList) {
//                            if (tieuDeClass instanceof MenuClass && tieuDeClass.Menu.equals(menuTieuDeClass.Menu)) {
//                                pos++;
//                                listMenu.add(pos, tieuDeClass);
//                            }
//                        }
//                        notifyDataSetChanged();
//                        i = 0;
//                    }
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }
}
