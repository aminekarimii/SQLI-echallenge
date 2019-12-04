package com.sqli.echallenge.UI.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sqli.echallenge.Model.Todo;
import com.sqli.echallenge.R;
import com.sqli.echallenge.UI.Interfaces.RecyclerViewClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.ViewHolder> {

    private List<Todo> todos;
    private RecyclerViewClickListener mClickListener;

    public TodosAdapter(ArrayList<Todo> todos, RecyclerViewClickListener clickListener) {
        this.todos = todos;
        this.mClickListener = clickListener;
    }

    public List<Todo> getItems() {
        return todos;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_container_row,
                parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindModel(todos.get(position));
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Todo mTodo;
        TextView myTodo;
        Button delete_todo;


        ViewHolder(View v) {
            super(v);
            myTodo = itemView.findViewById(R.id.todo_textview);
            delete_todo = itemView.findViewById(R.id.delete_todo);

            delete_todo.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        void bindModel(Todo todos) {

            this.mTodo = todos;
            myTodo.setText(todos.getDescription());

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.delete_todo) {
                mClickListener.onClick(view, mTodo);
            }

        }
    }

}