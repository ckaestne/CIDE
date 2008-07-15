package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_member_declarationEnd1 extends class_member_declarationEnd {
  public class_member_declarationEnd1(constant_declaration constant_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<constant_declaration>("constant_declaration", constant_declaration)
    }, firstToken, lastToken);
  }
  public class_member_declarationEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_member_declarationEnd1(cloneProperties(),firstToken,lastToken);
  }
  public constant_declaration getConstant_declaration() {
    return ((PropertyOne<constant_declaration>)getProperty("constant_declaration")).getValue();
  }
}
