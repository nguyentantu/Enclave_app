package com.bracesmedia.androidmaterialdashboard;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;
import model.Engineers;

public class MainActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtTotalEngineer;
    Button btnBack, btnProfile;

    LineChartView lineChartView;

    int totalEn = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();

    }

    private void addControls() {
        txtName = findViewById(R.id.txt_nameApp);
        txtName.setText("Enclave's Management System");


        btnBack = findViewById(R.id.btn_back);
        btnBack.setVisibility(View.GONE);

        btnProfile = findViewById(R.id.btn_profile);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lineChartView = findViewById(R.id.chart);

        txtTotalEngineer = findViewById(R.id.txt_totalEngineer);

        createChart();

        MainActivity.DanhSachSanPhamTask task = new MainActivity.DanhSachSanPhamTask();
        task.execute();
    }


    private void createChart() {
        String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
                "Oct", "Nov", "Dec"};
        int[] yAxisData = {500, 200, 150, 300, 200, 600, 120, 400, 450, 100, 900, 180};

        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();

        Line line = new Line(yAxisValues);

        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));

        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        lineChartView.setLineChartData(data);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);

        line.setColor(Color.RED);

        axis.setTextSize(16);

        axis.setTextColor(Color.parseColor("#03A9F4"));

        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);

        yAxis.setName("Revenue this year");
    }


    public void listEngineer(View view) {
        Intent intent = new Intent(MainActivity.this, ListEngineerActivity.class);
        startActivity(intent);
    }


    class DanhSachSanPhamTask extends AsyncTask<Void, Void, ArrayList<Engineers>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Engineers> sanPhams) {
            super.onPostExecute(sanPhams);

            txtTotalEngineer.setText(totalEn + "");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Engineers> doInBackground(Void... voids) {
            ArrayList<Engineers> dsEngineer = new ArrayList<>();
            try {
                URL url = new URL("https://cool-demo-api.herokuapp.com/api/v1/engineers");// link API
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

                InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
                JSONObject jsonArray = new JSONObject(builder.toString());
                totalEn = jsonArray.getInt("total");

                br.close();

            } catch (Exception ex) {
                Log.e("LOI", ex.toString());
            }
            return dsEngineer;
        }

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this); //Home is name of the activity
        builder.setMessage("Do you want to exit?");
        builder.setIcon(R.drawable.ic_warnig);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                System.exit(1);

            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert=builder.create();
        alert.show();

        //super.onBackPressed();
    }
}