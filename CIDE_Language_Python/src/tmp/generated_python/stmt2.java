package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class stmt2 extends stmt {
  public stmt2(compound_stmt compound_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<compound_stmt>("compound_stmt", compound_stmt)
    }, firstToken, lastToken);
  }
  public stmt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new stmt2(cloneProperties(),firstToken,lastToken);
  }
  public compound_stmt getCompound_stmt() {
    return ((PropertyOne<compound_stmt>)getProperty("compound_stmt")).getValue();
  }
}
