package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type_declaration2 extends type_declaration {
  public type_declaration2(struct_declaration struct_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<struct_declaration>("struct_declaration", struct_declaration)
    }, firstToken, lastToken);
  }
  public type_declaration2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type_declaration2(cloneProperties(),firstToken,lastToken);
  }
  public struct_declaration getStruct_declaration() {
    return ((PropertyOne<struct_declaration>)getProperty("struct_declaration")).getValue();
  }
}
