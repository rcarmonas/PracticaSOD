package interfaz;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class IconCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column)
	{
		if(value instanceof JProgressBar)
		{
			JProgressBar progressbar = (JProgressBar)value;
			return progressbar;
		}
		else
		{
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}
}
