package com.example.roadrescue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ContactViewHolder>  {





    private Context context;
    private ArrayList<ModelContact> contactlist;

    private SavedContactsHelper savedContactsHelper;



    public AdapterContact(Context context, ArrayList<ModelContact> contactlist) {
        this.context = context;
        this.contactlist = contactlist;
        savedContactsHelper = new SavedContactsHelper(context);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_contact_item,parent,false);
        ContactViewHolder vh = new ContactViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ModelContact modelContact = contactlist.get(position);

        String id = modelContact.getId();
        String name = modelContact.getName();
        String email = modelContact.getEmail();
        String phone = modelContact.getPhone();

        holder.contactName.setText(name);
        holder.contactImage.setImageResource(R.drawable.baseline_person_2_24);

        holder.contactDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = modelContact.getPhone();

                Intent intent = new Intent(context,SosCallActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                context.startActivity(intent);

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContactDetail.class);
                intent.putExtra("contactId",id);
                context.startActivity(intent);

            }
        });


        holder.optionConact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.optionConact);
                popupMenu.inflate(R.menu.contactoption_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.action_popup_edit){
                            Intent intent = new Intent(context, AddUpdateContact.class);
                            intent.putExtra("ID",id);
                            intent.putExtra("NAME",name);
                            intent.putExtra("PHONE",phone);
                            intent.putExtra("EMAIL",email);

                            intent.putExtra("EditMode",true);
                            context.startActivity(intent);


                            return true;
                        }else if(item.getItemId()==R.id.action_popup_delete){

                            savedContactsHelper.deleteContact(id);
                            ((SosContactActivity)context).onPostResume();
                            return true;
                        }
                        else {
                            return false;
                        }

                    }
                });
                popupMenu.show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return contactlist.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder  {
        ImageView contactImage, contactDial, optionConact;
        TextView contactName;
        MenuItem editcontact;


    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);

        contactImage = itemView.findViewById(R.id.contact_image);
        contactDial = itemView.findViewById(R.id.contact_num_dial);
        contactName = itemView.findViewById(R.id.contact_name);
        optionConact = itemView.findViewById(R.id.option_contact);

    }


    }
}


