package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type_declaration1 extends type_declaration {
  public type_declaration1(class_declaration class_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<class_declaration>("class_declaration", class_declaration)
    }, firstToken, lastToken);
  }
  public type_declaration1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type_declaration1(cloneProperties(),firstToken,lastToken);
  }
  public class_declaration getClass_declaration() {
    return ((PropertyOne<class_declaration>)getProperty("class_declaration")).getValue();
  }
}
