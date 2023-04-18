package com.example.parcial_dos;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.parcial_dos.model.Empleado;
import com.example.parcial_dos.sininterface.CrudEmpleadoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {





    public  Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.101.75:8081/")
            .addConverterFactory(GsonConverterFactory.create()).build();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        // obtener todos los datos
        getAll();






        // crear empleado
        Empleado e= new Empleado("1113677062","santiago calero calero","sistemas29232","santiago.calero01@usc.edu.co");
        crear_empleado(e);






        // actulizar empleado por id
        int id=1113677062;
        Empleado em = new Empleado("1113677062","santiago calero beltran","sistemas29232","santiago.calero01@usc.edu.co");
        actualizar_empleado(id,em);






       // eliminar empleado por id
        int id_borrar=1113677062;
        eliminar_empleado_id(id_borrar);





        consultar_ID(1113677062); // consultar por id
    }









    public void eliminar_empleado_id(int id){



        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.eliminar_empleado_id(id);


        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                Toast.makeText(MainActivity.this, "Empleado borrado con Exito", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }// fn mtd consultar_id



















    public void consultar_ID(int id){


        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.consultar_ID(id);


        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                Toast.makeText(MainActivity.this, "Empleado borrado con Exito", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }// fn mtd consultar_id




    public void actualizar_empleado(int id,Empleado u){


        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.actualizar_empleado(id, u);


        call.enqueue(new Callback<Empleado>() {

            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Empleado Actulizado con Exito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }








    public void crear_empleado(Empleado u){


        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.crear_empleado(u);



        call.enqueue(new Callback<Empleado>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(!response.isSuccessful()){

                    Log.e("text",response.message());
                    return;
                }

                List<Empleado> listEmpleado = (List<Empleado>) response.body();

                listEmpleado.forEach(p-> Log.i("emp",p.getNombre()));

                Toast.makeText(MainActivity.this,"correcto",Toast.LENGTH_SHORT).show();
            }



            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }











    private void getAll(){



        CrudEmpleadoInterface cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<List<Empleado>> call = cruempleado.getAll();



        call.enqueue(new Callback<List<Empleado>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {


                if(!response.isSuccessful()){

                    Log.e("text",response.message());
                    return;
                }

                List<Empleado> listEmpleado = response.body();

                listEmpleado.forEach(p-> Log.i("emp",p.getNombre()));

                Toast.makeText(MainActivity.this,"correcto",Toast.LENGTH_SHORT).show();


            }




            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();


            }


        });





    }// fn retrofit obtener_todo






























}// fn clase