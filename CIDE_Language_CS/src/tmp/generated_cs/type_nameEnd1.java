package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type_nameEnd1 extends type_nameEnd {
  public type_nameEnd1(method_declaration method_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<method_declaration>("method_declaration", method_declaration)
    }, firstToken, lastToken);
  }
  public type_nameEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type_nameEnd1(cloneProperties(),firstToken,lastToken);
  }
  public method_declaration getMethod_declaration() {
    return ((PropertyOne<method_declaration>)getProperty("method_declaration")).getValue();
  }
}
