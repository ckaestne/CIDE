package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_member_declarationEnd3 extends class_member_declarationEnd {
  public class_member_declarationEnd3(destructor_declaration destructor_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<destructor_declaration>("destructor_declaration", destructor_declaration)
    }, firstToken, lastToken);
  }
  public class_member_declarationEnd3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_member_declarationEnd3(cloneProperties(),firstToken,lastToken);
  }
  public destructor_declaration getDestructor_declaration() {
    return ((PropertyOne<destructor_declaration>)getProperty("destructor_declaration")).getValue();
  }
}
