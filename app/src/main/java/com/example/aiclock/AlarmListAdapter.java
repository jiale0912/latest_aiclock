package com.example.aiclock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aiclock.backup.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdapter extends ArrayAdapter<Alarm> {

    private List<Alarm> alarms = new ArrayList<>();
    private Context context;

    public AlarmListAdapter(Context context, int resource, List<Alarm> objects) {
        super(context, resource, objects);
        this.context = context;
        this.alarms = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v==null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.alarm_card,null);
        }
        final Alarm mAlarm = alarms.get(position);

        TextView time = (TextView) v.findViewById(R.id.alarm_time);
        time.setText(" " + mAlarm.getHour() + ":" + mAlarm.getMin());
        return v;
    }
}
