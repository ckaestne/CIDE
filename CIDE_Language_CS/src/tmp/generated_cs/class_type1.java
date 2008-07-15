package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_type1 extends class_type {
  public class_type1(type_name type_name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type_name>("type_name", type_name)
    }, firstToken, lastToken);
  }
  public class_type1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_type1(cloneProperties(),firstToken,lastToken);
  }
  public type_name getType_name() {
    return ((PropertyOne<type_name>)getProperty("type_name")).getValue();
  }
}
