package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class iteration_statement4 extends iteration_statement {
  public iteration_statement4(foreach_statement foreach_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<foreach_statement>("foreach_statement", foreach_statement)
    }, firstToken, lastToken);
  }
  public iteration_statement4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new iteration_statement4(cloneProperties(),firstToken,lastToken);
  }
  public foreach_statement getForeach_statement() {
    return ((PropertyOne<foreach_statement>)getProperty("foreach_statement")).getValue();
  }
}
