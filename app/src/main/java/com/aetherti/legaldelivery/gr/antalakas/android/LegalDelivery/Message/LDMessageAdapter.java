package com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.aetherti.legaldelivery.R;

public class LDMessageAdapter extends ArrayAdapter<LegalDeliveryMessage> {

    private final List<LegalDeliveryMessage> list;
    private final Activity context;

    public LDMessageAdapter(Activity context, List<LegalDeliveryMessage> objects) {
        super(context, R.layout.ld_download_line, objects);
        this.context = context;
        this.list = objects;
    }

    static class ViewHolder {
        TextView serviceTypeText;
        TextView casepaperType;
        TextView fullnameText;
        TextView textViewfullname;
        TextView bussnameText;
        TextView textViewbuss;
        TextView stndfullnameText;
        TextView textViewstndfullname;
        TextView addressText;
        TextView aptText;
        TextView cityText;
        TextView stateText;
        TextView zipText;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (convertView == null) {

            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.ld_download_line, null);

            final ViewHolder viewHolder = new ViewHolder();

            viewHolder.serviceTypeText = (TextView) view.findViewById(R.id.dl_servicetypeTextView);
            viewHolder.casepaperType = (TextView) view.findViewById(R.id.dl_CasepaperTypeTextView);
            viewHolder.fullnameText = (TextView) view.findViewById(R.id.dl_fullnameTextView);
            viewHolder.textViewfullname = (TextView) view.findViewById(R.id.textViewfullname);
            viewHolder.bussnameText = (TextView) view.findViewById(R.id.dl_bussnameTextView);
            viewHolder.textViewbuss = (TextView) view.findViewById(R.id.textViewbussname);
            viewHolder.stndfullnameText = (TextView) view.findViewById(R.id.dl_stndfullnameTextView);
            viewHolder.textViewstndfullname = (TextView) view.findViewById(R.id.textViewstndfullname);
            viewHolder.addressText = (TextView) view.findViewById(R.id.dl_addressTextView);
            viewHolder.aptText = (TextView) view.findViewById(R.id.dl_aptTextView);
            viewHolder.cityText = (TextView) view.findViewById(R.id.dl_cityTextView);
            viewHolder.stateText = (TextView) view.findViewById(R.id.dl_stateTextView);
            viewHolder.zipText = (TextView) view.findViewById(R.id.dl_zipTextView);

            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.serviceTypeText.setText(list.get(position).getServiceType());

        int count = 20;
        if (list.get(position).getSTND_ServiceType() != null) {
            char[] stndServiceArr = list.get(position).getSTND_ServiceType().toCharArray();
            for (int i1 = 0; i1 < stndServiceArr.length; i1++) {
                if (stndServiceArr[i1] == '1') {
                    count = i1;
                    break;
                }
            }
        }
        String STND_ServiceType;
        switch (count) {
            case 0:
                STND_ServiceType = "Summons";
                break;
            case 1:
                STND_ServiceType = "Summons w/notice";
                break;
            case 2:
                STND_ServiceType = "Summons w/verified complaint";
                break;
            case 3:
                STND_ServiceType = "Summons w/notice & w/verified complaint";
                break;
            case 4:
                STND_ServiceType = "Summons w/endorsed complaint";
                break;
            case 5:
                STND_ServiceType = "Citation";
                break;
            case 6:
                STND_ServiceType = "Judicial Subpoena";
                break;
            case 7:
                STND_ServiceType = "Judicial Subpoen Ducestecum";
                break;
            case 8:
                STND_ServiceType = "Judicial Subpoen & Judicial Subpoen Ducestecum";
                break;
            case 9:
                STND_ServiceType = "Notice of Petation & Petation";
                break;
            case 10:
                STND_ServiceType = "Order to show cause";
                break;
            case 11:
                STND_ServiceType = "Order Standard";
                break;
            default:
                STND_ServiceType = " Not Available";
                break;
        }
        try {
            if (list.get(position).getServiceType().equals("Standard")) {
                holder.fullnameText.setVisibility(View.GONE);
                holder.textViewfullname.setVisibility(View.GONE);
                holder.bussnameText.setVisibility(View.GONE);
                holder.textViewbuss.setVisibility(View.GONE);
                holder.stndfullnameText.setVisibility(View.VISIBLE);
                holder.textViewstndfullname.setVisibility(View.VISIBLE);
                holder.casepaperType.setText(STND_ServiceType);
                holder.stndfullnameText.setText(list.get(position).getSTND_FULLNAME());
                holder.addressText.setText(list.get(position).getSTND_ADDRESS());
                holder.aptText.setText(list.get(position).getSTND_APT());
                holder.cityText.setText(list.get(position).getSTND_CITY());
                holder.stateText.setText(list.get(position).getSTND_STATE());
                holder.zipText.setText(list.get(position).getSTND_ZIP());
            }
            if (list.get(position).getServiceType().equals("L&T Commercial")) {
                holder.fullnameText.setVisibility(View.GONE);
                holder.textViewfullname.setVisibility(View.GONE);
                holder.bussnameText.setVisibility(View.VISIBLE);
                holder.textViewbuss.setVisibility(View.VISIBLE);
                holder.stndfullnameText.setVisibility(View.GONE);
                holder.textViewstndfullname.setVisibility(View.GONE);
//			holder.ltserviceTypeText.setText(list.get(position).getLTServiceType());
                holder.casepaperType.setText(list.get(position).getLTServiceType());
                holder.bussnameText.setText(list.get(position).getLTBIZNAME());
                holder.addressText.setText(list.get(position).getLTAddress());
                holder.aptText.setText(list.get(position).getLTApt());
                holder.cityText.setText(list.get(position).getLTCity());
                holder.stateText.setText(list.get(position).getLTState());
                holder.zipText.setText(list.get(position).getLTZip());
            }

            if (list.get(position).getServiceType().equals("L&T Residential")) {
                holder.fullnameText.setVisibility(View.VISIBLE);
                holder.textViewfullname.setVisibility(View.VISIBLE);
                holder.bussnameText.setVisibility(View.GONE);
                holder.textViewbuss.setVisibility(View.GONE);
                holder.stndfullnameText.setVisibility(View.GONE);
                holder.textViewstndfullname.setVisibility(View.GONE);
                holder.casepaperType.setText(list.get(position).getLTServiceType());
                holder.fullnameText.setText(list.get(position).getLTFullname());
                holder.addressText.setText(list.get(position).getLTAddress());
                holder.aptText.setText(list.get(position).getLTApt());
                holder.cityText.setText(list.get(position).getLTCity());
                holder.stateText.setText(list.get(position).getLTState());
                holder.zipText.setText(list.get(position).getLTZip());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
           // Toast.makeText(getContext(), "Can't Show List because of im ", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}
