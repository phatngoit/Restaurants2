package vn.com.phatngoit.restaurants2.OrderFinal;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.com.phatngoit.restaurants2.databinding.ActivityOptionsBinding;
//import vn.com.phatngoit.restaurants2.databinding.ItemOrderFinalBinding;

public class OrderFinalAdapter extends RecyclerView.Adapter {
    private List<OrderFinalClass> listOrderFinal;

    public OrderFinalAdapter(List<OrderFinalClass> listOrderFinal) {
        this.listOrderFinal = listOrderFinal;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        ItemOrderFinalBinding binding = DataBindingUtil.inflate(layoutInflater, R.la)
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listOrderFinal.size();
    }
}
