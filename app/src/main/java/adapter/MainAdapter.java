package adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bracesmedia.androidmaterialdashboard.R;

import model.Engineers;

public class MainAdapter  extends ArrayAdapter<Engineers> {


    Activity context;
    int resource;

    public MainAdapter(@NonNull Activity context, @LayoutRes int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = this.context.getLayoutInflater().inflate(this.resource, null);

        TextView txtId = (TextView) view.findViewById(R.id.txt_id);
        TextView txtName = (TextView) view.findViewById(R.id.txt_name);
        TextView txtUserName = (TextView) view.findViewById(R.id.txt_username);
        TextView txtEmail = (TextView) view.findViewById(R.id.txt_email);
        LinearLayout llContent = (LinearLayout) view.findViewById(R.id.ll_main);

        Engineers engineers =getItem(position);


        txtId.setText(engineers.getId()+"");
        txtName.setText(engineers.getName());
        txtUserName.setText(engineers.getUsername());
        txtEmail.setText(engineers.getEmail());



        return view;
    }
}

