package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_member_declarationEndTypeIdentifier1 extends interface_member_declarationEndTypeIdentifier {
  public interface_member_declarationEndTypeIdentifier1(interface_method_declaration interface_method_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<interface_method_declaration>("interface_method_declaration", interface_method_declaration)
    }, firstToken, lastToken);
  }
  public interface_member_declarationEndTypeIdentifier1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_member_declarationEndTypeIdentifier1(cloneProperties(),firstToken,lastToken);
  }
  public interface_method_declaration getInterface_method_declaration() {
    return ((PropertyOne<interface_method_declaration>)getProperty("interface_method_declaration")).getValue();
  }
}
