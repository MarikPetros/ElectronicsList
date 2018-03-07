package com.marik.electronicslist.activity;

import com.marik.electronicslist.R;
import com.marik.electronicslist.fragment.CategoriesFragment;
import com.marik.electronicslist.model.CategoryModel;
import com.marik.electronicslist.model.SubCategoryItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

/**
 * This main page for showing list ov products
 */
public class MainPageActivity extends AppCompatActivity implements CategoriesFragment.onCategoriesItemClickListener {

    public static final String LANGUAGE = "language";
    public static Locale current = Locale.getDefault();
    String language = current.getLanguage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        if (savedInstanceState != null) {
            language = savedInstanceState.getString(LANGUAGE);
            current = new Locale(language);
        }

        CategoriesFragment categoriesFragment = new CategoriesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, categoriesFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.electronics_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //select language
        switch (id) {
            case R.id.arm:
                current = new Locale("hy");
                language = "hy";
                Intent refresh = new Intent(this, MainPageActivity.class);
                startActivity(refresh);
                finish();

                break;
            case R.id.rus:
                current = new Locale("ru");
                language = "ru";
                refresh = new Intent(this, MainPageActivity.class);
                startActivity(refresh);
                finish();
                break;
            case R.id.eng:
                current = new Locale("en");
                language = "en";
                refresh = new Intent(this, MainPageActivity.class);
                startActivity(refresh);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LANGUAGE, language);
    }

    // Listener metod for Categories list
    @Override
    public void itemCategoryClick(CategoryModel categoryModel) {
        CategoriesFragment categoriesFragment = CategoriesFragment.getInstance(categoryModel);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, categoriesFragment).addToBackStack(null).commit();
    }

    // Listener method for subCategories list
    @Override
    public void itemClick(SubCategoryItem categoryModel) {
        CategoriesFragment categoriesFragment = CategoriesFragment.getInstance(categoryModel);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, categoriesFragment).addToBackStack(null).commit();
    }
}
