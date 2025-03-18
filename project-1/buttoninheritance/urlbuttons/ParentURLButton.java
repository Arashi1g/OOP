package buttoninheritance.urlbuttons;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import buttoninheritance.ButtonUI;

// Parent class: URL Buttons
public class ParentURLButton extends ButtonUI implements ActionListener {
    protected String url;

    public ParentURLButton(String text, String url) {
        super(text);
        this.url = url;
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       URLHandler.openBrowser(url);// USing the utitlity class ( arashi )
    }

    protected void reNameButton(String name){
        this.setText(name);
    }

}


