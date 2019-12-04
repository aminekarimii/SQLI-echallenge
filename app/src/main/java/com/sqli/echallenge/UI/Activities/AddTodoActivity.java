package com.sqli.echallenge.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sqli.echallenge.Model.Todo;
import com.sqli.echallenge.R;
import com.sqli.echallenge.Utils.TinyDB;

import java.util.ArrayList;

public class AddTodoActivity extends AppCompatActivity {

    TinyDB tinyDB;
    private ArrayList<Object> todosList;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        tinyDB = new TinyDB(this);
        todosList = tinyDB.getListObject("firstInitList", Todo.class);

        // findviewbyid
        editText = findViewById(R.id.add_todo_edittext);
        findViewById(R.id.button_add_todo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInput()) {
                    if (!alreadyExist(editText.getText().toString())) {
                        todosList.add(new Todo(editText.getText().toString()));
                        tinyDB.putListObject("firstInitList", todosList);
                        Toast.makeText(AddTodoActivity.this, "Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else
                        Toast.makeText(AddTodoActivity.this, "This Todo already exists", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private boolean alreadyExist(String todo) {
        for (Object td : todosList) {
            Todo todo1 = (Todo) td;
            if (todo1.getDescription().toLowerCase().equals(todo.toLowerCase()))
                return true;
        }

        return false;
    }

    private boolean checkInput() {
        return editText.getText() != null;
    }
}
