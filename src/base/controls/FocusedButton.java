package base.controls;

import javafx.scene.Node;

/**
 * Binds defaulButton to focused, making the enter action always choose the focused button
 * @author Franky Laseure
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