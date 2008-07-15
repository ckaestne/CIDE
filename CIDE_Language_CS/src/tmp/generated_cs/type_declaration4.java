package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type_declaration4 extends type_declaration {
  public type_declaration4(enum_declaration enum_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<enum_declaration>("enum_declaration", enum_declaration)
    }, firstToken, lastToken);
  }
  public type_declaration4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type_declaration4(cloneProperties(),firstToken,lastToken);
  }
  public enum_declaration getEnum_declaration() {
    return ((PropertyOne<enum_declaration>)getProperty("enum_declaration")).getValue();
  }
}
