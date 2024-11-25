package todolist.swing;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

/**
 *
 * @author ghiyatsa_
 */
public class ButtonAction extends JButton {

    public ButtonAction() {

        putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:8,20,8,20;"
                + "background:$Panel.background");
    }
}
