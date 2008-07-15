package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class event_declarationInt1 extends event_declarationInt {
  public event_declarationInt1(type_name type_name, event_accessor_declarations event_accessor_declarations, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type_name>("type_name", type_name),
      new PropertyOne<event_accessor_declarations>("event_accessor_declarations", event_accessor_declarations)
    }, firstToken, lastToken);
  }
  public event_declarationInt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new event_declarationInt1(cloneProperties(),firstToken,lastToken);
  }
  public type_name getType_name() {
    return ((PropertyOne<type_name>)getProperty("type_name")).getValue();
  }
  public event_accessor_declarations getEvent_accessor_declarations() {
    return ((PropertyOne<event_accessor_declarations>)getProperty("event_accessor_declarations")).getValue();
  }
}
