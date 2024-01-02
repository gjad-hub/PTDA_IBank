package pt.ua.ibank.Staff.components.accountList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author ricar
 */
public class ContasListModel extends AbstractListModel {

    List<ClientOverview> data = null;

    public ContasListModel() {
        data = new ArrayList<>();
        var c = new ClientOverview(1, "e", 0.2, new Date(1, 1, 1));
        var d = new ClientOverview(2, "a", 69.0, new Date(1, 1, 1));
        var e = new ClientOverview(3, "sports", 420.69, new Date(1, 1, 1));
        data.add(c);
        data.add(d);
        data.add(e);
    }

    @Override

    public int getSize() {
        return data.size();
    }

    @Override
    public Object getElementAt(int index) {
        return data.get(index);
    }

}
