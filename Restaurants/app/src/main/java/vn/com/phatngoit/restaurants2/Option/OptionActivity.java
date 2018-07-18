package vn.com.phatngoit.restaurants2.Option;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.com.phatngoit.restaurants2.R;
import vn.com.phatngoit.restaurants2.databinding.ActivityOptionsBinding;

public class OptionActivity extends AppCompatActivity {
    private ActivityOptionsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_options);
    }
}

