package vn.viettuts.qlsv.entity;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateAdapter extends XmlAdapter<String, Date> {
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public Date unmarshal(String v) throws Exception {
        return format.parse(v);
    }
    @Override
    public String marshal(Date v) throws Exception {
        return format.format(v);
    }
}
