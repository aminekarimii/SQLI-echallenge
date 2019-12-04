package com.sqli.echallenge.UI.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sqli.echallenge.Model.Todo;
import com.sqli.echallenge.R;
import com.sqli.echallenge.UI.Adapters.TodosAdapter;
import com.sqli.echallenge.UI.Interfaces.RecyclerViewClickListener;
import com.sqli.echallenge.Utils.TinyDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Object> todosList = new ArrayList<>();
    ;
    private TinyDB tinyDB;
    SharedPreferences prefs = null;
    private RecyclerView todosRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        //TinyDB helper call
        tinyDB = new TinyDB(this);

        //Calling Views
        todosRecycler = findViewById(R.id.todos_recycler);
        todosRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        findViewById(R.id.add_todo).setOnClickListener(this);

        // First List of default items
        listData();

    }


    /**
     * this function list data from cached Todos List
     */
    private void listData() {

        todosList = tinyDB.getListObject("firstInitList", Todo.class);

        TodosAdapter adapter = new TodosAdapter((ArrayList) todosList,
                new RecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, Todo todo) {
                        Toast.makeText(MainActivity.this, todo.getDescription(), Toast.LENGTH_SHORT).show();
                        deleteTodoFromTDB(todo);
                    }
                });
        todosRecycler.setAdapter(adapter);

    }

    /**
     * This function to delete a todoObject from Todolist and update TodosList State
     *
     * @param todoToDel is the TodoItem to Delete
     */
    private void deleteTodoFromTDB(Todo todoToDel) {
        todosList = tinyDB.getListObject("firstInitList", Todo.class);

        for (Object td : todosList) {
            Todo todo1 = (Todo) td;
            if (todo1.getDescription().toLowerCase().equals(todoToDel.getDescription().toLowerCase()))
                todosList.remove(todo1);
        }
        tinyDB.putListObject("firstInitList", todosList);
        listData();

    }


    /**
     * This function is executed one time in the first time the application is lunched
     */

    private void initListWithFirstData() {
        Log.v("debuging", todosList.toString());
        todosList.add(new Todo("todo test 1"));
        todosList.add(new Todo("todo test 2"));
        todosList.add(new Todo("todo test 3"));
        todosList.add(new Todo("todo test 4"));
        tinyDB.putListObject("firstInitList", todosList);
        listData();
    }


    /**
     * if user open the application the first time, a boolean will be created and store in SharedPrefs
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {

            //The first list to list to user :
            initListWithFirstData();

            prefs.edit().putBoolean("firstrun", false).apply();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_todo) {
            startActivity(new Intent(this, AddTodoActivity.class));
            this.finish();
        }
    }
}
