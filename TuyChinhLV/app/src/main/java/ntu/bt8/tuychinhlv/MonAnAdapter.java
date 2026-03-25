package ntu.bt8.tuychinhlv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MonAnAdapter extends BaseAdapter {
    private ArrayList<MonAn> dsMonAn;
    private LayoutInflater layoutInflater;
    private Context context;

    public MonAnAdapter( Context _context, ArrayList<MonAn> dsMonAn) {
        this.dsMonAn = dsMonAn;
        this.context = _context;
        this.layoutInflater=LayoutInflater.from(_context);
    }

    @Override
    public int getCount() {
        return dsMonAn.size();
    }

    @Override
    public Object getItem(int i) {
        return dsMonAn.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //view item hien hanh
       View viewHienHanh = view;

       //kiem tra
        if(viewHienHanh == null){
            viewHienHanh= layoutInflater.inflate(R.layout.activity_item_mon_an,null);
        }
        //lay du lieu
        MonAn  monAnHienTai = dsMonAn.get(i);

        //tim dieu khien
        TextView textView_TenMon= (TextView) viewHienHanh.findViewById(R.id.tenMonAn);
        TextView textView_GiaMon= (TextView) viewHienHanh.findViewById(R.id.giaMonAn);
        TextView textView_MoTaMon= (TextView) viewHienHanh.findViewById(R.id.moTaMonAn);
        ImageView img_AnhMHo= (ImageView) viewHienHanh.findViewById(R.id.anhDaiDien);

        //set du lieu
        textView_TenMon.setText(monAnHienTai.getTenMonAn());
        textView_GiaMon.setText(String.valueOf(monAnHienTai.getDonGia()));
        textView_MoTaMon.setText(monAnHienTai.getMoTa());
        img_AnhMHo.setImageResource(monAnHienTai.getIdAnhMinhHoa());

        return viewHienHanh;

    }
}
