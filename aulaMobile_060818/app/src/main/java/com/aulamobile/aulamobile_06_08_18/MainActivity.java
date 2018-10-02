package com.aulamobile.aulamobile_06_08_18;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAdd;
    private RecyclerView rvList;
    private LinkedList<Tarefa> list;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rvList = findViewById(R.id.rvList);
        this.fabAdd = findViewById(R.id.fabAdd);

        getElements();
        createActions();

        Toolbar tollbar = findViewById(R.id.iToolbar);
        tollbar.setTitle("Lista");

        setSupportActionBar(tollbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.rvList.setLayoutManager(linearLayoutManager);

        list = new LinkedList<>();
        for(int i = 1; i <= 30; i++){
          list.add(new Tarefa("Tarefa " + i));
       }

        adapter = new ListAdapter(this, list);
        rvList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                LoginUtil.remove(getApplicationContext());
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createActions() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Toast.makeText(getApplicationContext(),"Clicou!", Toast.LENGTH_SHORT).show();
                addTask();
            }
        });
    }

    private void addTask() {
        final EditText etTask = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Tarefa")
                .setMessage("Add tarefa").setView(etTask)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Tarefa tarefa = new Tarefa(etTask.getText().toString());
                        list.add(tarefa);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"Salvou!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancelar", null).create();

        dialog.show();
    }

    public void getElements() {
        this.rvList = findViewById(R.id.rvList);
        this.fabAdd = findViewById(R.id.fabAdd);
    }

    public void onClickList(Tarefa tarefa) {
        list.remove(tarefa);
        adapter.notifyDataSetChanged();
    }

    public void onEditList (final Tarefa tarefa) {
        final EditText etTask = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Tarefa")
                .setMessage("Editar tarefa").setView(etTask)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Tarefa tarefa = new Tarefa(etTask.getText().toString());
                        int index = 0;

                        tarefa.setTarefa(etTask.getText().toString());
                        for(int id = 0; id < list.size(); id++){
                            if(tarefa.getTarefa() == list.get(id).getTarefa()){
                                index = id;
                            }
                        }
                        list.set(index, tarefa);

                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"Editado!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancelar", null).create();

        dialog.show();
    }
}
