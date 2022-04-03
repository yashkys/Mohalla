package com.kys.mohalla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.kys.mohalla.databinding.ActivityMainBinding;
import com.kys.mohalla.ui.cart.CartFragment;
import com.kys.mohalla.ui.grocery.GroceryFragment;
import com.kys.mohalla.ui.medicine.MedicineFragment;
import com.kys.mohalla.ui.snacks.SnacksFragment;
import com.kys.mohalla.ui.stationery.StationeryFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replacementFragment(new GroceryFragment());

        mAuth = FirebaseAuth.getInstance();
        Button signout = binding.Signout;

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, IntroductionActivity.class));
            }
        });

        setSupportActionBar(binding.toolbar);

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.grocery_navigation, R.id.snacks_navigation, R.id.cart_navigation,
//                R.id.stationery_navigation, R.id.medicine_navigation)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);

        binding.navView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.navigation_groceries:
                    replacementFragment(new GroceryFragment());
                    break;

                case R.id.navigation_snacks:
                    replacementFragment(new SnacksFragment());
                    break;

                case R.id.navigation_cart:
                    replacementFragment(new CartFragment());
                    break;

                case R.id.navigation_stationery:
                    replacementFragment(new StationeryFragment());
                    break;

                case R.id.navigation_medicine:
                    replacementFragment(new MedicineFragment());
                    break;
            }
            return true;
        });
    }

    private void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}