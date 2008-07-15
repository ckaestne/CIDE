package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class event_declarationInt2 extends event_declarationInt {
  public event_declarationInt2(event_variable_declarator event_variable_declarator, ArrayList<event_variable_declarator> event_variable_declarator1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<event_variable_declarator>("event_variable_declarator", event_variable_declarator),
      new PropertyZeroOrMore<event_variable_declarator>("event_variable_declarator1", event_variable_declarator1)
    }, firstToken, lastToken);
  }
  public event_declarationInt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new event_declarationInt2(cloneProperties(),firstToken,lastToken);
  }
  public event_variable_declarator getEvent_variable_declarator() {
    return ((PropertyOne<event_variable_declarator>)getProperty("event_variable_declarator")).getValue();
  }
  public ArrayList<event_variable_declarator> getEvent_variable_declarator1() {
    return ((PropertyZeroOrMore<event_variable_declarator>)getProperty("event_variable_declarator1")).getValue();
  }
}
