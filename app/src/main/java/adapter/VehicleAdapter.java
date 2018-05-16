package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dommy.qrcode.R;

import java.util.ArrayList;
import java.util.List;

import entity.VehicleEntity;

/**
 * @package PACKAGE_NAME
 * @fileName VehicleAdapter
 * @Author Bob on 2018/5/14 14:39.
 * @Describe TODO
 */

public class VehicleAdapter extends BaseAdapter {

    private List<VehicleEntity> vehicleEntityList = new ArrayList<>();
    private Context mContext;

    public VehicleAdapter(Context context,List<VehicleEntity> vehicleEntities){
        this.mContext = context;
        this.vehicleEntityList = vehicleEntities;
    }

    @Override
    public int getCount() {
        return vehicleEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return vehicleEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        if(null == convertView){
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle,null);
            mHolder.mTvVehicleNum = (TextView) convertView.findViewById(R.id.tv_vehicle_num);
            mHolder.mTvCheckInTime = (TextView) convertView.findViewById(R.id.tv_check_in_time);
            mHolder.mTvCheckOutTime = (TextView) convertView.findViewById(R.id.tv_check_out_time);
            mHolder.mTvVehicleWeight = (TextView) convertView.findViewById(R.id.tv_vehicle_weight);
            mHolder.mVehicleDepartment = (TextView) convertView.findViewById(R.id.tv_vehicle_department);
            mHolder.mTvVehicleDriver = (TextView) convertView.findViewById(R.id.tv_driver);
            mHolder.mTvQRCode = (TextView) convertView.findViewById(R.id.tv_qrcode);
            convertView.setTag(mHolder);
        }else{
            mHolder =(ViewHolder) convertView.getTag();
        }

        VehicleEntity vehicleEntity = vehicleEntityList.get(position);
        if(null != vehicleEntity){
            mHolder.mTvQRCode.setText(vehicleEntity.getQrCode().trim());
            mHolder.mTvVehicleNum.setText(vehicleEntity.getVehicleNum());
            mHolder.mTvCheckInTime.setText(vehicleEntity.getCheckInDate());
            mHolder.mTvCheckOutTime.setText(vehicleEntity.getCheckOutDate());
            mHolder.mVehicleDepartment.setText(vehicleEntity.getVehicleDepartment());
            mHolder.mTvVehicleDriver.setText(vehicleEntity.getDriverName());
            mHolder.mTvVehicleWeight.setText(vehicleEntity.getVehicleWeight());
        }
        return convertView;
    }

    private class ViewHolder{
        TextView mTvVehicleNum,mTvVehicleDriver,mTvVehicleWeight
                ,mTvCheckInTime,mTvCheckOutTime,mTvIsCheck,mVehicleDepartment,mTvQRCode;
    }
}
