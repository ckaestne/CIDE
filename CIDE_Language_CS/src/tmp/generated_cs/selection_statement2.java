package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class selection_statement2 extends selection_statement {
  public selection_statement2(switch_statement switch_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<switch_statement>("switch_statement", switch_statement)
    }, firstToken, lastToken);
  }
  public selection_statement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new selection_statement2(cloneProperties(),firstToken,lastToken);
  }
  public switch_statement getSwitch_statement() {
    return ((PropertyOne<switch_statement>)getProperty("switch_statement")).getValue();
  }
}
