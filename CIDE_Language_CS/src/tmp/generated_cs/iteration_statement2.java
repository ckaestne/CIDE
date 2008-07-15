package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class iteration_statement2 extends iteration_statement {
  public iteration_statement2(do_statement do_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<do_statement>("do_statement", do_statement)
    }, firstToken, lastToken);
  }
  public iteration_statement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new iteration_statement2(cloneProperties(),firstToken,lastToken);
  }
  public do_statement getDo_statement() {
    return ((PropertyOne<do_statement>)getProperty("do_statement")).getValue();
  }
}
