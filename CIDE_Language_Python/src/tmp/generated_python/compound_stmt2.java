package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class compound_stmt2 extends compound_stmt {
  public compound_stmt2(while_stmt while_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<while_stmt>("while_stmt", while_stmt)
    }, firstToken, lastToken);
  }
  public compound_stmt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new compound_stmt2(cloneProperties(),firstToken,lastToken);
  }
  public while_stmt getWhile_stmt() {
    return ((PropertyOne<while_stmt>)getProperty("while_stmt")).getValue();
  }
}
