package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class selection_statement1 extends selection_statement {
  public selection_statement1(if_statement if_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<if_statement>("if_statement", if_statement)
    }, firstToken, lastToken);
  }
  public selection_statement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new selection_statement1(cloneProperties(),firstToken,lastToken);
  }
  public if_statement getIf_statement() {
    return ((PropertyOne<if_statement>)getProperty("if_statement")).getValue();
  }
}
