package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type_nameEnd3 extends type_nameEnd {
  public type_nameEnd3(field_declaration field_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<field_declaration>("field_declaration", field_declaration)
    }, firstToken, lastToken);
  }
  public type_nameEnd3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type_nameEnd3(cloneProperties(),firstToken,lastToken);
  }
  public field_declaration getField_declaration() {
    return ((PropertyOne<field_declaration>)getProperty("field_declaration")).getValue();
  }
}
