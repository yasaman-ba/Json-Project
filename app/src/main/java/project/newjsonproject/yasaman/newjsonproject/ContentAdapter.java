package project.newjsonproject.yasaman.newjsonproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends ArrayAdapter{

    List list = new ArrayList();


    public ContentAdapter(@NonNull Context context, int resource) {
        super(context, resource);

    }


    public void add(@Nullable Content object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        row=convertView;
        ContentHolder contentHolder;

        if(row==null){

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_laout, parent, false);
            contentHolder = new ContentHolder();

            contentHolder.txt_name = (TextView) row.findViewById(R.id.txt_name);
            contentHolder.txt_password = (TextView) row.findViewById(R.id.txt_password);
            contentHolder.txt_contact = (TextView) row.findViewById(R.id.txt_contact);
            contentHolder.txt_country = (TextView) row.findViewById(R.id.txt_country);
            row.setTag(contentHolder);


        } else {

            contentHolder= (ContentHolder) row.getTag();
        }

        Content content = (Content) this.getItem(position);

        contentHolder.txt_name.setText(content.getName());
        contentHolder.txt_password.setText(content.getPassword());
        contentHolder.txt_contact.setText(content.getContact());
        contentHolder.txt_country.setText(content.getCountry());


        return row;
    }

    static class ContentHolder {

        TextView txt_name, txt_password, txt_contact, txt_country;
    }
}
