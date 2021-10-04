package ec.edu.tecnologicoloja.contactlist.views;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ec.edu.tecnologicoloja.contactlist.R;
import ec.edu.tecnologicoloja.contactlist.Registro;
import ec.edu.tecnologicoloja.contactlist.adapter.ContactAdapter;

import ec.edu.tecnologicoloja.contactlist.manager.FirebaseContactManager;
import ec.edu.tecnologicoloja.contactlist.model.Contact;


public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView mListView;

    private ContactAdapter contactAdapter;
    private FloatingActionButton FBAgregar;
    private List<Contact> mListContacts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        //CONFIGURAR PROYECTO - PROBLEMA BOTON CON ERROR:
        FBAgregar = (FloatingActionButton) findViewById(R.id.fabBtonAgregar);
        FBAgregar.setOnClickListener(this);

        // Set views
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);

        // Set contact list
        mListContacts = FirebaseContactManager.getInstance().getAllContacts();
        // check if list is null or empty to show the list or an informative text
        if (mListContacts != null && mListContacts.size() > 0) {
            mListView.setVisibility(View.VISIBLE);

            // Init adapter
            contactAdapter = new ContactAdapter(this, mListContacts);
            mListView.setAdapter(contactAdapter);
        } else {

            mListView.setVisibility(View.GONE);

        }



    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // start detail screen when an item list is pressed
        Intent intent = new Intent();
        intent.setClass(ListActivity.this, ContactActivity.class);
        intent.putExtra("id", mListContacts.get(position).getObjectId());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(ListActivity.this, Registro.class);
        startActivity(i);
    }
}
