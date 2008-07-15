package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_member_declarationEnd4 extends class_member_declarationEnd {
  public class_member_declarationEnd4(conversion_operator_declaration conversion_operator_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<conversion_operator_declaration>("conversion_operator_declaration", conversion_operator_declaration)
    }, firstToken, lastToken);
  }
  public class_member_declarationEnd4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_member_declarationEnd4(cloneProperties(),firstToken,lastToken);
  }
  public conversion_operator_declaration getConversion_operator_declaration() {
    return ((PropertyOne<conversion_operator_declaration>)getProperty("conversion_operator_declaration")).getValue();
  }
}
