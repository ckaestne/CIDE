package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class field_declaration_start extends GenASTNode {
  public field_declaration_start(variable_initializer variable_initializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<variable_initializer>("variable_initializer", variable_initializer)
    }, firstToken, lastToken);
  }
  public field_declaration_start(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new field_declaration_start(cloneProperties(),firstToken,lastToken);
  }
  public variable_initializer getVariable_initializer() {
    return ((PropertyZeroOrOne<variable_initializer>)getProperty("variable_initializer")).getValue();
  }
}
