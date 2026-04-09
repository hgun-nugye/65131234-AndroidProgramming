package ntu.ntth.bottomnavigationbar;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import ntu.ntth.bottomnavigationbar.fragment.CartFragment;
import ntu.ntth.bottomnavigationbar.fragment.GiftsFragment;
import ntu.ntth.bottomnavigationbar.fragment.ProfileFragment;
import ntu.ntth.bottomnavigationbar.fragment.ShopFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                int id = item.getItemId();

                if (id == R.id.navigation_shop) {
                    fragment = new ShopFragment();
                    loadFragment(fragment);
                    return true;
                } else if (id == R.id.navigation_gifts) {
                    fragment = new GiftsFragment();
                    loadFragment(fragment);
                    return true;
                } else if (id == R.id.navigation_cart) {
                    fragment = new CartFragment();
                    loadFragment(fragment);
                    return true;
                } else if (id == R.id.navigation_profile) {
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
                }
                return false;
            }
        });
//        navigationView.setOnItemSelectedListener(item -> {
//            Fragment fragment;
//            int id = item.getItemId();
//
//            if (id == R.id.navigation_shop) {
//                fragment = new ShopFragment();
//                loadFragment(fragment);
//                return true;
//            } else if (id == R.id.navigation_gifts) {
//                fragment = new GiftsFragment();
//                loadFragment(fragment);
//                return true;
//            } else if (id == R.id.navigation_cart) {
//                fragment = new CartFragment();
//                loadFragment(fragment);
//                return true;
//            } else if (id == R.id.navigation_profile) {
//                fragment = new ProfileFragment();
//                loadFragment(fragment);
//                return true;
//            }
//            return false;
//        });

        // Load default fragment
        loadFragment(new ShopFragment());
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}