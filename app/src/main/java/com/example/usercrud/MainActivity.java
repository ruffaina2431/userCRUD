package com.example.usercrud;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.usercrud.network.endpoints;
import com.example.usercrud.network.VolleySingleton;

import org.json.*;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Demo: Create a user
    private void createUser(String name, String email, String password) {
        StringRequest request = new StringRequest(Request.Method.POST, endpoints.CREATE,
                response -> Toast.makeText(this, "User Created: " + response, Toast.LENGTH_LONG).show(),
                error -> Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("email", email);
                map.put("password", password);
                return map;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    // Demo: Fetch all users
    private void fetchUsers() {
        StringRequest request = new StringRequest(Request.Method.GET, endpoints.READ_ALL,
                response -> {
                    try {
                        JSONArray users = new JSONArray(response);
                        StringBuilder userData = new StringBuilder();

                        for (int i = 0; i < users.length(); i++) {
                            JSONObject obj = users.getJSONObject(i);
                            String name = obj.getString("name");
                            String email = obj.getString("email");
                            userData.append("Name: ").append(name).append("\n");
                            userData.append("Email: ").append(email).append("\n\n");
                        }

                        // Show in a popup (AlertDialog)
                        new AlertDialog.Builder(this)
                                .setTitle("Fetched Users")
                                .setMessage(userData.toString())
                                .setPositiveButton("OK", null)
                                .show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "JSON Error", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show()
        );

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText idField = findViewById(R.id.editTextId);
        EditText nameField = findViewById(R.id.editTextName);
        EditText emailField = findViewById(R.id.editTextEmail);
        EditText passwordField = findViewById(R.id.editTextPassword);

        findViewById(R.id.btnCreate).setOnClickListener(v -> {
            String name = nameField.getText().toString();
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            createUser(name, email, password);
        });

        findViewById(R.id.btnRead).setOnClickListener(v -> fetchUsers());

        findViewById(R.id.btnUpdate).setOnClickListener(v -> {
            int id = Integer.parseInt(idField.getText().toString());
            String name = nameField.getText().toString();
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            updateUser(id, name, email, password);
        });

        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            int id = Integer.parseInt(idField.getText().toString());
            deleteUser(id);
        });

    }
    private void updateUser(int id, String name, String email, String password) {
        StringRequest request = new StringRequest(Request.Method.POST, endpoints.UPDATE,
                response -> Toast.makeText(this, "User Updated: " + response, Toast.LENGTH_LONG).show(),
                error -> Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(id));
                map.put("name", name);
                map.put("email", email);
                map.put("password", password);
                return map;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }
    private void deleteUser(int id) {
        StringRequest request = new StringRequest(Request.Method.POST, endpoints.DELETE,
                response -> Toast.makeText(this, "User Deleted: " + response, Toast.LENGTH_LONG).show(),
                error -> Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(id));
                return map;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

}