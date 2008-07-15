package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_member_declarationEnd5 extends class_member_declarationEnd {
  public class_member_declarationEnd5(type_declaration type_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type_declaration>("type_declaration", type_declaration)
    }, firstToken, lastToken);
  }
  public class_member_declarationEnd5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_member_declarationEnd5(cloneProperties(),firstToken,lastToken);
  }
  public type_declaration getType_declaration() {
    return ((PropertyOne<type_declaration>)getProperty("type_declaration")).getValue();
  }
}
