package ec.edu.tecnologicoloja.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ec.edu.tecnologicoloja.contactlist.model.Contact;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNombre;
    private EditText txtCorreo;
    private EditText txtCiudad;
    private EditText txtDescripcion;
    private EditText txtTelefono;
    private Button btnGuardar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtNombre = (EditText) findViewById(R.id.textNombre);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtCiudad = (EditText) findViewById(R.id.textCiudad);
        txtDescripcion = (EditText) findViewById(R.id.textDescripcion);
        txtTelefono = (EditText) findViewById(R.id.textTelefono);
        btnGuardar = (Button) findViewById(R.id.btnguardar);
        btnGuardar.setOnClickListener(this);


        //METODO PARA INICIALIZAR FAREBASE
        inicializarFarebase();

    }
    //AQUI SE INICIALIZA
    private void inicializarFarebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    @Override
    public void onClick(View view) {
guardarBD();
    }

    public void guardarBD(){
        Contact contact = new Contact();
        contact.setName(txtNombre.toString());
        contact.setEmail(txtCorreo.toString());
        contact.setCity(txtCiudad.toString());
        contact.setPhone(txtTelefono.toString());
        contact.setDescription(txtDescripcion.toString());
        databaseReference.child("Contacto").child(contact.getObjectId()).setValue(contact);

    }
}