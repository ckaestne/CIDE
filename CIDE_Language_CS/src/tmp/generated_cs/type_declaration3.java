package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type_declaration3 extends type_declaration {
  public type_declaration3(interface_declaration interface_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<interface_declaration>("interface_declaration", interface_declaration)
    }, firstToken, lastToken);
  }
  public type_declaration3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type_declaration3(cloneProperties(),firstToken,lastToken);
  }
  public interface_declaration getInterface_declaration() {
    return ((PropertyOne<interface_declaration>)getProperty("interface_declaration")).getValue();
  }
}
