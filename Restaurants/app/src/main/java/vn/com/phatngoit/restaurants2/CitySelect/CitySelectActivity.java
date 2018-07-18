package vn.com.phatngoit.restaurants2.CitySelect;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.com.phatngoit.restaurants2.R;
import vn.com.phatngoit.restaurants2.databinding.CityselectBinding;

public class CitySelectActivity extends AppCompatActivity {

    private CityselectBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.cityselect);
    }
}
