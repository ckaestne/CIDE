package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type_nameEnd2 extends type_nameEnd {
  public type_nameEnd2(property_declaration property_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<property_declaration>("property_declaration", property_declaration)
    }, firstToken, lastToken);
  }
  public type_nameEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type_nameEnd2(cloneProperties(),firstToken,lastToken);
  }
  public property_declaration getProperty_declaration() {
    return ((PropertyOne<property_declaration>)getProperty("property_declaration")).getValue();
  }
}
