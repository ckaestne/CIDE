package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class event_declaration extends GenASTNode {
  public event_declaration(type type, event_declarationInt event_declarationInt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyOne<event_declarationInt>("event_declarationInt", event_declarationInt)
    }, firstToken, lastToken);
  }
  public event_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new event_declaration(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public event_declarationInt getEvent_declarationInt() {
    return ((PropertyOne<event_declarationInt>)getProperty("event_declarationInt")).getValue();
  }
}
