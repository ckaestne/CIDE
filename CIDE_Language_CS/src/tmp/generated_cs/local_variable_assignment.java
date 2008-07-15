package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class local_variable_assignment extends GenASTNode {
  public local_variable_assignment(local_variable_initializer local_variable_initializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<local_variable_initializer>("local_variable_initializer", local_variable_initializer)
    }, firstToken, lastToken);
  }
  public local_variable_assignment(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new local_variable_assignment(cloneProperties(),firstToken,lastToken);
  }
  public local_variable_initializer getLocal_variable_initializer() {
    return ((PropertyOne<local_variable_initializer>)getProperty("local_variable_initializer")).getValue();
  }
}
