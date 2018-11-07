package com.example.alejandro_neri.login.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.alejandro_neri.login.R;
import com.example.alejandro_neri.login.Util.Util;

public class LoginActivity extends AppCompatActivity {

    // 1. Declaramos las variables que necesitaremos
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch switchRemember;
    private Button btnLogin;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        bindUI();
        setCredentialsIfExist();


        // Esta es la manera en la que le damos vida a un botón., En este ejemplo, lo hacemos con el boton de login y su respectivo efecto de validación
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (login(email,password)){
                    goToMain();
                    saveOnPreferences(email, password);
                }
            }
        });
    }

    // 2. Este método traerá los elementos de nuestra interfaz (XML), para que los podamos usar en el código
    private void bindUI(){
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
    }

    // 6. el método servirá para recordar la sesión si esta fue iniciada.
    private void setCredentialsIfExist(){
        String email = Util.getUserEmail(preferences);
        String password = Util.getUserPassword(preferences);

        // si no están vacíos ninguno de los 2
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            editTextEmail.setText(email);
            editTextPassword.setText(password);
        }
    }

    private boolean login(String email, String password){
        if (!isValidEmail(email)){
            Toast.makeText(this, "The email is not a valid one", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)){
            Toast.makeText(this, "The email requires al least 5 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    // 3.1 Este método verificará que el correo sea válida
    private boolean isValidEmail(String email){
        // verifica que no este vacío, Y que tenga los campos que le corresponden a un corre @ .
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // 3.2 Este método verificará que la contraseña sea válida
    private boolean isValidPassword(String password){
        // Solamente verificará si esta es mayor a 4 caracteres
        return password.length() > 4;
    }

    // 4. Este método es el que se encargará de cambiar de pantalla
    private void goToMain(){
        Intent intentMain = new Intent(this, MainActivity.class);
        //en esta linea ordena que se le agreguen las banderas para que una vez que entró, no pueda regresar a esta
        intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentMain);
    }

    // 5. Este método usa Shared Preferences para guardar la infprmación del usuario.
    private void saveOnPreferences(String email, String password){
        if (switchRemember.isChecked()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            // este se usaría si necesitaramos que no haga nada hasta que guardara todas las llaves y valores
            //editor.commit();
            editor.apply();
        }
    }


    //los pasos 6.1 y 6.2, se mudaron al archivo util.java

}
