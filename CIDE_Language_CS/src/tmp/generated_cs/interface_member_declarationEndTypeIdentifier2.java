package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_member_declarationEndTypeIdentifier2 extends interface_member_declarationEndTypeIdentifier {
  public interface_member_declarationEndTypeIdentifier2(interface_property_declaration interface_property_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<interface_property_declaration>("interface_property_declaration", interface_property_declaration)
    }, firstToken, lastToken);
  }
  public interface_member_declarationEndTypeIdentifier2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_member_declarationEndTypeIdentifier2(cloneProperties(),firstToken,lastToken);
  }
  public interface_property_declaration getInterface_property_declaration() {
    return ((PropertyOne<interface_property_declaration>)getProperty("interface_property_declaration")).getValue();
  }
}
