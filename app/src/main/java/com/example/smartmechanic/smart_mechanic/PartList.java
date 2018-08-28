package com.example.smartmechanic.smart_mechanic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.smartmechanic.smart_mechanic.Interface.ItemClickListener;
import com.example.smartmechanic.smart_mechanic.ViewHolder.PartViewHolder;
import com.example.smartmechanic.smart_mechanic.Model.Part;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PartList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference partList;

    String categoryId="";

    FirebaseRecyclerAdapter<Part,PartViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_list);

        database = FirebaseDatabase.getInstance();
        partList = database.getReference("Part");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_part);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() !=null) {
            categoryId = getIntent().getStringExtra("CategoryId");
        }
        if(!categoryId.isEmpty() && categoryId !=null){
            loadListPart(categoryId);
        }


    }

    private void loadListPart(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Part, PartViewHolder>(Part.class,R.layout.part,PartViewHolder.class,partList.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(PartViewHolder viewHolder, Part model, int position) {
                viewHolder.part_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.part_image);
                final Part local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(PartList.this,""+local.getName(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);
    }
}
