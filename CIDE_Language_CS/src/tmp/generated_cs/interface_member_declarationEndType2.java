package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_member_declarationEndType2 extends interface_member_declarationEndType {
  public interface_member_declarationEndType2(identifier identifier, interface_member_declarationEndTypeIdentifier interface_member_declarationEndTypeIdentifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyOne<interface_member_declarationEndTypeIdentifier>("interface_member_declarationEndTypeIdentifier", interface_member_declarationEndTypeIdentifier)
    }, firstToken, lastToken);
  }
  public interface_member_declarationEndType2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_member_declarationEndType2(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public interface_member_declarationEndTypeIdentifier getInterface_member_declarationEndTypeIdentifier() {
    return ((PropertyOne<interface_member_declarationEndTypeIdentifier>)getProperty("interface_member_declarationEndTypeIdentifier")).getValue();
  }
}
