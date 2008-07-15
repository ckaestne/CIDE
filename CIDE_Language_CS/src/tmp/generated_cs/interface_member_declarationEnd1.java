package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_member_declarationEnd1 extends interface_member_declarationEnd {
  public interface_member_declarationEnd1(interface_event_declaration interface_event_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<interface_event_declaration>("interface_event_declaration", interface_event_declaration)
    }, firstToken, lastToken);
  }
  public interface_member_declarationEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_member_declarationEnd1(cloneProperties(),firstToken,lastToken);
  }
  public interface_event_declaration getInterface_event_declaration() {
    return ((PropertyOne<interface_event_declaration>)getProperty("interface_event_declaration")).getValue();
  }
}
