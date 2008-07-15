package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class statement2 extends statement {
  public statement2(local_variable_declaration local_variable_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<local_variable_declaration>("local_variable_declaration", local_variable_declaration)
    }, firstToken, lastToken);
  }
  public statement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new statement2(cloneProperties(),firstToken,lastToken);
  }
  public local_variable_declaration getLocal_variable_declaration() {
    return ((PropertyOne<local_variable_declaration>)getProperty("local_variable_declaration")).getValue();
  }
}
