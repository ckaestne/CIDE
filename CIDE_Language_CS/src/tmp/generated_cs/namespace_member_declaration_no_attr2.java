package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class namespace_member_declaration_no_attr2 extends namespace_member_declaration_no_attr {
  public namespace_member_declaration_no_attr2(type_modifiers type_modifiers, type_declaration type_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<type_modifiers>("type_modifiers", type_modifiers),
      new PropertyOne<type_declaration>("type_declaration", type_declaration)
    }, firstToken, lastToken);
  }
  public namespace_member_declaration_no_attr2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new namespace_member_declaration_no_attr2(cloneProperties(),firstToken,lastToken);
  }
  public type_modifiers getType_modifiers() {
    return ((PropertyZeroOrOne<type_modifiers>)getProperty("type_modifiers")).getValue();
  }
  public type_declaration getType_declaration() {
    return ((PropertyOne<type_declaration>)getProperty("type_declaration")).getValue();
  }
}
