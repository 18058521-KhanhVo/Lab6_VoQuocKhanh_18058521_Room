package com.example.lab6_roomsql_voquockhanh_18058521;

import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private ArrayList<Location> locations;
    private Context context;
    private LayoutInflater inflater;
    private LocationDAO dao;
    private String s = "";

    public LocationAdapter(ArrayList<Location> locations, Context context, LocationDAO dao) {
        this.locations = locations;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.dao = dao;
    }

    @NonNull
    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new LocationViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocationViewHolder holder, int position) {
        Location location = locations.get(position);
        holder.txtName.setText(location.toString());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.delete(location);
                locations.clear();
                locations = (ArrayList<Location>) dao.getAll();
                notifyDataSetChanged();
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Edit Location Name");

                EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        s = (input.getText()).toString();
                        location.setName(s);
                        dao.edit(location);
                        locations.clear();
                        locations = (ArrayList<Location>) dao.getAll();
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        private LocationAdapter adapter;
        private TextView txtName;
        private ImageButton btnEdit;
        private ImageButton btnDelete;

        public LocationViewHolder(@NonNull View itemView, LocationAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            txtName = itemView.findViewById(R.id.txtname);
            btnEdit = itemView.findViewById(R.id.btnedit);
            btnDelete = itemView.findViewById(R.id.btndelete);
        }
    }
}
