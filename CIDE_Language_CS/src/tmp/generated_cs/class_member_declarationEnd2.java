package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_member_declarationEnd2 extends class_member_declarationEnd {
  public class_member_declarationEnd2(event_declaration event_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<event_declaration>("event_declaration", event_declaration)
    }, firstToken, lastToken);
  }
  public class_member_declarationEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_member_declarationEnd2(cloneProperties(),firstToken,lastToken);
  }
  public event_declaration getEvent_declaration() {
    return ((PropertyOne<event_declaration>)getProperty("event_declaration")).getValue();
  }
}
