package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type_declaration5 extends type_declaration {
  public type_declaration5(delegate_declaration delegate_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<delegate_declaration>("delegate_declaration", delegate_declaration)
    }, firstToken, lastToken);
  }
  public type_declaration5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type_declaration5(cloneProperties(),firstToken,lastToken);
  }
  public delegate_declaration getDelegate_declaration() {
    return ((PropertyOne<delegate_declaration>)getProperty("delegate_declaration")).getValue();
  }
}
