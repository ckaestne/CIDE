package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class statement3 extends statement {
  public statement3(local_constant_declaration local_constant_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<local_constant_declaration>("local_constant_declaration", local_constant_declaration)
    }, firstToken, lastToken);
  }
  public statement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new statement3(cloneProperties(),firstToken,lastToken);
  }
  public local_constant_declaration getLocal_constant_declaration() {
    return ((PropertyOne<local_constant_declaration>)getProperty("local_constant_declaration")).getValue();
  }
}
