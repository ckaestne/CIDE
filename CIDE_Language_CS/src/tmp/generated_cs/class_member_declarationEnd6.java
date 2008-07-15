package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_member_declarationEnd6 extends class_member_declarationEnd {
  public class_member_declarationEnd6(type type, typeEnd typeEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyOne<typeEnd>("typeEnd", typeEnd)
    }, firstToken, lastToken);
  }
  public class_member_declarationEnd6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_member_declarationEnd6(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public typeEnd getTypeEnd() {
    return ((PropertyOne<typeEnd>)getProperty("typeEnd")).getValue();
  }
}
