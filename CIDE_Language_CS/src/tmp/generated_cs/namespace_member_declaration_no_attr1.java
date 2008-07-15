package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class namespace_member_declaration_no_attr1 extends namespace_member_declaration_no_attr {
  public namespace_member_declaration_no_attr1(namespace_declaration namespace_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<namespace_declaration>("namespace_declaration", namespace_declaration)
    }, firstToken, lastToken);
  }
  public namespace_member_declaration_no_attr1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new namespace_member_declaration_no_attr1(cloneProperties(),firstToken,lastToken);
  }
  public namespace_declaration getNamespace_declaration() {
    return ((PropertyOne<namespace_declaration>)getProperty("namespace_declaration")).getValue();
  }
}
