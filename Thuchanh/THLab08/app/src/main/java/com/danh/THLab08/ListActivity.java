package com.example.lab8;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Food> arrayList;
    FoodAdapter adapter;

    int selectedIndex = -1;   // lưu vị trí item đang nhấn giữ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);

        // dữ liệu mẫu
        arrayList = new ArrayList<>();
        arrayList.add(new Food("Hamburger", "Món Mỹ", 50000, R.drawable.image1));
        arrayList.add(new Food("Trà sữa", "Ngọt dễ uống", 35000, R.drawable.image2));
        arrayList.add(new Food("Pizza", "Món Ý", 120000, R.drawable.image3));

        adapter = new FoodAdapter(this, arrayList);
        listView.setAdapter(adapter);

        // bật menu khi nhấn giữ
        registerForContextMenu(listView);
    }

    // tạo menu Sửa / Xóa
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.food_menu, menu);

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;

        selectedIndex = info.position; // lấy vị trí item
    }

    // xử lý menu Sửa / Xóa
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (selectedIndex == -1) return false;

        int itemId = item.getItemId();

        if (itemId == R.id.mnEdit) {
            showEditDialog();
            return true;

        } else if (itemId == R.id.mnDelete) {
            arrayList.remove(selectedIndex);
            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    // dialog sửa món ăn
    private void showEditDialog() {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_food);

        EditText edtName = dialog.findViewById(R.id.edtName);
        EditText edtDes = dialog.findViewById(R.id.edtDes);
        EditText edtPrice = dialog.findViewById(R.id.edtPrice);

        Button btnSave = dialog.findViewById(R.id.btnSave);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        Food f = arrayList.get(selectedIndex);

        edtName.setText(f.getName());
        edtDes.setText(f.getDescription());
        edtPrice.setText(String.valueOf(f.getPrice()));

        btnSave.setOnClickListener(v -> {
            f.setName(edtName.getText().toString());
            f.setDescription(edtDes.getText().toString());
            f.setPrice(Integer.parseInt(edtPrice.getText().toString()));

            adapter.notifyDataSetChanged();
            dialog.dismiss();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
