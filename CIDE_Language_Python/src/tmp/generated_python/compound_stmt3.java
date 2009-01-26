package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class compound_stmt3 extends compound_stmt {
  public compound_stmt3(for_stmt for_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<for_stmt>("for_stmt", for_stmt)
    }, firstToken, lastToken);
  }
  public compound_stmt3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new compound_stmt3(cloneProperties(),firstToken,lastToken);
  }
  public for_stmt getFor_stmt() {
    return ((PropertyOne<for_stmt>)getProperty("for_stmt")).getValue();
  }
}
