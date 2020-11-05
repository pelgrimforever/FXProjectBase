package base.controls;

import javafx.scene.Node;

/**
 *
 * @author pelgrim
 */
public class FocusedButton extends javafx.scene.control.Button {

    public FocusedButton ( ) {
        super ( );
        bindFocusToDefault ( );
    }

    public FocusedButton ( String text ) {
        super ( text );
        bindFocusToDefault ( );
    }

    public FocusedButton ( String text, Node graphic ) {
        super ( text, graphic );
        bindFocusToDefault ( );
    }

    private void bindFocusToDefault ( ) {
        defaultButtonProperty().bind(focusedProperty());
    }

}